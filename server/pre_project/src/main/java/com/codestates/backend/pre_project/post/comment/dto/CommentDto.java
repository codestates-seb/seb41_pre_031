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
        private long memberId;

        @NotBlank
        @Positive
        private long questionId;

        public Member getMember() {
            Member member = new Member();
            member.setMemberId(memberId);
            return member;
        }

        public Question getQuestion() {
            Question question = new Question();
            question.setQuestionId(questionId);
            return question;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AnswerPost {
        @NotBlank
        @Length(max = 500)
        private String commentBody;

        @Positive
        private long memberId;

        @Positive
        private long answerId;


        public Member getMember() {
            Member member = new Member();
            member.setMemberId(memberId);
            return member;
        }

        public Answer getAnswer() {
            Answer answer = new Answer();
            answer.setAnswerId(answerId);
            return answer;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuestionPatch {
        @Length(max = 500)
        private String commentBody;

        private long questionId;

        public void setQuestionId(long questionId) {this.questionId = questionId;}
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AnswerPatch {
        @Length(max = 500)
        private String commentBody;

        private long answerId;

        public void setAnswerId(long answerId) {this.answerId = answerId;}
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private long commentId;
        private long memberId;
        private long answerId;
        private long questionId;
        private String commentBody;
    }
}
