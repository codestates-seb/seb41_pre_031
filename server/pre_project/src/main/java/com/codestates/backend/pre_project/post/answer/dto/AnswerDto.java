package com.codestates.backend.pre_project.post.answer.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AnswerDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {
        private long questionId;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        @Length(max = 500)
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
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private long answerId;
        private long questionId;
        private String memberName;
        private String answerBody;
        private int answerLikes;
        private Boolean answerSelected;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

    }
}
