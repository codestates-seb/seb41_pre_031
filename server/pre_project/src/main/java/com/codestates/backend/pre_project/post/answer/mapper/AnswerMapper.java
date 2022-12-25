package com.codestates.backend.pre_project.post.answer.mapper;

import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);
    AnswerDto.Response answerToAnswerResponseDto(Answer answer);
//    List<AnswerDto.Response> answersToAnswerResponses(List<Answer> answers);

    default Answer answerPostDtoToAnswer(long questionId, AnswerDto.Post answerPostDto) {
        Answer answer = new Answer();
        answer.setAnswerBody(answerPostDto.getAnswerBody());

        Question question = new Question();
        question.setQuestionId(questionId);
        answer.setQuestion(question);

        return answer;
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
