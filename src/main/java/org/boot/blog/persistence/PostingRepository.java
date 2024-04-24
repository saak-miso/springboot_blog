package org.boot.blog.persistence;

import org.boot.blog.model.PostingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<PostingEntity, String> {

    List<PostingEntity> findByUserId(String memberId);
}