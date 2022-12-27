package com.codestates.backend.pre_project.post.answer.mapper;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);
    AnswerDto.Response answerToAnswerResponseDto(Answer answer);
//    List<AnswerDto.Response> answersToAnswerResponses(List<Answer> answers);

    default Answer answerPostDtoToAnswer(long questionId, AnswerDto.Post answerPostDto) {
        Answer answer = new Answer();
        answer.setAnswerBody(answerPostDto.getAnswerBody());
        answer.getMember().setMemberId(answerPostDto.getMemberId());
        answer.getQuestion().setQuestionId(answerPostDto.getQuestionId());

        Question question = new Question();
        question.setQuestionId(questionId);
        answer.setQuestion(question);

        return answer;
    }

    default List<AnswerDto.Response> answersToAnswersResponsesDtos(List<Answer> answers) {
        return answers
                .stream()
                .map(answer -> AnswerDto.Response
                        .builder()
                        .answerId(answer.getAnswerId())
                        .memberId(answer.getMember().getMemberId())
                        .answerBody(answer.getAnswerBody())
                        .answerLikes(answer.getAnswerLikes())
                        .comments(answer.getComments())
                        .build()
                ).collect(Collectors.toList());


    }
}
