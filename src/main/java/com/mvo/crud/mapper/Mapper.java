package com.mvo.crud.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper <T> {
    T map (ResultSet rs) throws SQLException;
}
