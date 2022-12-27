package com.codestates.backend.pre_project.helper;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;



import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface AnswerControllerTestHelper extends ControllerTestHelper {
    String ANSWER_URL = "/answers";
    String RESOURCE_URI = "/{answer-id}";

    default String getUrl() {
        return ANSWER_URL;
    }
    default String getURI() {
        return ANSWER_URL + RESOURCE_URI;
    }

    default List<ParameterDescriptor> getAnswerRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("answer-id").description("답변 식별자 ID"));
    }

    default List<FieldDescriptor> getDefaultAnswerPostRequestDescriptors() {

        return List.of(
                fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별ID"),
                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("답변의 작성자 식별ID"),
                fieldWithPath("answerBody").type(JsonFieldType.STRING).description("답변 본문")
        );
    }

    default List<FieldDescriptor> getDefaultAnswerPatchRequestDescriptors(){
        return List.of(
                fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변의 질문 식별ID"),
                fieldWithPath("answerBody").type(JsonFieldType.STRING).description("답변 본문")
        );
    }

    default List<FieldDescriptor> getDefaultAnswerResponseDescriptors(DataResponseType dataResponseType) {
        String parentPath = getDataParentPath(dataResponseType);

        return List.of(
                fieldWithPath(parentPath.concat("answerId")).type(JsonFieldType.NUMBER).description("질문 식별 ID"),
                fieldWithPath(parentPath.concat("memberId")).type(JsonFieldType.NUMBER).description("멤버 식별 ID"),
                fieldWithPath(parentPath.concat("answerBody")).type(JsonFieldType.STRING).description("질문 식별 ID"),
                fieldWithPath(parentPath.concat("comments")).type(JsonFieldType.ARRAY).description("답변에 달린 코멘트들"),
                fieldWithPath(parentPath.concat("answerLikes")).type(JsonFieldType.NUMBER).description("질문 좋아요 수")

        );
    }
}
