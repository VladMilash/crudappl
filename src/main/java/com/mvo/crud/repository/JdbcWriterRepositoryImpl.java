package com.mvo.crud.repository;

import com.mvo.crud.model.Post;
import com.mvo.crud.model.Writer;
import com.mvo.crud.repository.dbutil.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {
    @Override
    public Writer findById(Integer id) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String selectSQL = "SELECT * FROM writers WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(selectSQL);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException("Writer with id " + id + " not found");
            }
            int writeId = rs.getInt("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");

            List<Post> posts = new ArrayList<>();

            return new Writer(writeId, firstName, lastName, posts);

        } catch (SQLException e) {
            throw new RuntimeException("Error finding writer with id " + id, e);
        }
    }

    @Override
    public List<Writer> findAll() {
        List<Writer> writers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String allSelectSQL = "SELECT * FROM writers";
            PreparedStatement pstm = connection.prepareStatement(allSelectSQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int writeId = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                List<Post> posts = new ArrayList<>(); // Placeholder for now

                Writer writer = new Writer(writeId, firstName, lastName, posts);
                writers.add(writer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all writers", e);
        }
        return writers;
    }

    @Override
    public void save(Writer writer) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String insertSQL = "INSERT INTO writers (firstName, lastName) VALUES (?, ?)";
            PreparedStatement pstm = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, writer.getFirstName());
            pstm.setString(2, writer.getLastName());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    writer.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving writer: ", e);
        }
    }

    @Override
    public void update(Writer writer) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String updateSQL = "UPDATE writers SET firstName = ?, lastName = ? WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(updateSQL);
            pstm.setString(1, writer.getFirstName());
            pstm.setString(2, writer.getLastName());
            pstm.setInt(3, writer.getId()); // Устанавливаем id для условия WHERE
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating writer: " + writer.getId(), e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String deleteSQL = "DELETE FROM writers WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new RuntimeException("No writer found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting writer with ID: " + id, e);
        }
    }
}
