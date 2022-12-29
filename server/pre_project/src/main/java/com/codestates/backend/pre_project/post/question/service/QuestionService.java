package com.codestates.backend.pre_project.post.question.service;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.repository.MemberRepository;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.QuestionTag;
import com.codestates.backend.pre_project.post.question.repository.QuestionRepository;
import com.codestates.backend.pre_project.post.question.repository.QuestionTagRepository;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import com.sun.xml.bind.v2.TODO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.PostPersist;
import java.util.LinkedList;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionTagRepository questionTagRepository;
    private final QuestionRepository questionRepository;

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final CustomBeanUtils<Question> beanUtils;

    public QuestionService(QuestionTagRepository questionTagRepository, QuestionRepository questionRepository, MemberRepository memberRepository, MemberService memberService, CustomBeanUtils<Question> beanUtils) {
        this.questionTagRepository = questionTagRepository;
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.beanUtils = beanUtils;
    }
    public Question createQuestion(Question question){
        Member member = memberService.findMember(getCurrentMember().getMemberId());
        question.setMember(member);
        return questionRepository.save(question);
    }


    public Question updateQuestion(Question question) throws BusinessLogicException{
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        if(findQuestion.getMember().getMemberId() != getCurrentMember().getMemberId())
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION);

        Question updateQuestion = beanUtils.copyNonNullProperties(question, findQuestion);
        updateQuestion.setQuestionTags(question.getQuestionTags());


        return questionRepository.save(updateQuestion);
    }

    public Question findVerifiedQuestion(long questionId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        Question findQuestion =
                optionalQuestion.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }
    public Page<Question> findQuestions(int page, int size){
        return questionRepository.findAll(
                PageRequest.of(page,size, Sort.by("questionId").descending()));
    }

    public Question findQuestion(long questionId){
        return findVerifiedQuestion(questionId);

    }

    public void deleteQuestion(long questionId){
        Question findQuestion = findVerifiedQuestion(questionId);
        if(findQuestion.getMember().getMemberId() != getCurrentMember().getMemberId())
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION);
        questionRepository.delete(findQuestion);
    }

    //TODO
    //조회수
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
