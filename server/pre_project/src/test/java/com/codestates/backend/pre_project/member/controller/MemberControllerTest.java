package com.codestates.backend.pre_project.member.controller;

import com.codestates.backend.pre_project.member.dto.MemberDto;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.mapper.MemberMapper;
import com.codestates.backend.pre_project.member.repository.MemberRepository;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
        private MemberMapper mapper;

        @Autowired
        private MemberRepository memberRepository;

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
                        .andExpect(jsonPath("$.data.email").value(post.getEmail()));
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

               //when

                //then
        }

        @Test
        void deleteMemberTest() throws Exception {
                //given

                //when

                //then
        }
}
