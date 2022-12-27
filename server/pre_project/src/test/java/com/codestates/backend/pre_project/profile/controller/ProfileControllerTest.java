package com.codestates.backend.pre_project.profile.controller;

import com.codestates.backend.pre_project.helper.ControllerTestHelper;
import com.codestates.backend.pre_project.helper.ProfileStubData;
import com.codestates.backend.pre_project.profile.dto.ProfileDto;
import com.codestates.backend.pre_project.profile.entity.Profile;
import com.codestates.backend.pre_project.profile.profileMapper.ProfileMapper;
import com.codestates.backend.pre_project.profile.service.ProfileService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.BDDMockito.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private ProfileService profileService;

    @MockBean
    private ProfileMapper mapper;

    @Test
    void patchProfileTest() throws Exception {
        //given
        long profileId = 1L;

        ProfileDto.Patch patch = (ProfileDto.Patch) ProfileStubData.MockProfile.getRequestBody(HttpMethod.PATCH);
        ProfileDto.Response response =
                ProfileStubData.MockProfile.getSingleResponseBody(profileId,"나는 슈퍼신찬맨","sc.io","Korea",
                        "만나서 반갑습니다. 개발자로 성공합시다.",1);

        given(mapper.profilePatchDtoToProfile(Mockito.any(ProfileDto.Patch.class))).willReturn(new Profile());

        given(profileService.updateProfile(Mockito.any(Profile.class))).willReturn(new Profile());

        given(mapper.profileToprofileResponseDto(Mockito.any(Profile.class))).willReturn(response);

        String content = gson.toJson(patch);

        //when
//        ResultActions actions = mockMvc.perform()


        //then
    }
}
