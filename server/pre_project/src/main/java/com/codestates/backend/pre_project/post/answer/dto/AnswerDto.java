package com.codestates.backend.pre_project.post.answer.dto;

import com.codestates.backend.pre_project.post.comment.entity.Comment;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

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
        private String memberName;
        private String answerBody;
        private int answerLikes;
        private boolean answerSelected;
        private LocalDateTime answerRegDate;
        private LocalDateTime answerLastDate;

    }
//
//    public void setComments(List<Comment> comments){
//        this.setComments(comments);
//    }
}
