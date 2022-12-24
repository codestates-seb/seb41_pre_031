package com.codestates.backend.pre_project.post.answer.service;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.answer.repository.AnswerRepository;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final CustomBeanUtils<Answer> beanUtils;
//    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository, CustomBeanUtils<Answer> beanUtils) {
        this.answerRepository = answerRepository;
        this.beanUtils = beanUtils;
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
//        updatePoint(savedAnswer); 포인트 증가
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Answer updateAnswer = beanUtils.copyNonNullProperties(answer, findAnswer);

        return answerRepository.save(updateAnswer);
//        답변 작성자만 수정 가능하도록 구현 필요
    }

    public void deleteAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        answerRepository.delete(findAnswer);
//        답변 작성자만 삭제 가능하도록 구현 필요
    }

    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }

    public Page<Answer> findAnswers(int page, int size) {
        return answerRepository.findAll(PageRequest.of(page, size,
                Sort.by("answerId").descending()));
    }

    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer =
                answerRepository.findById(answerId);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.EDIT_NOT_ALLOWED));
        return findAnswer;
    }
//    Author의 memberId가 사용자와 다를 때 글 수정이 되지 않도록 한다.

}
