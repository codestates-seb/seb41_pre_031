package com.codestates.backend.pre_project.post.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class QuestionTagResponseDto {
    private long tagId;

    private long questionId;

    private String tagName;
}
