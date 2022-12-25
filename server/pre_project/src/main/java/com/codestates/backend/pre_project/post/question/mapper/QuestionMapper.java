package com.codestates.backend.pre_project.post.question.mapper;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.QuestionTag;
import com.codestates.backend.pre_project.post.question.Tag;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.dto.QuestionTagResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
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

    @Mapping(source = "member.memberId", target = "memberId")
    QuestionDto.Response questionToQuestionResponseDto(Question question);

    List<QuestionDto.Response> questionToQuestionResponse(List<Question> questions);

    @Mapping(source = "tag.tagId", target= "tagId")
    QuestionTagResponseDto questionTagToQuestionTagDto(QuestionTag questionTag);
}
