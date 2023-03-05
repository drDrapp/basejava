package ru.drdrapp.webapp.sql;

import ru.drdrapp.webapp.exeption.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.connectionFactory = connectionFactory;
    }

    public void command(String sqlText) {
        command(sqlText, PreparedStatement::execute);
    }

    public <T> T command(String sqlText, SqlExecutor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement sqlBox = connection.prepareStatement(sqlText)) {
            return executor.execute(sqlBox);
        } catch (SQLException e) {
            throw SqlException.convertException(e);
        }
    }

    public <T> T transactionExecute(SqlTransaction<T> transaction) {
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                connection.setAutoCommit(false);
                T result = transaction.execute(connection);
                connection.commit();
                return result;
            } catch (SQLException e) {
                connection.rollback();
                throw SqlException.convertException(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

}