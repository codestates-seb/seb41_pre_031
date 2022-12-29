package com.codestates.backend.pre_project.post.comment.controller;

import com.codestates.backend.pre_project.post.answer.service.AnswerService;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.comment.mapper.CommentMapper;
import com.codestates.backend.pre_project.post.comment.service.CommentService;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.repository.QuestionRepository;
import com.codestates.backend.pre_project.post.question.service.QuestionService;
import com.codestates.backend.pre_project.response.MultiResponseDto;
import com.codestates.backend.pre_project.response.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper mapper;

    private final QuestionService questionService;
    private final AnswerService answerService;

    public CommentController(CommentService commentService, CommentMapper mapper, QuestionService questionService,
                             AnswerService answerService) {
        this.commentService = commentService;
        this.mapper = mapper;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @PostMapping("/questions/{question-id}/comments")
    public ResponseEntity questionPostComment(@Valid
                                                  @PathVariable("question-id") @Positive long questionId,
                                                  @RequestBody CommentDto.QuestionPost requestBody) {

        requestBody.setQuestionId(questionId);

        Comment comment = mapper.commentDtoQuestionPostToComment(requestBody);

        Comment createComment = commentService.createQuestionComment(comment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentDtoResponse(createComment)), HttpStatus.CREATED
        );
    }

    @PostMapping("/answers/{answer-id}/comments")
    public ResponseEntity answerPostComment(@Valid
                                                @PathVariable("answer-id") @Positive long answerId,
                                                @RequestBody CommentDto.AnswerPost requestBody) {
        requestBody.setAnswerId(answerId);

        Comment comment = mapper.commentDtoAnswerPostToComment(requestBody);

        Comment createComment = commentService.createComment(comment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentDtoAnswerResponse(createComment)), HttpStatus.CREATED
        );
    }

    @PatchMapping("/questions/{question-id}/comments/{comment-id}")
    public ResponseEntity questionPatchComment(@Valid @PathVariable("question-id") @Positive long questionId,
                                               @PathVariable("comment-id") @Positive long commentId,
                                               @Valid @RequestBody CommentDto.QuestionPatch requestBody){

        requestBody.setQuestionId(questionId);

        Comment comment = commentService.updateComment(mapper.commentDtoQuestionPatchToComment(requestBody));
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentDtoResponse(comment)),HttpStatus.OK);
    }

    @PatchMapping("/answers/comments/{answer-id}")
    public ResponseEntity questionPatchComment(
            @PathVariable("answer-id") @Positive long answerId,
            @Valid @RequestBody CommentDto.AnswerPatch requestBody){

        requestBody.setAnswerId(answerId);

        Comment comment = commentService.updateComment(mapper.commentDtoAnswerPatchToComment(requestBody));
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentDtoAnswerResponse(comment)),HttpStatus.OK);
    }

    @GetMapping("/comments/{comment-id}")
    public ResponseEntity getComment(@PathVariable("comment-id") @Positive long commentId) {
        Comment comment = commentService.findComment(commentId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentDtoResponse(comment)),HttpStatus.OK
        );
    }

    @GetMapping("/comments")
    public ResponseEntity getComments(@Positive @RequestParam int page,
                                      @Positive @RequestParam int size) {
        Page<Comment> pageComments = commentService.findComments(page -1, size);
        List<Comment> comments = pageComments.getContent();
        return new ResponseEntity(
                new MultiResponseDto<>(mapper.commentsToCommentDtoResponses(comments),pageComments)
                ,HttpStatus.OK
        );
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") @Positive long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
