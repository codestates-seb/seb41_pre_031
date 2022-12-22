package com.codestates.backend.pre_project.member.controller;

import com.codestates.backend.pre_project.helper.StubData;
import com.codestates.backend.pre_project.member.dto.MemberDto;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.mapper.MemberMapper;
import com.codestates.backend.pre_project.member.repository.MemberRepository;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class MemberControllerTest implements MemberControllerTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Test
    void postMemberTest() throws Exception {
        //given
        MemberDto.Post post = (MemberDto.Post) StubData.MockMember.getRequestBody(HttpMethod.POST);
        MemberDto.Response responseBody = StubData.MockMember.getSingleResponseBody();

        given(mapper.memberPostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());

        Member mockResultMember = new Member();
        mockResultMember.setMemberId(1L);
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(mockResultMember);
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseBody);

        String content = gson.toJson(post);
        //URI uri = getURI();

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/members/signup")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

                //then
                actions
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                        .andExpect(jsonPath("$.data.password").value(post.getPassword()))
                        .andExpect(jsonPath("$.data.memberName").value(post.getMemberName()));
        }

    @Test
    void patchMemberTest() throws Exception {
        //given
        long memberId = 1L;

        MemberDto.Patch patch = (MemberDto.Patch) StubData.MockMember.getRequestBody(HttpMethod.PATCH);
        MemberDto.Response response =
                StubData.MockMember.getSingleResponseBody(memberId, null, "김현성", "asdf123");

        given(mapper.memberPatchDtoToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());

        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(new Member());

        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(response);

        String content = gson.toJson(patch);

        //when
        ResultActions actions = mockMvc.perform(patchRequestBuilder(getURI(), memberId, content));

        //then
        actions.andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(patch.getMemberId()))
                .andExpect(jsonPath("$.data.memberName").value(patch.getMemberName()))
                .andExpect(jsonPath("$.data.password").value(patch.getPassword()))
                .andDo(document("patch-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getMemberRequestPathParameterDescriptor()
                        ),
                        requestFields(
                                getDefaultMemberPatchRequestDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultMemberResponseDescriptors(DataResponseType.SINGLE))
                        )
                ));
    }

    @Test
    void getMemberTest() throws Exception {
        //given
        long memberId = 1L;
        Member member = StubData.MockMember.getSingleResultMember(memberId);
        MemberDto.Response response = StubData.MockMember.getSingleResponseBody();

        given(memberService.findMember(Mockito.anyLong())).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(response);

        //when
        ResultActions actions = mockMvc.perform(
                getRequestBuilder(getURI(), memberId));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.memberName").value(member.getMemberName()))
                .andExpect(jsonPath("$.data.password").value(member.getPassword()))
                .andDo(
                        document("get-member",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getMemberRequestPathParameterDescriptor()
                                ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultMemberResponseDescriptors(DataResponseType.SINGLE)
                                )
                        )

                ));

//            // given
//            MemberDto.Post post = new MemberDto.Post("vdcf2000@gmail.com",
//                    "asdf123","강신찬");
//            Member member = mapper.memberPostDtoToMember(post);
//
//
//            Member resultMember = memberRepository.save(member);
//
//            long memberId = resultMember.getMemberId();
//
//            // when / then
//            mockMvc.perform(
//                            get("/members/{memberId}",memberId)
//                                    .accept(MediaType.APPLICATION_JSON)
//                    )
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.data.email").value(resultMember.getEmail()))
//                    .andExpect(jsonPath("$.data.memberName").value(resultMember.getMemberName()));
//

    }

    @Test
    void getMembersTest() throws Exception {
        //give
        Page<Member> pageMembers = StubData.MockMember.getMultiResultMember();

        List<MemberDto.Response> responses = StubData.MockMember.getMultiResponseBody();

        given(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageMembers);
        given(mapper.membersToMemberResponses(Mockito.anyList())).willReturn(responses);

        String page = "1";
        String size = "10";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);


        //when
        ResultActions actions = mockMvc.perform(getRequestBuilder(getUrl(),queryParams));

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        System.out.println(list);
        MatcherAssert.assertThat(list.size(), is(2));

        //given
//            MemberDto.Post post1 = new MemberDto.Post("abcd1@gmail.com", "qhdks!1234", "강신찬");
//            MemberDto.Post post2 = new MemberDto.Post("abcd2@gmail.com", "qhdks!1234", "김정희");
//            MemberDto.Post post3 = new MemberDto.Post("abcd3@gmail.com", "qhdks!1234", "김현성");
//
//            Member member1 = mapper.memberPostDtoToMember(post1);
//            Member member2 = mapper.memberPostDtoToMember(post2);
//            Member member3 = mapper.memberPostDtoToMember(post3);
//
//            Member resultMember1 = memberRepository.save(member1);
//            Member resultMember2 = memberRepository.save(member2);
//            Member resultMember3 = memberRepository.save(member3);
//
//
//               //when
//        ResultActions actions =
//                mockMvc.perform(
//                        get("/members")
//                                .param("page","1")
//                                .param("size","10")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                );
//
//                //then
//        MvcResult result = actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray())
//                .andReturn();
//        String content = result.getResponse().getContentAsString();
//        System.out.println(content);
//        System.out.println("*******************************");
    }

    @Test
    void deleteMemberTest() throws Exception {
        //given
        long memberId = 1L;
        doNothing().when(memberService).deleteMember(memberId);
        //when
        ResultActions actions = mockMvc.perform(deleteRequestBuilder(getURI(), memberId));
        //then
        actions.andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-member",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getMemberRequestPathParameterDescriptor()
                                )
                        )
                );

        //given
//            MemberDto.Post post = new MemberDto.Post("vdcf2000@gmail.com",
//                    "asdf123","강신찬");
//            Member member = mapper.memberPostDtoToMember(post);
//            Member resultMember1 = memberRepository.save(member);
//
//            long memberId = memberRepository.findByEmail("vdcf2000@gmail.com").get().getMemberId();
//
//                //when
//            ResultActions actions =
//                    mockMvc.perform(
//                    delete("/members/" + memberId)
//                            .accept(MediaType.APPLICATION_JSON)
//                            .contentType(MediaType.APPLICATION_JSON)
//            );
//
//                //then
//            MvcResult result = actions
//                    .andExpect(status().isNoContent())
//                    .andExpect(jsonPath("$").doesNotExist())
//                    .andReturn();

        }
}
