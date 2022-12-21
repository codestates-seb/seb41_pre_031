package com.codestates.backend.pre_project.post.comment.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentPostDto {
    @NotBlank
    @Range(max= 500)
    private String commentBody;
}
