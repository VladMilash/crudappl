package com.mvo.crud.repository.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecutor<T> {
    T execute(PreparedStatement pstm) throws SQLException;
}
