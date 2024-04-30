package org.boot.blog.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component("boardModel")
public class BoardModel {

    private String uuid;
    private String writeId;
    private String boardTitle;
    private String boardContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registryDate;
    public BoardModel() {
        System.out.println("BoardModel 생성자 호출");
    }
}