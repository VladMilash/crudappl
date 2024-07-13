package com.mvo.crud.repository;

import com.mvo.crud.exception.NotExistCrudException;
import com.mvo.crud.mapper.PostMapper;
import com.mvo.crud.model.Post;
import com.mvo.crud.repository.dbutil.SqlHelper;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {

    SqlHelper sqlHelper = new SqlHelper();
    PostMapper postMapper = new PostMapper();

    @Override
    public Post findById(Integer id) {
        return sqlHelper.execute("SELECT * FROM post WHERE id = ?", pstm -> {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (!rs.next()) {
                    throw new NotExistCrudException(id);
                }
                return postMapper.map(rs);
            }
        });
    }

    @Override
    public List<Post> findAll() {
        return sqlHelper.execute("SELECT * FROM post", pstm -> {
            List<Post> posts = new ArrayList<>();
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    posts.add(postMapper.map(rs));
                    return posts;
                }
            }
            return posts;
        });
    }

    @Override
    public Post save(Post post) {
        return sqlHelper.executeWithGeneratedKeys("INSERT INTO post (content, created, updated, status) VALUES (?, ?, ?, ?)", pstm -> {
            pstm.setString(1, post.getContent());
            pstm.setDate(2, java.sql.Date.valueOf(post.getCreated()));
            pstm.setDate(3, java.sql.Date.valueOf(post.getUpdated()));
            pstm.setString(4, post.getPostStatus().name());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                }
            }
            return post;
        });
    }

    @Override
    public Post update(Post post) {
        return sqlHelper.execute("UPDATE post SET content = ?, updated = ?, status = ? WHERE id = ?", pstm -> {
            pstm.setString(1, post.getContent());
            pstm.setDate(2, java.sql.Date.valueOf(post.getUpdated()));
            pstm.setString(3, post.getPostStatus().name());
            pstm.setInt(4, post.getId());
            pstm.executeUpdate();
            return post;
        });
    }

    @Override
    public void deleteById(Integer id) {
        sqlHelper.execute("DELETE FROM post WHERE id = ?", pstm -> {
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new NotExistCrudException(id);
            }
            return null;
        });
    }

}
