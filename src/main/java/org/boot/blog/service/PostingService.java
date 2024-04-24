package org.boot.blog.service;

import org.boot.blog.model.PostingEntity;
import org.boot.blog.persistence.PostingRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostingService {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);

    @Autowired
    private PostingRepository repository;

    public String testService() {

        // BlogEntity 생성
        PostingEntity postingEntity = PostingEntity.builder().title("My first todo item").build();

        // BlogEntity 저장
        repository.save(postingEntity);

        // BlogEntity 검색
        PostingEntity savedEntity = repository.findById(postingEntity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<PostingEntity> create(final PostingEntity postingEntity) {

        // Validations
        validate(postingEntity);

        repository.save(postingEntity);
        logger.info("Entity Id : {} is saved.", postingEntity.getId());
        return repository.findByUserId(postingEntity.getUserId());
    }

    private void validate(final PostingEntity postingEntity) {

        if(postingEntity == null) {
            logger.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(postingEntity.getUserId() == null) {
            logger.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

    public List<PostingEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }
    
    // 업데이트는 이걸로 하지 않는 방식으로 변경
    public List<PostingEntity> update(final PostingEntity postingEntity) {

        // (1) 저장 할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create Todo에서 구현했다.
        validate(postingEntity);

        // (2) 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트 할 수 없기 때문이다.
        final Optional<PostingEntity> original = repository.findById(postingEntity.getId());

        original.ifPresent(blog -> {

            // (3) 반환된 BlogEntity가 존재하면 값을 새 blogEntity의 값으로 덮어 씌운다.
            blog.setTitle(postingEntity.getTitle());
            blog.setDone(postingEntity.isDone());

            // (4) 데이터베이스에 새 값을 저장한다.
            repository.save(blog);
        });

        // 2.3.2 Retrieve Blog 에서 만든 메서드를 이용해 유저의 모든 Blog 리스트를 리턴한다.
        return retrieve(postingEntity.getUserId());
    }


    public List<PostingEntity> delete(final PostingEntity postingEntity) {

        // (1) 저장 할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create Todo에서 구현했다.
        validate(postingEntity);

        try {

            // (2) 엔티티를 삭제한다.
            repository.delete(postingEntity);

        } catch (Exception e) {

            // (3) exception 발생시 id와 exception을 로깅한다.
            logger.error("error deleting entity ", postingEntity.getId(), e);

            // (4) 컨트롤러로 exception을 날린다. 데이터베이스 내부 로직을 캡슐화 하기 위해 e를 리턴하지 않고 새 exception 오브젝트를 리턴한다.
            throw new RuntimeException("error deleting entity " + postingEntity.getId());
        }

        // (5) 새 Todo리스트를 가져와 리턴한다.
        return retrieve(postingEntity.getUserId());
    }
}