package com.codestates.backend.pre_project.member.controller;

import com.codestates.backend.pre_project.helper.CommentControllerTestHelper;
import com.codestates.backend.pre_project.helper.CommnetStubData;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.comment.mapper.CommentMapper;
import com.codestates.backend.pre_project.post.comment.service.CommentService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
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

import static com.codestates.backend.pre_project.util.ApiDocumentUtils.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
        ResultActions actions = mockMvc.perform(postRequestBuilder(getResourceAnswerUri(), 1, content));

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.commentBody").value(answerPost.getCommentBody()))
                .andDo(document("post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getCommentAnswerRequestPathParameterDescriptor()
                        ),
                        requestFields(
                                getDefaultCommentAnswerPostRequestDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultCommentResponseDescriptors(DataResponseType.SINGLE))
                        )
                        )

                );


    }

    @Test
    void questionPostCommentTest() throws Exception {
        //given
        CommentDto.QuestionPost questionPost = (CommentDto.QuestionPost) CommnetStubData.MockComment.getQuestionRequestBody(HttpMethod.POST);
        CommentDto.Response responseBody = CommnetStubData.MockComment.getQuestionSingleResponseBody();

        given(mapper.commentDtoQuestionPostToComment(Mockito.any(CommentDto.QuestionPost.class))).willReturn(new Comment());

        Comment mockResultComment = new Comment();
        mockResultComment.setCommentId(1L);
        given(commentService.createComment(Mockito.any(Comment.class))).willReturn(mockResultComment);
        given(mapper.commentToCommentDtoResponse(Mockito.any(Comment.class))).willReturn(responseBody);

        String content = gson.toJson(questionPost);

        //when
        ResultActions actions = mockMvc.perform(postRequestBuilder(getResourceQuestionUri(), 1, content));

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.commentBody").value(questionPost.getCommentBody()))
                .andDo(document("post-question",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getCommentQuestionRequestPathParameterDescriptor()
                                ),
                                requestFields(
                                        getDefaultCommentQuestionPostRequestDescriptors()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(
                                                getDefaultCommentResponseDescriptors(DataResponseType.SINGLE))
                                )
                        )

                );
    }

    @Test
    void answerPatchCommentTest() throws Exception {
        //given
        long commentId = 1L;

        CommentDto.AnswerPatch patch = (CommentDto.AnswerPatch) CommnetStubData.MockComment.getAnswerRequestBody(HttpMethod.PATCH);
        CommentDto.Response response =
                CommnetStubData.MockComment.getSingleResponseBody(commentId,1,1,1,"Answer CommentBody Patch good");

        given(mapper.commentDtoAnswerPatchToComment(Mockito.any(CommentDto.AnswerPatch.class))).willReturn(new Comment());

        given(commentService.updateComment(Mockito.any(Comment.class))).willReturn(new Comment());

        given(mapper.commentToCommentDtoResponse(Mockito.any(Comment.class))).willReturn(response);

        String content = gson.toJson(patch);

        //when
        ResultActions actions = mockMvc.perform(patchRequestBuilder(getResourceAnswerUri(), commentId, content));


        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.commentBody").value(patch.getCommentBody()))
                .andDo(document("patch-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getCommentAnswerRequestPathParameterDescriptor()
                        ),
                        requestFields(
                                getDefaultCommentPatchRequestDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultCommentResponseDescriptors(DataResponseType.SINGLE))
                        )));
    }
}