package com.codestates.backend.pre_project.post.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

public class AnswerDto {

    @Getter
    @Setter
    public static class Post {
        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        @Range(max= 500)
        private String answerBody;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {
        private long answerId;

        private String answerBody;

        public void setAnswerId(long answerId) {
            this.answerId = answerId;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long answerId;
        private String answerBody;
        private String memberName;
        private String commentBody;
//        private int answerLikes;
    }
}
