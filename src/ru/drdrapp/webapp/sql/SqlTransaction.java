package ru.drdrapp.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlTransaction<T> {
        T execute(Connection connection) throws SQLException;
}