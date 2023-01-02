package com.codestates.backend.pre_project.post.question.dto;

import com.codestates.backend.pre_project.post.question.QuestionTag;
import lombok.*;

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

            @Valid
            private List<QuestionTag> questionTags;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch{
        private long questionId;

        private String questionTitle;

        private String questionBody;

        @Valid
        private List<QuestionTag> questionTags;

    }

    @Getter
    @AllArgsConstructor
    @Setter
    @Builder
    public static class Response{
        private long questionId;

        private String memberName;

        private String questionTitle;

        private String questionBody;

        @Valid
        private List<QuestionTagResponseDto> questionTags;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        private long questionLikes;

        private long questionView;

        private Boolean isSelectAnswer;

    }
}
