package ru.drdrapp.webapp.sql;

import ru.drdrapp.webapp.exeption.ExistStorageException;
import ru.drdrapp.webapp.exeption.StorageException;

import java.sql.SQLException;

public class SqlException {
    public static final String POSTGRES_UNIQUE_VIOLATION = "23505";

    public static StorageException convertException(SQLException e) {
        if (POSTGRES_UNIQUE_VIOLATION.equals(e.getSQLState())) {
            throw new ExistStorageException(e);
        }
        throw new StorageException(e);
    }
}