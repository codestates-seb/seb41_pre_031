package com.codestates.backend.pre_project.post.answer.controller;


import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.repository.MemberRepository;
import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.answer.mapper.AnswerMapper;
import com.codestates.backend.pre_project.post.answer.service.AnswerService;
import com.codestates.backend.pre_project.response.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;
    private final MemberRepository memberRepository;


    public AnswerController(AnswerService answerService, AnswerMapper mapper, MemberRepository memberRepository) {
        this.answerService = answerService;
        this.mapper = mapper;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/questions/answers/{answer-id}/selection")
    public ResponseEntity postAnswerSelection(@PathVariable("answer-id") long answerId){
        Answer answer = answerService.findAnswer(answerId);
        Member member = getCurrentMember();
        answerService.selectAnswer(answer, member);
        return new ResponseEntity<>(new SingleResponseDto<>(answer.getAnswerSelected()), HttpStatus.CREATED);
    }

    @PostMapping("/questions/answers/{answer-id}/likes")
    public ResponseEntity postAnswerLike(@PathVariable("answer-id") long answerId){
        Member member = getCurrentMember();
        Answer answer = answerService.findAnswer(answerId);
        answerService.addLikes(answer, member);

        return new ResponseEntity<>(new SingleResponseDto<>(answer.getAnswerLikes()), HttpStatus.CREATED);
    }

    @PostMapping("/questions/answers/{answer-id}/unlikes")
    public ResponseEntity postAnswerUnlike(@PathVariable("answer-id") long answerId){
        Member member = getCurrentMember();
        Answer answer = answerService.findAnswer(answerId);
        answerService.downLikes(answer, member);

        return new ResponseEntity(new SingleResponseDto<>(answer.getAnswerLikes()), HttpStatus.CREATED);
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

    public Member getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null || authentication.getName().equals("anonymousUser"))
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION);

        Optional<Member> optionalMember = memberRepository.findByEmail(authentication.getName());
        Member member = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        System.out.println("현재 사용자:"+member.getMemberId());

        return member;
    }
}
