package com.codestates.backend.pre_project.post.question.service;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.repository.QuestionRepository;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import com.sun.xml.bind.v2.TODO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final CustomBeanUtils<Question> beanUtils;

    public QuestionService(QuestionRepository questionRepository, CustomBeanUtils<Question> beanUtils) {
        this.questionRepository = questionRepository;
        this.beanUtils = beanUtils;
    }
    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        Question findQustion = findVerifiedQustion(question.getQuestionId());

        Question updateQuestion = beanUtils.copyNonNullProperties(question, findQustion);
        return updateQuestion;
    }

    public Question findVerifiedQustion(long questionId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        Question findQustion =
                optionalQuestion.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQustion;
    }
    public Page<Question> findQuestions(int page, int size){
        return questionRepository.findAll(
                PageRequest.of(page,size, Sort.by("questionId").descending()));
    }

    public Question findQustion(long qustionId){
        return findVerifiedQustion(qustionId);
    }

    public void deleteQuestion(long questionId){
        Question findQuestion = findVerifiedQustion(questionId);
        questionRepository.delete(findQuestion);
    }

    //TODO
    //조회수
}
