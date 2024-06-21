package org.boot.blog.repository;

import org.apache.ibatis.annotations.Mapper;
import org.boot.blog.model.PostModel;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("postRepository")
public interface PostRepository {

    public PostModel selectPostInfo(PostModel postModel) throws DataAccessException;

    public int selectPostCount(PostModel postModel) throws DataAccessException;

    public List<PostModel> selectPostList(PostModel postModel, int offset, int limitRow) throws DataAccessException;

    int insertPost(PostModel postModel) throws Exception;

    int updatePost(PostModel postModel) throws Exception;

    int deletePost(PostModel postModel) throws Exception;
}
