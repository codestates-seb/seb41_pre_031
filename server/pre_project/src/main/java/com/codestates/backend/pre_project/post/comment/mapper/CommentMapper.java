package com.codestates.backend.pre_project.post.comment.mapper;

import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    default Comment commentDtoQuestionPostToComment(CommentDto.QuestionPost questionPost){
        Comment comment = new Comment();
        Question question = new Question();
        question.setQuestionId(questionPost.getQuestionId());
        comment.setQuestion(question);
        comment.setCommentBody(questionPost.getCommentBody());
        return comment;
    }

    default Comment commentDtoAnswerPostToComment(CommentDto.AnswerPost answerPost){
        Comment comment = new Comment();
        Answer answer = new Answer();
        answer.setAnswerId(answerPost.getAnswerId());
        comment.setAnswer(answer);
        comment.setCommentBody(answerPost.getCommentBody());
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
                comment.getCreatedAt()
                );
        return response;
    };

    default CommentDto.AnswerResponse commentToCommentDtoAnswerResponse(Comment comment){
        CommentDto.AnswerResponse response = new CommentDto.AnswerResponse(
                comment.getCommentId(),
                comment.getAnswer().getAnswerId(),
                comment.getMember().getMemberName(),
                comment.getCommentBody(),
                comment.getCreatedAt()
        );
        return response;
    };

    default List<Object> commentsToQuestionCommentDtoResponses(List<Comment> comments){

        return  comments.stream()
               .map(comment ->{
                   if(comment.getAnswer() ==null) return commentToCommentDtoQuestionResponse(comment);
                   return commentToCommentDtoAnswerResponse(comment);

                       }).collect(Collectors.toList());

    }


}
