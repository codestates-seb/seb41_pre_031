package com.codestates.backend.pre_project.post.question.controller;

import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.mapper.QuestionMapper;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.service.QuestionService;
import com.codestates.backend.pre_project.response.MultiResponseDto;
import com.codestates.backend.pre_project.response.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/questions")
@Valid
public class QuestionController {

    private final QuestionService questionService;

    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPostDto){
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));

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
        Question question = questionService.findQustion(questionId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question))
                ,HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                       @Positive @RequestParam int size){
        Page<Question> pageQuestion = questionService.findQuestions(page, size);
        List<Question> question = pageQuestion.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionToQuestionResponse(question), pageQuestion),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId){
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
