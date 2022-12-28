package com.codestates.backend.pre_project.post.question.mapper;

import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.QuestionTag;
import com.codestates.backend.pre_project.post.question.Tag;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.dto.QuestionTagDto;
import com.codestates.backend.pre_project.post.question.dto.QuestionTagResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-28T16:17:07+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPatchDtoToQuestion(QuestionDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Question.QuestionBuilder question = Question.builder();

        question.questionId( requestBody.getQuestionId() );
        question.questionTitle( requestBody.getQuestionTitle() );
        question.questionBody( requestBody.getQuestionBody() );
        question.questionTags( questionTagDtoListToQuestionTagList( requestBody.getQuestionTags() ) );

        return question.build();
    }

    @Override
    public List<QuestionDto.Response> questionToQuestionResponse(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto.Response> list = new ArrayList<QuestionDto.Response>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponseDto( question ) );
        }

        return list;
    }

    @Override
    public QuestionTagResponseDto questionTagToQuestionTagDto(QuestionTag questionTag) {
        if ( questionTag == null ) {
            return null;
        }

        QuestionTagResponseDto.QuestionTagResponseDtoBuilder questionTagResponseDto = QuestionTagResponseDto.builder();

        questionTagResponseDto.tagId( questionTagTagTagId( questionTag ) );

        return questionTagResponseDto.build();
    }

    protected QuestionTag questionTagDtoToQuestionTag(QuestionTagDto questionTagDto) {
        if ( questionTagDto == null ) {
            return null;
        }

        QuestionTag.QuestionTagBuilder questionTag = QuestionTag.builder();

        return questionTag.build();
    }

    protected List<QuestionTag> questionTagDtoListToQuestionTagList(List<QuestionTagDto> list) {
        if ( list == null ) {
            return null;
        }

        List<QuestionTag> list1 = new ArrayList<QuestionTag>( list.size() );
        for ( QuestionTagDto questionTagDto : list ) {
            list1.add( questionTagDtoToQuestionTag( questionTagDto ) );
        }

        return list1;
    }

    private long questionTagTagTagId(QuestionTag questionTag) {
        if ( questionTag == null ) {
            return 0L;
        }
        Tag tag = questionTag.getTag();
        if ( tag == null ) {
            return 0L;
        }
        long tagId = tag.getTagId();
        return tagId;
    }
}
