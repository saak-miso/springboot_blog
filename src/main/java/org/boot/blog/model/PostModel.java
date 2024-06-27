package org.boot.blog.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component("postModel")
public class PostModel {

    private int rowNum;
    private String postUuid;
    private String writeId;
    private String postTitle;
    private String postContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registryDate;
    public PostModel() {
        System.out.println("PostVO 생성자 호출");
    }
}