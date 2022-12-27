package com.codestates.backend.pre_project.likes.answerlikes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerLikesDto {
    private long answerId;
    private long memberId;
    private int answerLikesCount;
}
