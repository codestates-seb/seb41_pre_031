package com.codestates.backend.pre_project.likes.answerlikes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerLikesPatchDto {
    private long answerId;
    private long memberId;
    private int answerLikesCount;
}
