package com.mvo.crud.mapper;

import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.WriterRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WriterMapper implements Mapper <Writer> {
    private final WriterRepository writerRepository;

    public WriterMapper(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public Writer map(ResultSet rs) throws SQLException {
        int writerId = rs.getInt("id");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");

        List<Post> posts = writerRepository.findAllPostsByWriterId(writerId);

        return new Writer(writerId, firstName, lastName, posts);
    }
}
