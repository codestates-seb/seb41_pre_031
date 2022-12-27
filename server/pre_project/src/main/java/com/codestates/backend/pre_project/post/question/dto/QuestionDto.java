package com.codestates.backend.pre_project.post.question.dto;

import com.codestates.backend.pre_project.post.question.QuestionTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class QuestionDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post{

            private String questionTitle;

            private String questionBody;

            private long memberId;

            @Valid
            private List<QuestionTagDto> questionTags;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch{
        private long questionId;

        private long memberId;

        private String questionTitle;

        private String questionBody;

        @Valid
        private List<QuestionTagDto> questionTags;

    }

    @Getter
    @AllArgsConstructor
    @Setter
    public static class Response{
        private long questionId;

        private long memberId;

        private String questionTitle;

        private String questionBody;

        @Valid
        private List<QuestionTagResponseDto> questionTags;

        private LocalDateTime questionRegDate;

        private LocalDateTime questionLastDate;

        private long questionLikes;

    }
}
