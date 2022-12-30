package com.codestates.backend.pre_project.post.comment.dto;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;


public class CommentDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuestionPost {
        @NotBlank
        @Length(max = 500)
        private String commentBody;

        @NotBlank
        @Positive
        private long questionId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AnswerPost {
        @NotBlank
        @Length(max = 500)
        private String commentBody;

        @NotBlank
        @Positive
        private long answerId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuestionPatch {
        @Length(max = 500)
        private String commentBody;
        private long questionId;
        private long commentId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AnswerPatch {
        @Length(max = 500)
        private String commentBody;
        private long answerId;
        private long commentId;
    }


    @Data
    @AllArgsConstructor
    public static class QuestionResponse {
        private long commentId;
        private long questionId;
        private String memberName;
        private String commentBody;
        private LocalDateTime commentRegDate;
    }

    @Data
    @AllArgsConstructor
    public static class AnswerResponse {
        private long commentId;
        private long answerId;
        private String memberName;
        private String commentBody;
        private LocalDateTime commentRegDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private long commentId;
        private long answerId;
        private long questionId;
        private String memberName;
        private String commentBody;
        private LocalDateTime commentRegDate;
    }
}
