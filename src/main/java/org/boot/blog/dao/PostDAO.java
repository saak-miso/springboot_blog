package org.boot.blog.dao;

import org.boot.blog.model.PostModel;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("postDAO")
public interface PostDAO {

    PostModel selectPostInfo(PostModel postModel) throws DataAccessException;

    int selectPostCount(PostModel postModel) throws DataAccessException;

    List<PostModel> selectPostList(PostModel postModel, int offset, int limitRow) throws DataAccessException;

    int insertPost(PostModel postModel) throws Exception;

    int updatePost(PostModel postModel) throws Exception;

    int deletePost(PostModel postModel) throws Exception;
}