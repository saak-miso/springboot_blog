package org.boot.blog.service;

import org.boot.blog.model.PostModel;

import java.util.List;

public interface PostService {

    PostModel postInfo(PostModel postModel) throws Exception;

    int postCount(PostModel postModel) throws Exception;

    List<PostModel> postList(PostModel postModel, int offset, int limitRow) throws Exception;

    int insertPost(PostModel postModel) throws Exception;

    int updatePost(PostModel postModel) throws Exception;

    int deletePost(PostModel postModel) throws Exception;
}