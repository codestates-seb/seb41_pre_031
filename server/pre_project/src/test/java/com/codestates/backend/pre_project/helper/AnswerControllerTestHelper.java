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
        return Arrays.asList(parameterWithName("answer-id").description("답변 식별"));
    }

    default List<FieldDescriptor> getDefaultMemberPostRequestDescriptors() {

        return List.of(
                fieldWithPath("answerBody").type(JsonFieldType.STRING).description("답변 본문")
//                fieldWithPath("memberName").type(JsonFieldType.STRING).description("이름")
        );
    }
}
