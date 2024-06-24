package org.boot.blog.service;

import org.boot.blog.dao.PostDAO;
import org.boot.blog.repository.PostRepository;
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
    @Qualifier("postRepository")
    private PostRepository postRepository;

    @Override
    public PostDAO postInfo(PostDAO postDao) throws Exception {
        return postRepository.selectPostInfo(postDao);
    }

    @Override
    public int postCount(PostDAO postDao) throws Exception {
        return postRepository.selectPostCount(postDao);
    }

    @Override
    public List<PostDAO> postList(PostDAO postDao, int offset, int limitRow) throws Exception {
        return postRepository.selectPostList(postDao, offset, limitRow);
    }

    @Override
    public int insertPost(PostDAO postDao) throws Exception {
        return postRepository.insertPost(postDao);
    }

    @Override
    public int updatePost(PostDAO postDao) throws Exception {
        return postRepository.updatePost(postDao);
    }

    @Override
    public int deletePost(PostDAO postDao) throws Exception {
        return postRepository.deletePost(postDao);
    }
}