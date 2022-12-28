package com.codestates.backend.pre_project.question.controller;

import com.codestates.backend.pre_project.helper.QuestionControllerTestHelper;
import com.codestates.backend.pre_project.helper.QuestionStubData;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.mapper.QuestionMapper;
import com.codestates.backend.pre_project.post.question.service.QuestionService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
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
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;

import java.util.List;

import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getResponsePreProcessor;
import static javax.management.openmbean.SimpleType.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class QuestionControllerTest implements QuestionControllerTestHelper{

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
        QuestionDto.Response response = QuestionStubData.MockQuestion.getSingleResponseBody();

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
                .andExpect(jsonPath("$.data.questionTitle").value(post.getQuestionTitle()))
                .andExpect(jsonPath("$.data.questionBody").value(post.getQuestionBody()))
                .andDo(
                        document("post-question",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestFields(
                                        getDefaultQuestionPostRequestDescriptors()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(
                                                getDefaultQuestionResponseDescriptors(DataResponseType.SINGLE)
                                        )
                                ))
                );

    }

    @Test
    public void patchQuestionTest() throws Exception{
        //given
        Long questionId = 1L;
        QuestionDto.Patch patch = (QuestionDto.Patch) QuestionStubData.MockQuestion.getRequestBody(HttpMethod.PATCH);
        String content = toJsonContent(patch);
        QuestionDto.Response response=
                QuestionStubData.MockQuestion.getSingleResponseBody();

        given(mapper.questionPatchDtoToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());

        given(questionService.updateQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(response);



        //when
        ResultActions actions = mockMvc.perform(patchRequestBuilder(getResourceUri(),questionId,content));

        //then

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(patch.getQuestionId()))
                .andExpect(jsonPath("$.data.questionBody").value(patch.getQuestionBody()))
                .andExpect(jsonPath("$.data.questionTitle").value(patch.getQuestionTitle()))
                .andDo(document("patch-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        RequestDocumentation.pathParameters(
                                getQuestionRequestPathParamerterDescriptor()
                        ),
                        requestFields(getDefaultQuestionPatchRequestDescriptors()),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultQuestionResponseDescriptors(DataResponseType.SINGLE)
                                )
                        )
                        ));
    }

    @Test
    public void getQuestionTest() throws Exception{
        long questionId = 1L;
        QuestionDto.Response response = QuestionStubData.MockQuestion.getSingleResponseBody();

        //given
        given(questionService.findQustion(questionId)).willReturn(new Question());
        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(response);

        //when
        ResultActions actions = mockMvc.perform(getRequestBuilder(getResourceUri(),questionId));

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(questionId))
                .andExpect(jsonPath("$.data.questionTitle").value(response.getQuestionTitle()))
                .andExpect(jsonPath("$.data.questionBody").value(response.getQuestionBody()))
                .andDo(
                        MockMvcRestDocumentation.document("get-question",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                RequestDocumentation.pathParameters(
                                        getQuestionRequestPathParamerterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(
                                                getDefaultQuestionResponseDescriptors(DataResponseType.SINGLE))
                                )
                        ));


    }

    @Test
    public void getQuestionsTest() throws Exception{
        String page = "1";
        String size = "10";

        MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
        queryParam.add("page", page);
        queryParam.add("size", size);

        Page<Question> questions= QuestionStubData.MockQuestion.getMultiResultQuestion();
        List<QuestionDto.Response> responses = QuestionStubData.MockQuestion.getMultiResponseBody();

        given(questionService.findQuestions(Mockito.anyInt(),Mockito.anyInt())).willReturn(questions);
        given(mapper.questionToQuestionResponse(Mockito.anyList())).willReturn(responses);

        ResultActions actions = mockMvc.perform(getRequestBuilder(getQuestionUrl(),queryParam ));

        MvcResult result =
                actions
                        .andExpect(status().isOk())
                        .andDo(
                                document("get-questions",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                RequestDocumentation.requestParameters(
                                        getDefaultRequestParameterDescriptors()
                                ),
                                PayloadDocumentation.responseFields(
                                        getFullPageResponseDescriptors(
                                                getDefaultQuestionResponseDescriptors(DataResponseType.LIST))
                                        )
                                )
                        ).andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        MatcherAssert.assertThat(list.size(), Matchers.is(2));
    }

    @Test
    public void deleteQuestionTest() throws Exception{
        long questionId = 1L;
        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());

        mockMvc.perform(deleteRequestBuilder(getResourceUri(), questionId))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-question",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                RequestDocumentation.pathParameters(
                                        getQuestionRequestPathParamerterDescriptor()
                                )
                        )
                );
    }

}
