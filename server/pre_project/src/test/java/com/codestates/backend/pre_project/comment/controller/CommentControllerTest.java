package com.codestates.backend.pre_project.comment.controller;

import com.codestates.backend.pre_project.helper.CommentControllerTestHelper;
import com.codestates.backend.pre_project.helper.CommnetStubData;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.comment.mapper.CommentMapper;
import com.codestates.backend.pre_project.post.comment.service.CommentService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.codestates.backend.pre_project.util.ApiDocumentUtils.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
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
                .andDo(document("post-AnswerComment",
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
                .andDo(document("post-questionComment",
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
        long answerId = 1L;

        CommentDto.AnswerPatch patch = (CommentDto.AnswerPatch) CommnetStubData.MockComment.getAnswerRequestBody(HttpMethod.PATCH);
        CommentDto.Response response =
                CommnetStubData.MockComment.getSingleResponseBody(1,1,1,1,"Answer CommentBody Patch good");

        given(mapper.commentDtoAnswerPatchToComment(Mockito.any(CommentDto.AnswerPatch.class))).willReturn(new Comment());

        given(commentService.updateComment(Mockito.any(Comment.class))).willReturn(new Comment());

        given(mapper.commentToCommentDtoResponse(Mockito.any(Comment.class))).willReturn(response);

        String content = gson.toJson(patch);

        //when
        ResultActions actions = mockMvc.perform(patchRequestBuilder(getResourceAnswerUri(), answerId, content));


        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.commentBody").value(patch.getCommentBody()))
                .andDo(document("patch-answerComment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getCommentAnswerRequestPathParameterDescriptor()
                        ),
                        requestFields(
                                getDefaultCommentAnswerPatchRequestDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultCommentResponseDescriptors(DataResponseType.SINGLE))
                        )));
    }
    @Test
    void questionPatchCommentTest() throws Exception {
        //given
        long questionId = 1L;

        CommentDto.QuestionPatch patch = (CommentDto.QuestionPatch) CommnetStubData.MockComment.getQuestionRequestBody(HttpMethod.PATCH);
        CommentDto.Response response =
                CommnetStubData.MockComment.getSingleResponseBody(1,1,1,1,"Question CommentBody Patch good");

        given(mapper.commentDtoQuestionPatchToComment(Mockito.any(CommentDto.QuestionPatch.class))).willReturn(new Comment());

        given(commentService.updateComment(Mockito.any(Comment.class))).willReturn(new Comment());

        given(mapper.commentToCommentDtoResponse(Mockito.any(Comment.class))).willReturn(response);

        String content = gson.toJson(patch);

        //when
        ResultActions actions = mockMvc.perform(patchRequestBuilder(getResourceQuestionUri(), questionId, content));


        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.commentBody").value(patch.getCommentBody()))
                .andDo(document("patch-questionComment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getCommentQuestionRequestPathParameterDescriptor()
                        ),
                        requestFields(
                                getDefaultCommentQuestionPatchRequestDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultCommentResponseDescriptors(DataResponseType.SINGLE))
                        )));
    }

    @Test
    void getCommentTest() throws Exception {
        //given
        long commentId = 1L;
        Comment comment = CommnetStubData.MockComment.getSingleResultComment(commentId);
        CommentDto.Response response = CommnetStubData.MockComment.getSingleResponseBody();

        given(commentService.findComment(anyLong())).willReturn(new Comment());
        given(mapper.commentToCommentDtoResponse(Mockito.any(Comment.class))).willReturn(response);

        //when
        ResultActions actions = mockMvc.perform(
                getRequestBuilder("/comments/{comment-id}",commentId));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.commentBody").value(comment.getCommentBody()))
                .andDo(
                        document("get-Comment",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getCommentRequestPathParameterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(
                                                getDefaultCommentResponseDescriptors(DataResponseType.SINGLE)
                                        )
                                ))
                );
    }

    @Test
    void getCommentsTest() throws Exception {
        //given
        Page<Comment> commentPage = CommnetStubData.MockComment.getMultiResultComment();

        List<CommentDto.Response> responses = CommnetStubData.MockComment.getMultiResponseBody();

        given(commentService.findComments(anyInt(), anyInt())).willReturn(commentPage);
        given(mapper.commentsToCommentDtoResponses(anyList())).willReturn(responses);

        String page = "1";
        String size = "10";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        //when
        ResultActions actions = mockMvc.perform(getRequestBuilder("/comments",queryParams));

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "get-comments",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestParameters(
                                        getDefaultRequestParameterDescriptors()
                                ),
                                responseFields(
                                        getFullPageResponseDescriptors(
                                                getDefaultCommentResponseDescriptors(DataResponseType.LIST)
                                        )
                                )
                        )
                ).andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        System.out.println(list);
        System.out.println("###############");
        assertThat(list.size(), is(3));
    }

    @Test
    void deleteCommentTest() throws Exception {
        //given
        long commentId = 1L;
        doNothing().when(commentService).deleteComment(commentId);

        //when
        ResultActions actions = mockMvc.perform(deleteRequestBuilder("/comments/{comment-id}",commentId));

        //then
        actions.andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-comment",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getCommentRequestPathParameterDescriptor()
                                )
                        )
                );

    }

}