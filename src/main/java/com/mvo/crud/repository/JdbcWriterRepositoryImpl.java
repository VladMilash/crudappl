package com.mvo.crud.repository;

import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.mapper.PostMapper;
import com.mvo.crud.mapper.WriterMapper;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.dbutil.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {

    private final SqlHelper sqlHelper = new SqlHelper();
    private final WriterMapper writerMapper = new WriterMapper(this);

    @Override
    public Writer findById(Integer id) {
        return sqlHelper.execute("SELECT * FROM writers WHERE id = ?", pstm -> {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (!rs.next()) {
                    throw new NotExistCrudException(id);
                }
                return writerMapper.map(rs);
            }
        });
    }

    @Override
    public List<Writer> findAll() {
        return sqlHelper.execute("SELECT * FROM writers", pstm -> {
            List<Writer> writers = new ArrayList<>();
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    writers.add(writerMapper.map(rs));
                }
            }
            return writers;
        });
    }

    @Override
    public Writer save(Writer writer) {
        return sqlHelper.executeWithGeneratedKeys("INSERT INTO writers (firstName, lastName) VALUES (?, ?)", pstm -> {
            pstm.setString(1, writer.getFirstName());
            pstm.setString(2, writer.getLastName());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    writer.setId(generatedKeys.getInt(1));
                }
            }
            return writer;
        });
    }

    @Override
    public Writer update(Writer writer) {
        return sqlHelper.execute("UPDATE writers SET firstName = ?, lastName = ? WHERE id = ?", pstm -> {
            pstm.setString(1, writer.getFirstName());
            pstm.setString(2, writer.getLastName());
            pstm.setInt(3, writer.getId());
            pstm.executeUpdate();
            return writer;
        });

    }

    @Override
    public void deleteById(Integer id) {
        sqlHelper.execute("DELETE FROM writers WHERE id = ?", pstm -> {
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new NotExistCrudException(id);
            }
            return null;
        });
    }

    @Override
    public List<Post> findAllPostsByWriterId(Integer writerId) {
        return sqlHelper.execute("SELECT p.* FROM Post p JOIN writer_post wp ON p.id = wp.post_id WHERE wp.writer_id = ?", pstm -> {
            pstm.setInt(1, writerId);
            List<Post> posts = new ArrayList<>();
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    posts.add(new PostMapper(new JdbcPostRepositoryImpl()).map(rs));
                }
            }
            return posts;
        });
    }

    @Override
    public void deleteAllPostsByWriterId(Integer writerId) {
        sqlHelper.execute("DELETE FROM Post WHERE id IN (SELECT post_id FROM writer_post WHERE writer_id = ?)", pstm -> {
            pstm.setInt(1, writerId);
            pstm.executeUpdate();
            return null;
        });
    }

    @Override
    public void addPostToWriter(Integer writerId, Integer postId) {
        sqlHelper.execute("INSERT INTO Writer_Post (writer_id, post_id) VALUES (?, ?)", pstm -> {
            pstm.setInt(1,writerId);
            pstm.setInt(2,postId);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new NotExistCrudException(writerId, postId);
            }
            return null;
        });
    }
}
