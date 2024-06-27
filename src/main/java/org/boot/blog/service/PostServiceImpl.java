package org.boot.blog.service;

import org.boot.blog.model.PostModel;
import org.boot.blog.dao.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("postService")
@Transactional(propagation = Propagation.REQUIRED)
public class PostServiceImpl implements PostService {

    @Autowired
    @Qualifier("postDAO")
    private PostDAO postDAO;

    @Override
    public PostModel postInfo(PostModel postModel) throws Exception {
        return postDAO.selectPostInfo(postModel);
    }

    @Override
    public int postCount(PostModel postModel) throws Exception {
        return postDAO.selectPostCount(postModel);
    }

    @Override
    public List<PostModel> postList(PostModel postModel, int offset, int limitRow) throws Exception {
        return postDAO.selectPostList(postModel, offset, limitRow);
    }

    @Override
    public int insertPost(PostModel postModel) throws Exception {
        return postDAO.insertPost(postModel);
    }

    @Override
    public int updatePost(PostModel postModel) throws Exception {
        return postDAO.updatePost(postModel);
    }

    @Override
    public int deletePost(PostModel postModel) throws Exception {
        return postDAO.deletePost(postModel);
    }
}