package com.codestates.backend.pre_project.post.question.controller;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.likes.question.QuestionLikesService;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.repository.MemberRepository;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.mapper.QuestionMapper;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.repository.QuestionRepository;
import com.codestates.backend.pre_project.post.question.repository.QuestionTagRepository;
import com.codestates.backend.pre_project.post.question.service.QuestionService;
import com.codestates.backend.pre_project.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/questions")
@Valid
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final QuestionMapper mapper;
    private final MemberRepository memberRepository;
    private final QuestionLikesService questionLikesService;
    private final QuestionTagRepository questionTagRepository;



    @PostMapping("/{question-id}/likes")
    public ResponseEntity postQuestionLike(@PathVariable ("question-id") long questionId){
        Member member = getCurrentMember();
        Question question = questionService.findQuestion(questionId);
        questionService.addLikes(question, member);

        return new ResponseEntity<>(new SingleResponseDto<>(question.getQuestionLikes()), HttpStatus.CREATED);
    }

    @PostMapping("/{question-id}/unlikes")
    public ResponseEntity postQuestionUnlike(@PathVariable ("question-id") long questionId){
        Member member = getCurrentMember();
        Question question = questionService.findQuestion(questionId);
        questionService.downLikes(question, member);

        return new ResponseEntity<>(new SingleResponseDto<>(question.getQuestionLikes()), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPostDto){
        Question question = mapper.questionPostDtoToQuestion(questionPostDto);

        Question createQuestion = questionService.createQuestion(question);


        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)), HttpStatus.CREATED
                );
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion( @PathVariable("question-id") long questionId,
            @Valid @RequestBody QuestionDto.Patch questionPatchDto){
        questionPatchDto.setQuestionId(questionId);
        Question question = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId){
        Question question = questionService.findQuestion(questionId);
        question.setQuestionView(question.getQuestionView()+1);
        questionRepository.save(question);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question))
                ,HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity getQuestions(){
        List<Question> questions = questionService.findQuestions();

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponse(questions)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId) {
        questionService.deleteQuestion(questionId);
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
