package com.codestates.backend.pre_project.post.question.mapper;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.QuestionTag;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.dto.QuestionTagResponseDto;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import org.aspectj.apache.bcel.classfile.Module;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
        question.setQuestionLastDate(LocalDateTime.now());
        question.setQuestionRegDate(LocalDateTime.now());

        return question;
    }

   default Question questionPatchDtoToQuestion(QuestionDto.Patch requestBody){
       Question question = new Question();


       List<QuestionTag> questionTags = requestBody.getQuestionTags().stream()
               .map(questionTagDto -> {
                   QuestionTag questionTag = new QuestionTag();
                   questionTag.setQuestion(question);
                   questionTag.setTagName(questionTagDto.getTagName());
                   return questionTag;
               }).collect(Collectors.toList());
       question.setQuestionId(requestBody.getQuestionId());
       question.setQuestionTitle(requestBody.getQuestionTitle());
       question.setQuestionBody(requestBody.getQuestionBody());
       question.setQuestionTags(questionTags);
       question.setQuestionLastDate(LocalDateTime.now());

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
                    new QuestionDto.Response(
                            question.getQuestionId(),
                            question.getMemberName(),
                            question.getQuestionTitle(),
                            question.getQuestionBody(),
                            questionTagResponseDtos,
                            question.getQuestionRegDate(),
                            question.getQuestionLastDate(),
                            question.getQuestionLikes());


            return questionResponseDto;
    }



    List<QuestionDto.Response> questionToQuestionResponse(List<Question> questions);
//    {
//        List<QuestionDto.Response> responses = new LinkedList<>();
//        for(int i =0; i>questions.size(); i++){
//            responses.add(questionToQuestionResponseDto(questions.get(i)));
//        }
//        return responses;
//    }



}
