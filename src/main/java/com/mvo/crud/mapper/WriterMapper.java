package com.mvo.crud.mapper;

import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WriterMapper implements Mapper <Writer> {
    @Override
    public Writer map(ResultSet rs) throws SQLException {
        int writerId = rs.getInt("id");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");

        List<Post> posts = new ArrayList<>();

        return new Writer(writerId, firstName, lastName, posts);
    }
}
