package com.codestates.backend.pre_project.post.comment.mapper;

import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import org.apache.tomcat.jni.Local;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    Comment commentDtoQuestionPostToComment(CommentDto.QuestionPost questionPost);
    Comment commentDtoAnswerPostToComment(CommentDto.AnswerPost answerPost);
    Comment commentDtoQuestionPatchToComment(CommentDto.QuestionPatch questionPatch);
    Comment commentDtoAnswerPatchToComment(CommentDto.AnswerPatch answerPatch);


    default CommentDto.QuestionResponse commentToCommentDtoQuestionResponse(Comment comment){
        CommentDto.QuestionResponse response = new CommentDto.QuestionResponse(
                comment.getCommentId(),
                comment.getMember().getMemberId(),
                comment.getQuestion().getQuestionId(),
                comment.getCommentBody(),
                LocalDateTime.now()
                );
        return response;
    };

    default CommentDto.AnswerResponse commentToCommentDtoAnswerResponse(Comment comment){
        CommentDto.AnswerResponse response = new CommentDto.AnswerResponse(
                comment.getCommentId(),
                comment.getMember().getMemberId(),
                comment.getAnswer().getAnswerId(),
                comment.getCommentBody(),
                LocalDateTime.now()
        );
        return response;
    };
    CommentDto.Response commentToCommentDtoResponse(Comment comment);
    List<CommentDto.Response> commentsToCommentDtoResponses(List<Comment> comments);

    //List<CommentDto.AnswerResponse> commentsToAnswerCommentDtoResponses(List<Comment> comments);

}
