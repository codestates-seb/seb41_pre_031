package com.codestates.backend.pre_project.answer;

import com.codestates.backend.pre_project.helper.AnswerControllerTestHelper;
import com.codestates.backend.pre_project.helper.AnswerStubData;
import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.answer.mapper.AnswerMapper;
import com.codestates.backend.pre_project.post.answer.service.AnswerService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.backend.pre_project.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
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

//    @Test
//    void postAnswerTest() throws Exception {
//        //given
//        AnswerDto.Post post = (AnswerDto.Post) AnswerStubData.MockAnswer.getRequestBody(HttpMethod.POST);
//        AnswerDto.Response responseBody = AnswerStubData.MockAnswer.getSingleResponseBody();
//
//        given(mapper.answerPostDtoToAnswer(1L, Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());
//
//        Answer mockResultAnswer = new Answer();
//        mockResultAnswer.setAnswerId(1L);
//        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(mockResultAnswer);
//        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(responseBody);
//
//        String content = gson.toJson(post);
//        //URI uri = getURI();
//
//        //when
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/questions/{question-id}/answers/post")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        //then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.answerBody").value(post.getAnswerBody()))
////                .andExpect(jsonPath("$.data.memberName").value(post.getMemberName()))
//                .andDo(document("post-answer",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("answerBody").type(STRING).description("답변 본문")
////                                        ,fieldWithPath("memberName").type(STRING).description("이름")
//                                )
//                        )
////                                responseHeaders(
////                                        headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
////                                )
//                ));
//    }
}
