package org.boot.blog.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("postVO")
public class PostVO {

    private int rowNum;
    private String postUuid;
    private String writeId;
    private String postTitle;
    private String postContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registryDate;
    public PostVO() {
        System.out.println("PostModel 생성자 호출");
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getPostUuid() {
        return postUuid;
    }

    public void setPostUuid(String postUuid) {
        this.postUuid = postUuid;
    }

    public String getWriteId() {
        return writeId;
    }

    public void setWriteId(String writeId) {
        this.writeId = writeId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }
}