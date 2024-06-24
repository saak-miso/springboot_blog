package org.boot.blog.dao;


// 패키지 - Entity로 변경

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component("postDAO")
public class PostDAO {

    private int rowNum;
    private String postUuid;
    private String writeId;
    private String postTitle;
    private String postContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registryDate;
    public PostDAO() {
        System.out.println("PostDAO 생성자 호출");
    }
}