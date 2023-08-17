package com.codestates.backend.pre_project.post.answer.mapper;

import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
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
}
