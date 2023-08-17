package com.codestates.backend.pre_project.post.question.mapper;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.QuestionTag;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.dto.QuestionTagResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {

    default Question questionPostDtoToQuestion(QuestionDto.Post requestBody){
        Question question = new Question();
        Member member = new Member();

        List<QuestionTag> questionTags = requestBody.getQuestionTags().stream()
                .map(questionTagDto -> {
                    QuestionTag questionTag = new QuestionTag();
                    questionTag.setQuestion(question);
                    questionTag.setTagName(questionTagDto.getTagName());
                    return questionTag;
                }).collect(Collectors.toList());
        question.setQuestionId(question.getQuestionId());
        question.setMember(member);
        question.setQuestionTags(questionTags);
        question.setQuestionBody(requestBody.getQuestionBody());
        question.setQuestionTitle(requestBody.getQuestionTitle());

        return question;
    }

   default Question questionPatchDtoToQuestion(QuestionDto.Patch requestBody){
    Question question = new Question();
       List<QuestionTag> questionTags = requestBody.getQuestionTags().stream()
               .map(questionTagDto -> {
                   QuestionTag questionTag = QuestionTag.builder()
                           .question(question)
                                   .tagName(questionTagDto.getTagName())
                                           .build();
                   return questionTag;
               }).collect(Collectors.toList());
       question.setQuestionId(requestBody.getQuestionId());
       question.setQuestionTitle(requestBody.getQuestionTitle());
       question.setQuestionBody(requestBody.getQuestionBody());
       question.setQuestionTags(questionTags);

       return question;
   }




    default QuestionDto.Response questionToQuestionResponseDto(Question question){
            List<QuestionTag> questionTags = question.getQuestionTags();
            List<QuestionTagResponseDto> questionTagResponseDtos = new LinkedList<>();
            for(int i=0; i<questionTags.size(); i++){
                QuestionTag qt = questionTags.get(i);
                questionTagResponseDtos.add(
                        new QuestionTagResponseDto(qt.getQuestionTagId(), qt.getTagName()
                        ));
            }
            QuestionDto.Response questionResponseDto =
                    QuestionDto.Response.builder()
                            .questionId(question.getQuestionId())
                            .questionBody(question.getMemberName())
                            .questionTitle(question.getQuestionTitle())
                            .questionLikes(question.getQuestionLikes())
                            .questionBody(question.getQuestionBody())
                            .questionBody(question.getQuestionBody())
                            .questionView(question.getQuestionView())
                            .questionTags(questionTagResponseDtos)
                            .createdAt(question.getCreatedAt())
                            .modifiedAt(question.getModifiedAt())
                            .isSelectAnswer(question.getIsSelectAnswer())
                            .build();

            return questionResponseDto;
    }

    List<QuestionDto.Response> questionToQuestionResponse(List<Question> questions);

}
