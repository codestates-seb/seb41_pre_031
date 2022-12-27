package com.codestates.backend.pre_project.helper;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public interface ProfileControllerTestHelper extends ControllerTestHelper {

    String PROFILE_URL = "/profiles";

    String RESOURCE_URI = "/{profile-id}";

    default String getUrl() {
        return PROFILE_URL;
    }

    default String getUri() {
        return PROFILE_URL+RESOURCE_URI;
    }

    default List<ParameterDescriptor> getProfileRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("profile-id").description("프로필 식별자 ID"));
    }

    default List<FieldDescriptor> getDefaultProfileRequestDescriptors() {

        return List.of(
                fieldWithPath("profileId").type(JsonFieldType.NUMBER).description("프로필 식별자").ignored(),
                fieldWithPath("profileTitle").type(JsonFieldType.STRING).description("프로필 제목").optional(),
                fieldWithPath("homepage").type(JsonFieldType.STRING).description("프로필 홈페이지").optional(),
                fieldWithPath("location").type(JsonFieldType.STRING).description("프로필 주소").optional(),
                fieldWithPath("about").type(JsonFieldType.STRING).description("프로필 본문").optional(),
                fieldWithPath("profileView").type(JsonFieldType.NUMBER).description("프로필 조회수").optional()
        );
    }
    default List<FieldDescriptor> getDefaultProfileResponseDescriptors(DataResponseType dataResponseType) {
        String parentPath = getDataParentPath(dataResponseType);
        return List.of(
                fieldWithPath(parentPath.concat("profileId")).type(JsonFieldType.NUMBER).description("프로필 식별자"),
                fieldWithPath(parentPath.concat("profileTitle")).type(JsonFieldType.STRING).description("프로필 제목"),
                fieldWithPath(parentPath.concat("homepage")).type(JsonFieldType.STRING).description("프로필 홈페이지"),
                fieldWithPath(parentPath.concat("location")).type(JsonFieldType.STRING).description("프로필 주소"),
                fieldWithPath(parentPath.concat("about")).type(JsonFieldType.STRING).description("프로필 본문"),
                fieldWithPath(parentPath.concat("profileView")).type(JsonFieldType.NUMBER).description("프로필 조회수")
        );
    }
}
