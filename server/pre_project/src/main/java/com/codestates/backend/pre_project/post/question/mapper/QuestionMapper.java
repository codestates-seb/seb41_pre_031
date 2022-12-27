package com.codestates.backend.pre_project.post.question.mapper;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.QuestionTag;
import com.codestates.backend.pre_project.post.question.Tag;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.dto.QuestionTagResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {


    default Question questionPostDtoToQuestion(QuestionDto.Post requestBody){
        Question question = new Question();
        Member member = new Member();
        member.setMemberId(requestBody.getMemberId());

        List<QuestionTag> questionTags = requestBody.getQuestionTags().stream()
                .map(questionTagDto -> {
                    QuestionTag questionTag = new QuestionTag();
                    Tag tag = new Tag();
                    tag.setTagId(questionTagDto.getTagId());
                    questionTag.setQuestion(question);
                    questionTag.setTag(tag);

                    return questionTag;
                }).collect(Collectors.toList());
        question.setQuestionId(question.getQuestionId());
        question.setMember(member);
        question.setQuestionTags(questionTags);

        return question;
    }

    Question questionPatchDtoToQuestion(QuestionDto.Patch requestBody);


    default QuestionDto.Response questionToQuestionResponseDto(Question question){
            List<QuestionTag> questionTags = question.getQuestionTags();
            List<QuestionTagResponseDto> questionTagResponseDtos = new LinkedList<>();
            for(int i=0; i<questionTags.size(); i++){
                QuestionTag qt = questionTags.get(i);
                questionTagResponseDtos.add(
                        new QuestionTagResponseDto(qt.getTag().getTagId(), qt.getQuestion().getQuestionId(), qt.getTag().getTagName()
                        ));
            }
            QuestionDto.Response questionResponseDto = new QuestionDto.Response(question.getQuestionId(),question.getMember().getMemberId(),question.getQuestionTitle(),question.getQuestionBody(),questionTagResponseDtos,question.getQuestionRegDate(),question.getQuestionLastDate(),question.getQuestionLikes());
            questionResponseDto.setQuestionId(question.getQuestionId());
            questionResponseDto.setQuestionBody(question.getQuestionBody());
            questionResponseDto.setQuestionLikes(question.getQuestionLikes());
            questionResponseDto.setMemberId(question.getMember().getMemberId());
            questionResponseDto.setQuestionTags(
                    questionTagsToQuestionTagResponseDtos(questionTags)
            );
            return questionResponseDto;
    }

   default List<QuestionTagResponseDto> questionTagsToQuestionTagResponseDtos(List<QuestionTag> questionTags){
        return questionTags
                .stream()
                .map(questionTag -> QuestionTagResponseDto
                        .builder()
                        .tagId(questionTag.getTag().getTagId())
                        .tagName(questionTag.getTag().getTagName())
                        .questionId(questionTag.getQuestion().getQuestionId())
                        .build())
                .collect(Collectors.toList());
    }

    List<QuestionDto.Response> questionToQuestionResponse(List<Question> questions);

    @Mapping(source = "tag.tagId", target= "tagId")
    QuestionTagResponseDto questionTagToQuestionTagDto(QuestionTag questionTag);
}
