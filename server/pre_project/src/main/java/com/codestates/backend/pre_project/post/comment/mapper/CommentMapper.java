package com.codestates.backend.pre_project.post.comment.mapper;

import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    Comment commentDtoQuestionPostToComment(CommentDto.QuestionPost questionPost);
    Comment commentDtoAnswerPostToComment(CommentDto.AnswerPost answerPost);
    Comment commentDtoQuestionPatchToComment(CommentDto.QuestionPatch questionPatch);
    Comment commentDtoAnswerPatchToComment(CommentDto.AnswerPatch answerPatch);
    CommentDto.Response commentToCommentDtoResponse(Comment comment);
    List<CommentDto.Response> commentsToCommentDtoResponses(List<Comment> comments);

}
