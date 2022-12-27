package com.codestates.backend.pre_project.answer;

import com.codestates.backend.pre_project.helper.AnswerControllerTestHelper;
import com.codestates.backend.pre_project.helper.AnswerStubData;

import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.answer.mapper.AnswerMapper;
import com.codestates.backend.pre_project.post.answer.service.AnswerService;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.response.MultiResponseDto;
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
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class AnswerControllerTest implements AnswerControllerTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private AnswerMapper mapper;

    @Test
    void postAnswerTest() throws Exception {
        //given
        AnswerDto.Post post = (AnswerDto.Post) AnswerStubData.MockAnswer.getRequestBody(HttpMethod.POST);
        AnswerDto.Response responseBody = AnswerStubData.MockAnswer.getSingleResponseBody();

        given(mapper.answerPostDtoToAnswer(Mockito.anyLong(),Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());

        Answer mockResultAnswer = new Answer();
        mockResultAnswer.setAnswerId(1L);
        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(mockResultAnswer);
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(responseBody);

        String content = gson.toJson(post);
        //URI uri = getURI();

        //when
        ResultActions actions =
                mockMvc.perform(postRequestBuilder("/questions/{question-id}/answers/post",1,content));

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.answerBody").value(post.getAnswerBody()))
                .andExpect(jsonPath("$.data.memberId").value(post.getMemberId()))
                .andDo(document("post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(), 
                        requestFields(
                                getDefaultAnswerPostRequestDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultAnswerResponseDescriptors(DataResponseType.SINGLE)
                                )
                        )
                ));
    }

    @Test
    public void patchAnswerTest() throws Exception{
        //given
        Long answerId = 1L;
        AnswerDto.Patch patch = (AnswerDto.Patch) AnswerStubData.MockAnswer.getRequestBody(HttpMethod.PATCH);
        String content = toJsonContent(patch);
        AnswerDto.Response response =
                AnswerStubData.MockAnswer.getSingleResponseBody();

        given(mapper.answerPatchDtoToAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(new Answer());

        given(answerService.updateAnswer(Mockito.any(Answer.class))).willReturn(new Answer());

        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);

        //when
        ResultActions actions = mockMvc.perform(patchRequestBuilder("/answers/{answer-id}/edit",answerId,content));

        //then

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerBody").value(patch.getAnswerBody()))
                .andExpect(jsonPath("$.data.answerId").value(patch.getAnswerId()))
                .andDo(document("patch-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getAnswerRequestPathParameterDescriptor()
                        ),
                        requestFields(getDefaultAnswerPatchRequestDescriptors()),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultAnswerResponseDescriptors(DataResponseType.SINGLE)
                                )
                        )
                ));
    }

    @Test
    public void getAnswerTest() throws Exception{
        long answerId =1L;
        AnswerDto.Response response = AnswerStubData.MockAnswer.getSingleResponseBody();

        given(answerService.findAnswer(Mockito.anyLong())).willReturn(new Answer());
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);

        ResultActions actions = mockMvc.perform(getRequestBuilder("/questions/{answer-id}/answers",answerId));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(answerId))
                .andExpect(jsonPath("$.data.memberId").value(response.getMemberId()))
                .andExpect(jsonPath("$.data.answerBody").value(response.getAnswerBody()))
                .andExpect(jsonPath("$.data.comments").value(response.getComments()))
                .andDo(
                        document("get-answer",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getAnswerRequestPathParameterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(
                                                getDefaultAnswerResponseDescriptors(DataResponseType.SINGLE)
                                        )
                                ))
                );

    }
    @Test
    public void getAnswersTest() throws Exception{
        String page ="1";
        String size ="10";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page",page);
        queryParams.add("size",size);

        Page<Answer> answers = AnswerStubData.MockAnswer.getMultiResultAnswer();
        List<AnswerDto.Response> responses = AnswerStubData.MockAnswer.getMultiResponseBody();

        given(answerService.findAnswers(Mockito.anyInt(), Mockito.anyInt())).willReturn(answers);
        given(mapper.answersToAnswersResponsesDtos(Mockito.anyList())).willReturn(responses);

        ResultActions actions = mockMvc.perform(getRequestBuilder("/answers",queryParams));

        MvcResult result =
                actions
                        .andExpect(status().isOk())
                        .andDo(
                                document(
                                        "get-answers",
                                        getRequestPreProcessor(),
                                        getResponsePreProcessor(),
                                        requestParameters(
                                                getDefaultRequestParameterDescriptors()
                                        ),
                                        PayloadDocumentation.responseFields(
                                                getFullPageResponseDescriptors(
                                                        getDefaultAnswerResponseDescriptors(DataResponseType.LIST)
                                                )
                                        )
                                )
                        ).andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        MatcherAssert.assertThat(list.size(), Matchers.is(2));
    }

    @Test
    public void deleteAnswerTest() throws Exception{
        long answerId = 1L;
        doNothing().when(answerService).deleteAnswer(Mockito.anyLong());

        mockMvc.perform(deleteRequestBuilder("/answers/{answer-id}",answerId))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-answer",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                RequestDocumentation.pathParameters(
                                        getAnswerRequestPathParameterDescriptor()
                                )
                        )
                );
    }
}
