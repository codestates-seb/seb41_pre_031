package com.codestates.backend.pre_project.post.answer.mapper;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);

    default AnswerDto.Response answerToAnswerResponseDto(Answer answer){
        AnswerDto.Response answer1 = AnswerDto.Response.builder()
                        .answerId(answer.getQuestion().getQuestionId())
                        .answerBody(answer.getAnswerBody())
                        .answerLikes(answer.getAnswerLikes())
                        .memberName(answer.getMember().getMemberName())
                        .answerSelected(answer.getAnswerSelected())
                        .build();


        return answer1;

    }

    default AnswerDto.Response answerPatchToAnswerResponseDto(Answer answer){
        AnswerDto.Response answer1 = AnswerDto.Response.builder()
                        .answerBody(answer.getAnswerBody())
                        .answerId(answer.getAnswerId())
                        .answerSelected(answer.getAnswerSelected())
                        .answerLikes(answer.getAnswerLikes())
                        .memberName(answer.getMember().getMemberName())
                        .build();

        return answer1;

    }
    default Answer answerPostDtoToAnswer(long questionId, AnswerDto.Post requestBody) {
        Answer answer = new Answer();
        Question question = new Question();
        question.setQuestionId(questionId);

        answer.setAnswerBody(requestBody.getAnswerBody());
        answer.setQuestion(question);

        return answer;
    }

    default List<AnswerDto.Response> answersToAnswersResponsesDtos(List<Answer> answers) {
        return answers
                .stream()
                .map(answer -> AnswerDto.Response
                        .builder()
                        .answerId(answer.getAnswerId())
                        .memberName(answer.getMember().getMemberName())
                        .answerBody(answer.getAnswerBody())
                        .answerLikes(answer.getAnswerLikes())
                        .build()
                ).collect(Collectors.toList());


    }
//    default AnswerResponseDto answerToAnswerResponseDto(Answer answer) {
//        Member member = answer.getMember();
//        List<Comment> comments = answer.getComments();
//
//        List<CommentDto.Response> commentResponse
//                = comments.stream().map(comment ->
//                        new CommentDto.Response(comment.getCommentId(),
//                                userToUserResponseDto(comment.getUser()),
//                                comment.getBody(),
//                                comment.getCreatedAt(),
//                                comment.getModifiedAt(),
//                                comment.getCommentType()))
//                .collect(Collectors.toList());
//        Comment 구현 후 작성
//
//        return AnswerResponseDto.builder()
//                .answerId(answer.getAnswerId())
//                .questionId(answer.getQuestion().getQuestionId())
//                .body(answer.getBody())
//                .createdAt(answer.getCreatedAt())
//                .modifiedAt(answer.getModifiedAt())
//                .build();
//    }
}
