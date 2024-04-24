package org.boot.blog.dto;

import org.boot.blog.model.PostingEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostingDTO {

    private String id;
    private String title;
    private boolean done;

    public PostingDTO(final PostingEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    public static PostingEntity toEntity(final PostingDTO dto) {
        return PostingEntity.builder()
            .id(dto.getId())
            .title(dto.getTitle())
            .done(dto.isDone())
            .build();
    }
}