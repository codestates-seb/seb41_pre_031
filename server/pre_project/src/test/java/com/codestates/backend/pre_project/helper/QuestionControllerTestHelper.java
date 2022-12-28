package com.codestates.backend.pre_project.helper;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.RequestDocumentation;

import java.util.Arrays;
import java.util.List;

import static javax.management.openmbean.SimpleType.LONG;
import static javax.management.openmbean.SimpleType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public interface QuestionControllerTestHelper extends ControllerTestHelper{

    String QUESTION_URL = "/questions";

    String RESOURCE_URI = "/{question-id}";

    default String getQuestionUrl() { return QUESTION_URL;}
    default String getResourceUri() { return QUESTION_URL + RESOURCE_URI;}

    default List<ParameterDescriptor> getQuestionRequestPathParamerterDescriptor(){
        return Arrays.asList(RequestDocumentation.parameterWithName("question-id").description("질문 식별자 ID"));
    }
    
    default List<FieldDescriptor> getDefaultQuestionPostRequestDescriptors() {
        return List.of(
                fieldWithPath("memberId").type(LONG).description("멤버 아이디"),
                fieldWithPath("questionTitle").type(STRING).description("질문 제목"),
                fieldWithPath("questionBody").type(STRING).description("질문 내용"),
                fieldWithPath("questionTags").type(JsonFieldType.ARRAY).description("태그").optional(),
                fieldWithPath("questionTags.[].questionId").type(JsonFieldType.NUMBER).description("태그의 질문 식별 ID").optional(),
                fieldWithPath("questionTags.[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 ID").optional(),
                fieldWithPath("questionTags.[].tagName").type(JsonFieldType.STRING).description("태그 제목").optional()
        );
    }
    
    default List<FieldDescriptor> getDefaultQuestionPatchRequestDescriptors(){
        return List.of(
                fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자 ID").ignored(),
                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("질문 작성자 식별 ID").ignored(),
                fieldWithPath("questionTitle").type(JsonFieldType.STRING).description("질문 제목").optional(),
                fieldWithPath("questionBody").type(JsonFieldType.STRING).description("질문 내용").optional(),
                fieldWithPath("questionTags").type(JsonFieldType.ARRAY).description("질문 태그")
        );
    }
    
    default List<FieldDescriptor> getDefaultQuestionResponseDescriptors(DataResponseType dataResponseType) {
        String parentPath = getDataParentPath(dataResponseType);
        return List.of(
                fieldWithPath(parentPath.concat("questionId")).type(JsonFieldType.NUMBER).description("질문 식별자 ID"),
                fieldWithPath(parentPath.concat("memberId")).type(JsonFieldType.NUMBER).description("질문 작성자 식별 ID"),
                fieldWithPath(parentPath.concat("questionTitle")).type(JsonFieldType.STRING).description("질문 제목"),
                fieldWithPath(parentPath.concat("questionBody")).type(JsonFieldType.STRING).description("질문 내용"),
                fieldWithPath(parentPath.concat("questionTags")).type(JsonFieldType.ARRAY).description("태그"),
                fieldWithPath(parentPath.concat("questionTags.[].questionId")).type(JsonFieldType.NUMBER).description("태그의 질문 식별 ID").optional(),
                fieldWithPath(parentPath.concat("questionTags.[].tagId")).type(JsonFieldType.NUMBER).description("태그 식별 ID").optional(),
                fieldWithPath(parentPath.concat("questionTags.[].tagName")).type(JsonFieldType.STRING).description("태그 제목").optional(),
                fieldWithPath(parentPath.concat("questionRegDate")).type(JsonFieldType.STRING).description("질문 작성 시간"),
                fieldWithPath(parentPath.concat("questionLastDate")).type(JsonFieldType.STRING).description("질문 마지막 작성 시간"),
                fieldWithPath(parentPath.concat("questionLikes")).type(JsonFieldType.NUMBER).description("비밀 번호")
        );
    }
}
