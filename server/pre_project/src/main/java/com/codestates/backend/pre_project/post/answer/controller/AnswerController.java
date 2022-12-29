package com.codestates.backend.pre_project.post.answer.controller;


import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.answer.mapper.AnswerMapper;
import com.codestates.backend.pre_project.post.answer.service.AnswerService;
import com.codestates.backend.pre_project.response.SingleResponseDto;
import com.codestates.backend.pre_project.response.MultiResponseDto;
import com.codestates.backend.pre_project.response.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;
    private final MemberService memberService;

    public AnswerController(AnswerService answerService, AnswerMapper mapper, MemberService memberService) {
        this.answerService = answerService;
        this.mapper = mapper;
        this.memberService = memberService;
    }

    @PostMapping("/questions/{question-id}/answers/post")
    public ResponseEntity postAnswer(@PathVariable("question-id") long questionId,
                                     @Validated @RequestBody AnswerDto.Post requestBody
    ) {
        requestBody.setQuestionId(questionId);
        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(questionId, requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/answers/{answer-id}/edit")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") long answerId,
                                      @Valid @RequestBody AnswerDto.Patch requestBody) {
//        if (requestBody.getAnswerId() != answerId)
//            throw new BusinessLogicException(ExceptionCode.EDIT_NOT_ALLOWED);
        // post로 받는 아이디값이 파라미터로 받는 아이디값과 다르면 예외 빵

        requestBody.setAnswerId(answerId);

        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerPatchToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @GetMapping("/questions/{answer-id}/answers")
    public ResponseEntity getAnswers(
            @PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.findAnswer(answerId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer))
                , HttpStatus.OK);
    }

    @GetMapping("/answers")
    public ResponseEntity getAnswers() {
        List<Answer> answers = answerService.findAnswers();

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answersToAnswersResponsesDtos(answers)),
                HttpStatus.OK);
    }

    @DeleteMapping("/answers/{answer-id}")
    public ResponseEntity deleteAnswer(
            @PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}