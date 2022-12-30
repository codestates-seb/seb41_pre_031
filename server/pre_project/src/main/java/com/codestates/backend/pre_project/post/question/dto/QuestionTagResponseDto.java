package com.codestates.backend.pre_project.post.question.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionTagResponseDto {
    private long questionTagId;
    private String tagName;
}
