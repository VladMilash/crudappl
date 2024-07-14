package com.mvo.crud.repository;

import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.mapper.LableMapper;
import com.mvo.crud.model.Label;
import com.mvo.crud.repository.dbutil.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcLabelRepositoryImpl implements LabelRepository {

    private final SqlHelper sqlHelper = new SqlHelper();
    private final LableMapper lableMapper = new LableMapper();

    @Override
    public Label findById(Integer id) {
        return sqlHelper.execute("SELECT * FROM label WHERE id = ?", pstm -> {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (!rs.next()) {
                    throw new NotExistCrudException(id);
                }
                return lableMapper.map(rs);
            }
        });
    }

    @Override
    public List<Label> findAll() {
        return sqlHelper.execute("SELECT * FROM label", pstm -> {
            List<Label> labels = new ArrayList<>();
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    labels.add(lableMapper.map(rs));
                }
                return labels;
            }
        });
    }

    @Override
    public Label save(Label label) {
        return sqlHelper.executeWithGeneratedKeys("INSERT INTO label (name) VALUES (?)", pstm -> {
            pstm.setString(1, label.getName());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    label.setId(generatedKeys.getInt(1));
                }
            }
            return label;
        });
    }

    @Override
    public Label update(Label label) {
        return sqlHelper.execute("UPDATE label SET name = ? WHERE id = ?", pstm -> {
            pstm.setString(1, label.getName());
            pstm.setInt(2, label.getId());
            pstm.executeUpdate();
            return label;
        });
    }

    @Override
    public void deleteById(Integer id) {
        sqlHelper.execute("DELETE FROM label WHERE id = ?", pstm -> {
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new NotExistCrudException(id);
            }
            return null;
        });
    }
}
