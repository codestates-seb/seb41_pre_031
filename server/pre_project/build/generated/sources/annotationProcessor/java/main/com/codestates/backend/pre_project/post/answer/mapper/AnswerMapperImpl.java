package com.codestates.backend.pre_project.post.answer.mapper;

import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-29T18:01:26+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
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

    @Override
    public AnswerDto.Response answerToAnswerResponseDto(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.Response.ResponseBuilder response = AnswerDto.Response.builder();

        response.answerId( answer.getAnswerId() );
        response.answerBody( answer.getAnswerBody() );
        List<Comment> list = answer.getComments();
        if ( list != null ) {
            response.comments( new ArrayList<Comment>( list ) );
        }
        response.answerLikes( answer.getAnswerLikes() );

        return response.build();
    }
}
