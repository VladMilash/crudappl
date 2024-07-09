package com.mvo.crud.repository;

import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.dbutil.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {

    SqlHelper sqlHelper = new SqlHelper();

    @Override
    public Writer findById(Integer id) {
        return sqlHelper.execute("SELECT * FROM writers WHERE id = ?", pstm -> {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (!rs.next()) {
                    throw new RuntimeException("Writer with id " + id + " not found");
                }
                int writerId = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                List<Post> posts = new ArrayList<>();

                return new Writer(writerId, firstName, lastName, posts);
            }
        });
    }

    @Override
    public List<Writer> findAll() {
        return sqlHelper.execute("SELECT * FROM writers", pstm -> {
            List<Writer> writers = new ArrayList<>();
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    int writerId = rs.getInt("id");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");

                    List<Post> posts = new ArrayList<>();

                    Writer writer = new Writer(writerId, firstName, lastName, posts);
                    writers.add(writer);
                }
            }
            return writers;
        });
    }

    @Override
    public void save(Writer writer) {
        sqlHelper.executeWithGeneratedKeys("INSERT INTO writers (firstName, lastName) VALUES (?, ?)", pstm -> {
            pstm.setString(1, writer.getFirstName());
            pstm.setString(2, writer.getLastName());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    writer.setId(generatedKeys.getInt(1));
                }
            }
            return null;
        });
    }

    @Override
    public void update(Writer writer) {
        sqlHelper.execute("UPDATE writers SET firstName = ?, lastName = ? WHERE id = ?", pstm -> {
            pstm.setString(1, writer.getFirstName());
            pstm.setString(2, writer.getLastName());
            pstm.setInt(3, writer.getId());
            pstm.executeUpdate();
            return null;
        });
    }

    @Override
    public void deleteById(Integer id) {
        sqlHelper.execute("DELETE FROM writers WHERE id = ?", pstm -> {
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No writer found with ID: " + id);
            }
            return null;
        });
    }
}
