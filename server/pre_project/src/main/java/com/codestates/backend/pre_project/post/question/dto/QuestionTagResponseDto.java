package com.codestates.backend.pre_project.post.question.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestionTagResponseDto {
    private long tagId;

    private long questionId;

    private String tagName;
}
