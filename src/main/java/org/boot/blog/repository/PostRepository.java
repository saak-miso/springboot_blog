package org.boot.blog.repository;

import org.apache.ibatis.annotations.Mapper;
import org.boot.blog.dao.PostDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("postRepository")
public interface PostRepository {

    public PostDAO selectPostInfo(PostDAO postModel) throws DataAccessException;

    public int selectPostCount(PostDAO postModel) throws DataAccessException;

    public List<PostDAO> selectPostList(PostDAO postModel, int offset, int limitRow) throws DataAccessException;

    int insertPost(PostDAO postModel) throws Exception;

    int updatePost(PostDAO postModel) throws Exception;

    int deletePost(PostDAO postModel) throws Exception;
}
