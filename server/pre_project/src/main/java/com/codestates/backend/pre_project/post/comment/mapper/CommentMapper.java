package com.codestates.backend.pre_project.post.comment.mapper;

import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
//    Comment commentDtoQuestionPostToComment(CommentDto.QuestionPost questionPost);
    default Comment commentDtoQuestionPostToComment(CommentDto.QuestionPost questionPost){
        Comment comment = new Comment();
        Question question = new Question();
        question.setQuestionId(questionPost.getQuestionId());
        comment.setQuestion(question);
        comment.setCommentBody(questionPost.getCommentBody());
        comment.setCommentRegDate(LocalDateTime.now());
        return comment;
    }

    default Comment commentDtoAnswerPostToComment(CommentDto.AnswerPost answerPost){
        Comment comment = new Comment();
        Answer answer = new Answer();
        answer.setAnswerId(answerPost.getAnswerId());
        comment.setAnswer(answer);
        comment.setCommentBody(answerPost.getCommentBody());
        comment.setCommentRegDate(LocalDateTime.now());
        return comment;
    }
    Comment commentDtoQuestionPatchToComment(CommentDto.QuestionPatch questionPatch);
    Comment commentDtoAnswerPatchToComment(CommentDto.AnswerPatch answerPatch);


    default CommentDto.QuestionResponse commentToCommentDtoQuestionResponse(Comment comment){
        CommentDto.QuestionResponse response = new CommentDto.QuestionResponse(
                comment.getCommentId(),
                comment.getQuestion().getQuestionId(),
                comment.getMember().getMemberName(),
                comment.getCommentBody(),
                LocalDateTime.now()
                );
        return response;
    };

    default CommentDto.AnswerResponse commentToCommentDtoAnswerResponse(Comment comment){
        CommentDto.AnswerResponse response = new CommentDto.AnswerResponse(
                comment.getCommentId(),
                comment.getAnswer().getAnswerId(),
                comment.getMember().getMemberName(),
                comment.getCommentBody(),
                LocalDateTime.now()
        );
        return response;
    };
//    default CommentDto.Response commentToCommentDtoResponse(Comment comment){
//        Question question = new Question();
//        question.setQuestionId(comment.getQuestion().getQuestionId());
//        Answer answer = new Answer();
//        answer.setAnswerId(comment.getAnswer().getAnswerId());
//        Member member = new Member();
//        member.setMemberName(comment.getMember().getMemberName());
//        comment.setQuestion(question);
//        comment.setAnswer(answer);
//        comment.setMember(member);
//        CommentDto.Response response = new CommentDto.Response(
//                comment.getCommentId(),
//                comment.getAnswer().getAnswerId(),
//                comment.getQuestion().getQuestionId(),
//                comment.getMember().getMemberName(),
//                comment.getCommentBody(),
//                LocalDateTime.now()
//        );
//        return response;
//
//    }
    default List<Object> commentsToQuestionCommentDtoResponses(List<Comment> comments){

        return  comments.stream()
               .map(comment ->{
                   if(comment.getAnswer() ==null) return commentToCommentDtoQuestionResponse(comment);
                   return commentToCommentDtoAnswerResponse(comment);

                       }).collect(Collectors.toList());

    }


}
