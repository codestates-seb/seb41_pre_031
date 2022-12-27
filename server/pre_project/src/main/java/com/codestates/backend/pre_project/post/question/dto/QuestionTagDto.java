package com.codestates.backend.pre_project.post.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionTagDto {

    private long questionId;

    private long tagId;

    private String tagName;
}
