package com.mvo.crud.mapper;

import com.mvo.crud.model.Label;

import java.sql.ResultSet;
import java.sql.SQLException;;

public class LableMapper implements Mapper<Label> {
    @Override
    public Label map(ResultSet rs) throws SQLException {
        int lableId = rs.getInt("id");
        String name = rs.getString("name");
        return new Label(lableId, name);
    }
}
