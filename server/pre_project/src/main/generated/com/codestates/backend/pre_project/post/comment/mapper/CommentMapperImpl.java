package com.codestates.backend.pre_project.post.comment.mapper;

import com.codestates.backend.pre_project.likes.entity.Likes;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-27T13:25:47+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentDtoQuestionPostToComment(CommentDto.QuestionPost questionPost) {
        if ( questionPost == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commentBody( questionPost.getCommentBody() );
        comment.member( questionPost.getMember() );
        comment.question( questionPost.getQuestion() );

        return comment.build();
    }

    @Override
    public Comment commentDtoAnswerPostToComment(CommentDto.AnswerPost answerPost) {
        if ( answerPost == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commentBody( answerPost.getCommentBody() );
        comment.member( answerPost.getMember() );
        comment.answer( answerToAnswer( answerPost.getAnswer() ) );

        return comment.build();
    }

    @Override
    public Comment commentDtoQuestionPatchToComment(CommentDto.QuestionPatch questionPatch) {
        if ( questionPatch == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commentBody( questionPatch.getCommentBody() );

        return comment.build();
    }

    @Override
    public Comment commentDtoAnswerPatchToComment(CommentDto.AnswerPatch answerPatch) {
        if ( answerPatch == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

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

        commentId = comment.getCommentId();
        commentBody = comment.getCommentBody();

        long memberId = 0L;
        long answerId = 0L;
        long questionId = 0L;

        CommentDto.Response response = new CommentDto.Response( commentId, memberId, answerId, questionId, commentBody );

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

    protected Answer answerToAnswer(com.codestates.backend.pre_project.post.answer.Answer answer) {
        if ( answer == null ) {
            return null;
        }

        Answer answer1 = new Answer();

        answer1.setAnswerId( answer.getAnswerId() );
        answer1.setAnswerBody( answer.getAnswerBody() );
        answer1.setAnswerLikes( (int) answer.getAnswerLikes() );
        answer1.setAnswerRegDate( answer.getAnswerRegDate() );
        answer1.setAnswerLastDate( answer.getAnswerLastDate() );
        answer1.setMember( answer.getMember() );
        answer1.setQuestion( answer.getQuestion() );
        List<Likes> list = answer.getLikes();
        if ( list != null ) {
            answer1.setLikes( new ArrayList<Likes>( list ) );
        }
        List<Comment> list1 = answer.getComments();
        if ( list1 != null ) {
            answer1.setComments( new ArrayList<Comment>( list1 ) );
        }

        return answer1;
    }
}
