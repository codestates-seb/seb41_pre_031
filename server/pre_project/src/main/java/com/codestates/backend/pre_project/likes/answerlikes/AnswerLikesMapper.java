package com.codestates.backend.pre_project.likes.answerlikes;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerLikesMapper {

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "answerId",target = "answer.answerId")
    @Mapping(source = "answerLikesCount",target = "AnswerLikesCount")
    AnswerLikes answerLikesPatchDtoToAnswerLikes(AnswerLikesPatchDto answerLikesPatchDto);

}