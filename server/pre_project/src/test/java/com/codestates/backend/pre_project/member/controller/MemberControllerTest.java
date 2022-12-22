package com.codestates.backend.pre_project.member.controller;

import com.codestates.backend.pre_project.member.dto.MemberDto;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.mapper.MemberMapper;
import com.codestates.backend.pre_project.member.repository.MemberRepository;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

import java.net.URI;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private Gson gson;

        @Autowired
        private MemberService memberService;

        @Autowired
        private MemberRepository memberRepository;

        @Autowired
        private MemberMapper mapper;

        @Test
        void postMemberTest() throws Exception {
                //given
                MemberDto.Post post = new MemberDto.Post("vdcf2000@gmail.com",
                        "asdf123","강신찬");
                
                String content = gson.toJson(post);


                //when
                ResultActions actions =
                        mockMvc.perform (
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
                MemberDto.Post post = new MemberDto.Post("abcd@gmail.com", "qhdks!1234", "");


                //when

                //then
        }


        @Test
        void getMemberTest() throws Exception {
            // given
            MemberDto.Post post = new MemberDto.Post("vdcf2000@gmail.com",
                    "asdf123","강신찬");
            Member member = mapper.memberPostDtoToMember(post);


            Member resultMember = memberRepository.save(member);

            long memberId = resultMember.getMemberId();

            // when / then
            mockMvc.perform(
                            get("/members/{memberId}",memberId)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.email").value(resultMember.getEmail()))
                    .andExpect(jsonPath("$.data.memberName").value(resultMember.getMemberName()));


        }

    @Test
        void getMembersTest() throws Exception {
                //given
            MemberDto.Post post1 = new MemberDto.Post("abcd1@gmail.com", "qhdks!1234", "강신찬");
            MemberDto.Post post2 = new MemberDto.Post("abcd2@gmail.com", "qhdks!1234", "김정희");
            MemberDto.Post post3 = new MemberDto.Post("abcd3@gmail.com", "qhdks!1234", "김현성");

            Member member1 = mapper.memberPostDtoToMember(post1);
            Member member2 = mapper.memberPostDtoToMember(post2);
            Member member3 = mapper.memberPostDtoToMember(post3);

            Member resultMember1 = memberRepository.save(member1);
            Member resultMember2 = memberRepository.save(member2);
            Member resultMember3 = memberRepository.save(member3);


               //when
        ResultActions actions =
                mockMvc.perform(
                        get("/members")
                                .param("page","1")
                                .param("size","10")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );

                //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        System.out.println("*******************************");
        }

        @Test
        void deleteMemberTest() throws Exception {
                //given
            MemberDto.Post post = new MemberDto.Post("vdcf2000@gmail.com",
                    "asdf123","강신찬");
            Member member = mapper.memberPostDtoToMember(post);
            Member resultMember1 = memberRepository.save(member);

            long memberId = memberRepository.findByEmail("vdcf2000@gmail.com").get().getMemberId();

                //when
            ResultActions actions =
                    mockMvc.perform(
                    delete("/members/" + memberId)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            );

                //then
            MvcResult result = actions
                    .andExpect(status().isNoContent())
                    .andExpect(jsonPath("$").doesNotExist())
                    .andReturn();

        }
}
