package com.mvo.crud.mapper;

import com.mvo.crud.model.Lable;

import java.sql.ResultSet;
import java.sql.SQLException;;

public class LableMapper implements Mapper<Lable> {
    @Override
    public Lable map(ResultSet rs) throws SQLException {
        int lableId = rs.getInt("id");
        String name = rs.getString("name");
        return new Lable(lableId, name);
    }
}
