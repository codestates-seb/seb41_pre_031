package com.codestates.backend.pre_project.post.question.mapper;

import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question questionPostDtoToQuestion(QuestionDto.Post requestBody);

    Question questionPatchDtoToQuestion(QuestionDto.Patch requestBody);

    QuestionDto.Response questionToQuestionResponseDto(Question question);

    List<QuestionDto.Response> questionToQuestionResponse(List<Question> questions);

}
