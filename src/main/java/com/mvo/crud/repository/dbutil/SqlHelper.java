package com.mvo.crud.repository.dbutil;

import com.mvo.crud.exception.CrudException;
import com.mvo.crud.exception.ExistCrudException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlHelper {
    public <T> T execute(String sql, SqlExecutor<T> executor) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement pstm = connection.prepareStatement(sql);
            return executor.execute(pstm);
        } catch (SQLException e) {
            throw new CrudException(e);
        }
    }

    public <T> T executeWithGeneratedKeys(String sql, SqlExecutor<T> executor) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return executor.execute(pstm);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistCrudException();
            }
            throw new CrudException(e);
        }
    }

}
