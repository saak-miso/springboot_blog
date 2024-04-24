package org.boot.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.boot.blog.dto.PostingDTO;
import org.boot.blog.dto.ResponseDTO;
import org.boot.blog.model.PostingEntity;
import org.boot.blog.service.PostingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("posting")
public class PostingController {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);

    @Autowired
    private PostingService postingService;

    @PostMapping("/createPosting")
    public ResponseEntity<ResponseDTO<PostingDTO>> createPosting(HttpServletRequest request) {

        try {

            String temporaryUserId = "temporary-user"; // temporary user id.

            PostingDTO postingDTO = new PostingDTO();
            postingDTO.setTitle(request.getParameter("title"));

            // (1) BlogEntity 변환한다.
            PostingEntity postingEntity = PostingDTO.toEntity(postingDTO);

            // (2) id를 null로 초기화 한다. 생성 당시에는 id가 없어야 하기 때문이다.
            postingEntity.setId(null);

            // (3) 임시 유저 아이디를 설정 해 준다. 이 부분은 4장 인증과 인가에서 수정 할 예정이다. 지금은 인증과 인가 기능이 없으므로 한 유저(temporary-user)만 로그인 없이 사용 가능한 어플리케이션인 셈이다
            postingEntity.setUserId(temporaryUserId);

            // (4) 서비스를 이용해 Blog엔티티를 생성한다.
            List<PostingEntity> entities = postingService.create(postingEntity);

            // (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BlogDTO리스트로 변환한다.
            List<PostingDTO> postingDTOs = entities.stream().map(PostingDTO::new).collect(Collectors.toList());

            // (6) 변환된 BlogDTO 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<PostingDTO> response = ResponseDTO.<PostingDTO>builder().data(postingDTOs).build();

            // (7) ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {

            // (8) 혹시 예외가 나는 경우 dto대신 error에 메시지를 넣어 리턴한다.
            String error = e.getMessage();
            ResponseDTO<PostingDTO> response = ResponseDTO.<PostingDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("retrievePostingList")
    public ResponseEntity<ResponseDTO<PostingDTO>> retrievePostingList() {

        logger.info("/posting/retrievePostingList");

        String temporaryUserId = "temporary-user"; // temporary user id.

        // (1) 서비스 메서드의 retrieve메서드를 사용해 Blog리스트를 가져온다
        List<PostingEntity> entities = postingService.retrieve(temporaryUserId);

        // (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BlogDTO리스트로 변환한다.
        List<PostingDTO> postingDTOs = entities.stream().map(PostingDTO::new).collect(Collectors.toList());

        // (6) 변환된 BlogDTO리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<PostingDTO> response = ResponseDTO.<PostingDTO>builder().data(postingDTOs).build();

        // (7) ResponseDTO를 리턴한다.
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("updatePosting")
    public ResponseEntity<ResponseDTO<PostingDTO>> updatePosting(@RequestBody PostingDTO postingDto) {

        String temporaryUserId = "temporary-user"; // temporary user id.

        // (1) dto를 entity로 변환한다.
        PostingEntity PostingEntity = PostingDTO.toEntity(postingDto);

        // (2) id를 temporaryUserId로 초기화 한다. 여기는 4장 인증과 인가에서 수정 할 예정이다.
        PostingEntity.setUserId(temporaryUserId);

        // (3) 서비스를 이용해 entity를 업데이트 한다.
        List<PostingEntity> entities = postingService.update(PostingEntity);

        // (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BlogDTO리스트로 변환한다.
        List<PostingDTO> postingDTOs = entities.stream().map(PostingDTO::new).collect(Collectors.toList());

        // (5) 변환된 BlogDTO리스트를 이용해  ResponseDTO를 초기화한다.
        ResponseDTO<PostingDTO> response = ResponseDTO.<PostingDTO>builder().data(postingDTOs).build();

        // (6) ResponseDTO를 리턴한다.
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("deletePosting")
    public ResponseEntity<ResponseDTO<PostingDTO>> deletePosting(@RequestBody PostingDTO dto) {

        try {
            String temporaryUserId = "temporary-user"; // temporary user id.

            // (1) BlogEntity로 변환한다.
            PostingEntity postingEntity = PostingDTO.toEntity(dto);

            // (2) 임시 유저 아이디를 설정 해 준다. 이 부분은 4장 인증과 인가에서 수정 할 예정이다. 지금은 인증과 인가 기능이 없으므로 한 유저(temporary-user)만 로그인 없이 사용 가능한 어플리케이션인 셈이다
            postingEntity.setUserId(temporaryUserId);

            // (3) 서비스를 이용해 entity를 삭제 한다.
            List<PostingEntity> entities = postingService.delete(postingEntity);

            // (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BlogDTO리스트로 변환한다.
            List<PostingDTO> postingDTOS = entities.stream().map(PostingDTO::new).collect(Collectors.toList());

            // (5) 변환된 BlogDTO리스트를 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<PostingDTO> response = ResponseDTO.<PostingDTO>builder().data(postingDTOS).build();

            // (6) ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {

            // (8) 혹시 예외가 나는 경우 dto대신 error에 메시지를 넣어 리턴한다.
            String error = e.getMessage();
            ResponseDTO<PostingDTO> response = ResponseDTO.<PostingDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}