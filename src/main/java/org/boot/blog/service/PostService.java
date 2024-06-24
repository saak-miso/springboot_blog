package org.boot.blog.service;

import org.boot.blog.dao.PostDAO;

import java.util.List;

public interface PostService {

    public PostDAO postInfo(PostDAO postDao) throws Exception;

    public int postCount(PostDAO postDao) throws Exception;

    public List<PostDAO> postList(PostDAO postDao, int offset, int limitRow) throws Exception;

    int insertPost(PostDAO postDao) throws Exception;

    int updatePost(PostDAO postDao) throws Exception;

    int deletePost(PostDAO postDao) throws Exception;
}
