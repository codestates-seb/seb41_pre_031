package com.codestates.backend.pre_project.question.controller;

import com.codestates.backend.pre_project.helper.QuestionStubData;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.mapper.QuestionMapper;
import com.codestates.backend.pre_project.post.question.service.QuestionService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import java.util.List;

import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getResponsePreProcessor;
import static javax.management.openmbean.SimpleType.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class QuestionControllerTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper mapper;

    @Test
    void postQuestionTest() throws Exception{
        QuestionDto.Post post = (QuestionDto.Post) QuestionStubData.MockQuestion.getRequestBody(HttpMethod.POST);
        QuestionDto.Response response = (QuestionDto.Response) QuestionStubData.MockQuestion.getMultiResponseBody();

        given(mapper.questionPostDtoToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        Question mockResultQuestion = new Question();
        mockResultQuestion.setQuestionId(1L);
        given(questionService.createQuestion(Mockito.any(Question.class))).willReturn(mockResultQuestion);
        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(response);

        String content = gson.toJson(post);

        ResultActions actions =
                mockMvc.perform(
                        MockMvcRequestBuilders.post("/questions")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.questionTitle").value(post.getQustionTitle()))
                .andExpect(jsonPath("$.data.questionBody").value(post.getQuestionBody()))
                .andDo(
                        document("post-question",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestFields(
                                        List.of(
                                            fieldWithPath("memberId").type(LONG).description("멤버 아이디"),
                                            fieldWithPath("questionTitle").type(STRING).description("질문 제목"),
                                                fieldWithPath("questionBody").type(STRING).description("질문 내용"),
                                                fieldWithPath("questionTags").type(OBJECTNAME).description("태그")
                                        )
                                ))
                );

    }

}
