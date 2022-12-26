package com.codestates.backend.pre_project.post.comment.mapper;

import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-24T08:45:30+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentDtoQuestionPostToComment(CommentDto.QuestionPost questionPost) {
        if ( questionPost == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentBody( questionPost.getCommentBody() );
        comment.setMember( questionPost.getMember() );
        comment.setQuestion( questionPost.getQuestion() );

        return comment;
    }

    @Override
    public Comment commentDtoAnswerPostToComment(CommentDto.AnswerPost answerPost) {
        if ( answerPost == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentBody( answerPost.getCommentBody() );
        comment.setMember( answerPost.getMember() );
        comment.setAnswer( answerPost.getAnswer() );

        return comment;
    }

    @Override
    public Comment commentDtoQuestionPatchToComment(CommentDto.QuestionPatch questionPatch) {
        if ( questionPatch == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentBody( questionPatch.getCommentBody() );

        return comment;
    }

    @Override
    public Comment commentDtoAnswerPatchToComment(CommentDto.AnswerPatch answerPatch) {
        if ( answerPatch == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentBody( answerPatch.getCommentBody() );

        return comment;
    }

    @Override
    public CommentDto.Response commentToCommentDtoResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        String commentBody = null;

        commentBody = comment.getCommentBody();

        long memberId = 0L;
        long answerId = 0L;
        long questionId = 0L;

        CommentDto.Response response = new CommentDto.Response( memberId, answerId, questionId, commentBody );

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
