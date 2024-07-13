package com.mvo.crud.mapper;

import com.mvo.crud.model.Lable;
import com.mvo.crud.model.Post;
import com.mvo.crud.model.PostStatus;
import com.mvo.crud.model.Writer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostMapper implements Mapper<Post> {
    @Override
    public Post map(ResultSet rs) throws SQLException {
        int postId = rs.getInt("id");
        String content = rs.getString("content");
        LocalDate created = rs.getDate("created").toLocalDate();
        LocalDate updated = rs.getDate("updated").toLocalDate();
        String postStatusString = rs.getString("status");
        PostStatus postStatus = PostStatus.valueOf(postStatusString);

        List<Lable> ladles = new ArrayList<>();

        return new Post(postId, content, created, updated, ladles, postStatus);
    }
}
