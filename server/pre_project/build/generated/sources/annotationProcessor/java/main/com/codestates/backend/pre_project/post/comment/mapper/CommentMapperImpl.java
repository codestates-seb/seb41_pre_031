package com.codestates.backend.pre_project.post.comment.mapper;

import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-29T23:40:30+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentDtoQuestionPatchToComment(CommentDto.QuestionPatch questionPatch) {
        if ( questionPatch == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commentId( questionPatch.getCommentId() );
        comment.commentBody( questionPatch.getCommentBody() );

        return comment.build();
    }

    @Override
    public Comment commentDtoAnswerPatchToComment(CommentDto.AnswerPatch answerPatch) {
        if ( answerPatch == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commentId( answerPatch.getCommentId() );
        comment.commentBody( answerPatch.getCommentBody() );

        return comment.build();
    }

    @Override
    public CommentDto.Response commentToCommentDtoResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        long commentId = 0L;
        String commentBody = null;
        LocalDateTime commentRegDate = null;

        commentId = comment.getCommentId();
        commentBody = comment.getCommentBody();
        commentRegDate = comment.getCommentRegDate();

        long memberId = 0L;
        long answerId = 0L;
        long questionId = 0L;

        CommentDto.Response response = new CommentDto.Response( commentId, memberId, answerId, questionId, commentBody, commentRegDate );

        return response;
    }

    @Override
    public List<CommentDto.Response> commentsToCommentDtoResponses(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentDto.Response> list = new ArrayList<CommentDto.Response>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToCommentDtoResponse( comment ) );
        }

        return list;
    }
}
