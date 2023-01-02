package com.codestates.backend.pre_project.post.answer.mapper;

import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-02T10:18:54+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto) {
        if ( answerPatchDto == null ) {
            return null;
        }

        Answer.AnswerBuilder answer = Answer.builder();

        answer.answerId( answerPatchDto.getAnswerId() );
        answer.answerBody( answerPatchDto.getAnswerBody() );

        return answer.build();
    }
}
