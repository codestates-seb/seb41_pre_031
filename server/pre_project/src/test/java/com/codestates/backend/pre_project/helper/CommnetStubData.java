package com.codestates.backend.pre_project.helper;

import com.codestates.backend.pre_project.member.entity.Member;

import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommnetStubData {

    private static Map<HttpMethod, Object> answerStubRequestBody;
    private static Map<HttpMethod, Object> questionStubRequestBody;
    static {
        answerStubRequestBody = new HashMap<>();
        answerStubRequestBody.put(HttpMethod.POST, new CommentDto.AnswerPost(
                "this is commentBody and Answer class", 1, 1
        ));
        answerStubRequestBody.put(HttpMethod.PATCH, new CommentDto.AnswerPatch(
                "Answer CommentBody Patch good", 1
        ));

        questionStubRequestBody = new HashMap<>();
        questionStubRequestBody.put(HttpMethod.POST, new CommentDto.QuestionPost(
                "this is commentBody and Question class", 1, 1
        ));
        questionStubRequestBody.put(HttpMethod.PATCH, new CommentDto.QuestionPatch(
                "Question CommentBody Patch good", 1
        ));
    }

    public static class MockComment {
        public static Object getAnswerRequestBody(HttpMethod method) {
            return answerStubRequestBody.get(method);
        }

        public static Object getQuestionRequestBody(HttpMethod method) {
            return questionStubRequestBody.get(method);
        }

        public static CommentDto.Response getAnswerSingleResponseBody() {
            return new CommentDto.Response(
                    1,
                    1,
                    1,
                    1,
                    "this is commentBody and Answer class"
            );
        }

        public static CommentDto.Response getSingleResponseBody() {
            return new CommentDto.Response(
                    1,
                    1,
                    1,
                    1,
                    "this is commentBody, why? who are you?"
            );
        }

        public static CommentDto.Response getQuestionSingleResponseBody() {
            return new CommentDto.Response(
                    1,
                    1,
                    1,
                    1,
                    "this is commentBody and Question class"
            );
        }
        public static CommentDto.Response getSingleResponseBody(long commentId, long memberId, long answerId, long questionId, String commentBody) {
            Long optionalCommentId = Optional.ofNullable(commentId).orElse(1L);
            Long optionalMemberId = Optional.ofNullable(memberId).orElse(1L);
            Long optionalAnswerId = Optional.ofNullable(answerId).orElse(1L);
            Long optionalQuestionId = Optional.ofNullable(questionId).orElse(1L);
            String optionalCommentBody = Optional.ofNullable(commentBody).orElse("Response CommentBody yeah~!");
            return new CommentDto.Response(
                    optionalCommentId,
                    optionalMemberId,
                    optionalAnswerId,
                    optionalQuestionId,
                    optionalCommentBody);
        }

        public static Comment getSingleResultComment(long commentId) {
            Comment comment = new Comment(1L,"this is commentBody, why? who are you?",new Member(),
                    LocalDateTime.now(), new Question(), new Answer());
            comment.setCommentId(commentId);

            return comment;
        }

        public static Comment getSingleResultComment(long commentId, Map<String, String > updatedInfo) {
            String commentBody = Optional.ofNullable(updatedInfo.get("commentBody")).orElse("commentBody is good");
            Comment comment = new Comment(1,commentBody,new Member(),LocalDateTime.now(),new Question(), new Answer());
            comment.setCommentId(commentId);

            return comment;
        }

        public static Page<Comment> getMultiResultComment() {
            Comment comment1 = new Comment(1,"comment1 Body man",new Member(),LocalDateTime.now(),new Question(), new Answer());
            comment1.setCommentId(1L);
            Comment comment2 = new Comment(2,"comment2 Body man",new Member(),LocalDateTime.now(),new Question(), new Answer());
            comment2.setCommentId(2L);
            Comment comment3 = new Comment(3,"comment2 Body man",new Member(),LocalDateTime.now(),new Question(), new Answer());
            comment3.setCommentId(3L);

            return new PageImpl<>(List.of(comment1, comment2, comment3),
                    PageRequest.of(0, 10, Sort.by("commentId").descending()),3);
        }

        public static List<CommentDto.Response> getMultiResponseBody() {
            return List.of(
                    new CommentDto.Response(
                            1, 1, 1, 1,"comment1 Body man"
                    ),
                    new CommentDto.Response(
                            2, 2, 2, 2,"comment2 Body man"
                    ),
                    new CommentDto.Response(
                            3, 3, 3, 3,"comment3 Body man"
                    )
            );
        }
    }

}
