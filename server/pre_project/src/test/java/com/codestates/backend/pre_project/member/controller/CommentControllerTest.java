package com.codestates.backend.pre_project.member.controller;

import com.codestates.backend.pre_project.helper.CommentControllerTestHelper;
import com.codestates.backend.pre_project.helper.CommnetStubData;
import com.codestates.backend.pre_project.helper.MemberControllerTestHelper;
import com.codestates.backend.pre_project.post.answer.Answer;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.comment.mapper.CommentMapper;
import com.codestates.backend.pre_project.post.comment.service.CommentService;
import com.codestates.backend.pre_project.util.ApiDocumentUtils;
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
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.backend.pre_project.util.ApiDocumentUtils.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class CommentControllerTest implements CommentControllerTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private CommentService commentService;

    @MockBean
    private CommentMapper mapper;

    @Test
    void answerPostCommentTest() throws Exception {
        //given
        CommentDto.AnswerPost answerPost = (CommentDto.AnswerPost) CommnetStubData.MockComment.getAnswerRequestBody(HttpMethod.POST);
        CommentDto.Response responseBody = CommnetStubData.MockComment.getAnswerSingleResponseBody();

        given(mapper.commentDtoAnswerPostToComment(Mockito.any(CommentDto.AnswerPost.class))).willReturn(new Comment());

        Comment mockResultComment = new Comment();
        mockResultComment.setCommentId(1L);
        given(commentService.createComment(Mockito.any(Comment.class))).willReturn(mockResultComment);
        given(mapper.commentToCommentDtoResponse(Mockito.any(Comment.class))).willReturn(responseBody);

        String content = gson.toJson(answerPost);

        //when
        ResultActions actions = mockMvc.perform(postRequestBuilder("/answers/comments/{answer-id}",1, content));

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.commentBody").value(answerPost.getCommentBody()))
                .andDo(document("post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getCommentRequestPathParameterDescriptor()
                        ),
                        requestFields(
                                getDefaultCommentPostRequestDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultCommentResponseDescriptors(DataResponseType.SINGLE))
                        )
                        )

                );

    }
}
