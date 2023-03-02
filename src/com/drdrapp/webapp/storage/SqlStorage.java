package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.NotExistStorageException;
import com.drdrapp.webapp.model.*;
import com.drdrapp.webapp.sql.SqlHelper;
import com.drdrapp.webapp.util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionExecute(connection -> {
                    insertResume(connection, r);
                    insertContacts(connection, r);
                    insertSection(connection, r);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.command("DELETE FROM resume WHERE uuid = ?",
                sqlBox -> {
                    sqlBox.setString(1, uuid);
                    if (sqlBox.executeUpdate() == 0) {
                        throw new NotExistStorageException(uuid);
                    }
                    return null;
                });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionExecute((connection) -> selectFullResume(uuid, connection));
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionExecute((connection) -> {
            updateResume(connection, r);
            updateContacts(connection, r);
            updateSection(connection, r);
            return null;
        });
    }

    @Override
    public int size() {
        return sqlHelper.command("SELECT COUNT(uuid) AS cc FROM resume",
                sqlBox -> {
                    ResultSet sqlResult = sqlBox.executeQuery();
                    return sqlResult.next() ? sqlResult.getInt("cc") : 0;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionExecute((connection) -> {
            Map<String, Resume> resumes_map = new LinkedHashMap<>();
            try (PreparedStatement sqlBox = connection.prepareStatement("SELECT uuid, full_name FROM resume ORDER BY full_name, uuid")) {
                ResultSet sqlResult = sqlBox.executeQuery();
                while (sqlResult.next()) {
                    resumes_map.put(sqlResult.getString("uuid"), new Resume(sqlResult.getString("uuid"), sqlResult.getString("full_name")));
                }
            }
            try (PreparedStatement sqlBox = connection.prepareStatement("SELECT type, value, resume_uuid FROM contact")) {
                ResultSet sqlResult = sqlBox.executeQuery();
                while (sqlResult.next()) {
                    resumes_map.get(sqlResult.getString("resume_uuid")).addContact(ContactType.valueOf(sqlResult.getString("type")), sqlResult.getString("value"));
                }
            }
            try (PreparedStatement sqlBox = connection.prepareStatement("SELECT type, value, resume_uuid FROM section ORDER BY type")) {
                ResultSet sqlResult = sqlBox.executeQuery();
                while (sqlResult.next()) {
                    addSection(sqlResult, resumes_map.get(sqlResult.getString("resume_uuid")));
                }
            }
            return new ArrayList<>(resumes_map.values());
        });
    }

    @Override
    public void clear() {
        sqlHelper.command("DELETE FROM resume");
    }

    private static Resume selectFullResume(String uuid, Connection connection) throws SQLException {
        Resume r = selectResume(uuid, connection);
        selectContacts(connection, r);
        selectSection(connection, r);
        return r;
    }

    private static Resume selectResume(String uuid, Connection connection) throws SQLException {
        try (PreparedStatement sqlBox = connection.prepareStatement("SELECT uuid, full_name FROM resume WHERE uuid =?")) {
            sqlBox.setString(1, uuid);
            ResultSet sqlResult = sqlBox.executeQuery();
            if (!sqlResult.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(sqlResult.getString("uuid"), sqlResult.getString("full_name"));
        }
    }

    private static void insertResume(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement sqlBox = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
            sqlBox.setString(1, r.getUuid());
            sqlBox.setString(2, r.getFullName());
            sqlBox.execute();
        }
    }

    private static void updateResume(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement sqlBox = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid =?")) {
            sqlBox.setString(1, r.getFullName());
            sqlBox.setString(2, r.getUuid());
            if (sqlBox.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
        }
    }

    private static void selectContacts(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement sqlBox = connection.prepareStatement("SELECT type, value FROM contact WHERE resume_uuid =?")) {
            sqlBox.setString(1, r.getUuid());
            ResultSet sqlResult = sqlBox.executeQuery();
            while (sqlResult.next()) {
                addContact(r, sqlResult);
            }
        }
    }

    private static void addContact(Resume r, ResultSet sqlResult) throws SQLException {
        r.addContact(ContactType.valueOf(sqlResult.getString("type")), sqlResult.getString("value"));
    }

    private static void deleteContacts(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement sqlBox = connection.prepareStatement("DELETE FROM contact WHERE resume_uuid =?")) {
            sqlBox.setString(1, r.getUuid());
            sqlBox.executeUpdate();
        }
    }

    private static void insertContacts(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement sqlBox = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (var contact : r.getContacts().entrySet()) {
                sqlBox.setString(1, r.getUuid());
                sqlBox.setString(2, contact.getKey().name());
                sqlBox.setString(3, contact.getValue());
                sqlBox.addBatch();
            }
            sqlBox.executeBatch();
        }
    }

    private static void updateContacts(Connection connection, Resume r) throws SQLException {
        deleteContacts(connection, r);
        insertContacts(connection, r);
    }

    private static void selectSection(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement sqlBox = connection.prepareStatement("SELECT resume_uuid, type, value FROM section WHERE resume_uuid =?")) {
            sqlBox.setString(1, r.getUuid());
            ResultSet sqlResult = sqlBox.executeQuery();
            while (sqlResult.next()) {
                addSection(sqlResult, r);
            }
        }
    }

    private static void addSection(ResultSet sqlResult, Resume r) throws SQLException {
        r.addSection(SectionType.valueOf(sqlResult.getString("type")), JsonParser.read(sqlResult.getString("value"), AbstractSection.class));
    }

    private static void insertSection(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement sqlBox = connection.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (var section : r.getSections().entrySet()) {
                String sectionText = JsonParser.write(section.getValue(), AbstractSection.class);
                if (sectionText != null) {
                    sqlBox.setString(1, r.getUuid());
                    sqlBox.setString(2, section.getKey().name());
                    sqlBox.setString(3, sectionText);
                    sqlBox.addBatch();
                }
            }
            sqlBox.executeBatch();
        }
    }

    private static void deleteSection(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement sqlBox = connection.prepareStatement("DELETE FROM section WHERE resume_uuid =?")) {
            sqlBox.setString(1, r.getUuid());
            sqlBox.executeUpdate();
        }
    }

    private static void updateSection(Connection connection, Resume r) throws SQLException {
        deleteSection(connection, r);
        insertSection(connection, r);
    }

}