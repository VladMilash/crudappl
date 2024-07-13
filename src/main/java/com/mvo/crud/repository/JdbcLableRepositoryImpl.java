package com.mvo.crud.repository;

import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.mapper.LableMapper;
import com.mvo.crud.mapper.Mapper;
import com.mvo.crud.model.Lable;
import com.mvo.crud.model.Post;
import com.mvo.crud.repository.dbutil.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcLableRepositoryImpl implements LableRepository {

    private final SqlHelper sqlHelper = new SqlHelper();
    private final LableMapper lableMapper = new LableMapper();

    @Override
    public Lable findById(Integer id) {
        return sqlHelper.execute("SELECT * FROM lable WHERE id = ?", pstm -> {
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
    public List<Lable> findAll() {
        return sqlHelper.execute("SELECT * FROM lable", pstm -> {
            List<Lable> lables = new ArrayList<>();
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    lables.add(lableMapper.map(rs));
                }
                return lables;
            }
        });
    }

    @Override
    public Lable save(Lable lable) {
        return sqlHelper.executeWithGeneratedKeys("INSERT INTO lable (name) VALUES (?)", pstm -> {
            pstm.setString(1, lable.getName());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lable.setId(generatedKeys.getInt(1));
                }
            }
            return lable;
        });
    }

    @Override
    public Lable update(Lable lable) {
        return sqlHelper.execute("UPDATE lable SET name = ? WHERE id = ?", pstm -> {
            pstm.setString(1, lable.getName());
            pstm.setInt(2, lable.getId());
            pstm.executeUpdate();
            return lable;
        });
    }

    @Override
    public void deleteById(Integer id) {
        sqlHelper.execute("DELETE FROM lable WHERE id = ?", pstm -> {
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new NotExistCrudException(id);
            }
            return null;
        });
    }
}
