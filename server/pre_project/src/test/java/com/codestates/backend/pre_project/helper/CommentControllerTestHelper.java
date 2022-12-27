package com.codestates.backend.pre_project.helper;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public interface CommentControllerTestHelper extends ControllerTestHelper{
    String COMMENT_ANSWER_URL = "/answers/comments";
    String COMMENT_QUESTION_URL = "/questions/comments";
    String RESOURCE_ANSWER_URI = "/{answer-id}";
    String RESOURCE_QUESTION_URI = "/{question-id}";

    default String getCommentAnswerUrl() {return COMMENT_ANSWER_URL;}
    default String getCommentQuestionUrl() {return COMMENT_QUESTION_URL;}

    default String getResourceAnswerUri() {return COMMENT_ANSWER_URL + RESOURCE_ANSWER_URI;}
    default String getResourceQuestionUri() {return COMMENT_QUESTION_URL + RESOURCE_QUESTION_URI;}

    default List<ParameterDescriptor> getCommentRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("comment-id").description("댓글 식별자 ID"));
    }

    default List<ParameterDescriptor> getCommentAnswerRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("answer-id").description("댓글의 answer 식별자 ID"));
    }

    default List<ParameterDescriptor> getCommentQuestionRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("question-id").description("댓글의 question 식별자 ID"));
    }

    default List<FieldDescriptor> getDefaultCommentAnswerPostRequestDescriptors() {
        return List.of(
                fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("댓글의 answer 식별 ID"),
                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("댓글의 member 식별 ID"),
                fieldWithPath("commentBody").type(JsonFieldType.STRING).description("댓글 본문 내용")
        );
    }

    default List<FieldDescriptor> getDefaultCommentQuestionPostRequestDescriptors() {
        return List.of(
                fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("댓글의 question 식별 ID"),
                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("댓글의 member 식별 ID"),
                fieldWithPath("commentBody").type(JsonFieldType.STRING).description("댓글 본문 내용")
        );
    }

    default List<FieldDescriptor> getDefaultCommentAnswerPatchRequestDescriptors() {
        return List.of(
                fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("댓글의 answer 식별 ID"),
                fieldWithPath("commentBody").type(JsonFieldType.STRING).description("댓글 본문 내용")
        );
    }

    default List<FieldDescriptor> getDefaultCommentQuestionPatchRequestDescriptors() {
        return List.of(
                fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("댓글의 question 식별 ID"),
                fieldWithPath("commentBody").type(JsonFieldType.STRING).description("댓글 본문 내용")
        );
    }


    default List<FieldDescriptor> getDefaultCommentResponseDescriptors(DataResponseType dataResponseType) {
        String parentPath = getDataParentPath(dataResponseType);

        return List.of(
                fieldWithPath(parentPath.concat("commentId")).type(JsonFieldType.NUMBER).description("댓글 식별자"),
                fieldWithPath(parentPath.concat("memberId")).type(JsonFieldType.NUMBER).description("댓글의 member 식별 ID"),
                fieldWithPath(parentPath.concat("answerId")).type(JsonFieldType.NUMBER).description("댓글의 answer 식별 ID"),
                fieldWithPath(parentPath.concat("questionId")).type(JsonFieldType.NUMBER).description("댓글의 question 식별 ID"),
                fieldWithPath(parentPath.concat("commentBody")).type(JsonFieldType.STRING).description("댓글 본문 내용")
        );
    }
}
