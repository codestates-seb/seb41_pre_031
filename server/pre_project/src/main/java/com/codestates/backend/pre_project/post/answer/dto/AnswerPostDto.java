package com.codestates.backend.pre_project.post.answer.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Getter
public class AnswerPostDto {
    @NotBlank
    @Range(max= 500)
    private String answerBody;
}
