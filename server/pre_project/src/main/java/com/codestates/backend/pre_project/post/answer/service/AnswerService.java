package com.codestates.backend.pre_project.post.answer.service;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.likes.answer.AnswerLikes;
import com.codestates.backend.pre_project.likes.answer.AnswerLikesService;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.answer.repository.AnswerRepository;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.service.QuestionService;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AnswerService {

    private final QuestionService questionService;
    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final CustomBeanUtils<Answer> beanUtils;
    private final AnswerLikesService answerLikesService;
//    삭제예정, 멤버서비스 통해서 구현.

//    private final QuestionService questionService;

    public AnswerService(QuestionService questionService, AnswerRepository answerRepository, MemberService memberService
                         , CustomBeanUtils<Answer> beanUtils, AnswerLikesService answerLikesService, MemberRepository memberRepository) {
        this.questionService = questionService;
        this.answerRepository = answerRepository;
        this.memberService = memberService;
        this.beanUtils = beanUtils;
        this.answerLikesService = answerLikesService;
    }

    public Answer createAnswer(Answer answer) {
        Member member = memberService.findMember(getCurrentMember().getMemberId());
        answer.setMember(member);
        Question question =questionService.findQuestion(answer.getQuestion().getQuestionId());
        answer.setQuestion(question);
//        현재 유저
        return answerRepository.save(answer);
//        updatePoint(savedAnswer); 포인트 증가
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        Member postMember = memberService.findVerifiedMember(findAnswer.getMember().getMemberId());
        if (memberService.getCurrentMember().getMemberId() != postMember.getMemberId())
            throw new BusinessLogicException(ExceptionCode.EDIT_NOT_ALLOWED);
//        작성자에게만 수정 권한을 주는 코드. 멤버서비스에 코드 구현 필요

        Answer updateAnswer = beanUtils.copyNonNullProperties(answer, findAnswer);

        return answerRepository.save(updateAnswer);
    }

    public void deleteAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        Member postMember = memberService.findVerifiedMember(findAnswer.getMember().getMemberId());
        if (memberService.getCurrentMember().getMemberId() != postMember.getMemberId())
            throw new BusinessLogicException(ExceptionCode.DELETE_NOT_ALLOWED);
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
                        new BusinessLogicException(ExceptionCode.NO_PERMISSION));
        return findAnswer;
    }
//    Author의 memberId가 사용자와 다를 때 글 수정이 되지 않도록 한다.

    public void addLikes(Answer answer, Member member) {
        AnswerLikes answerLikes = answerLikesService.findByMemberAndAnswer(member, answer);

        if (answerLikes.getCount()!=1){
            answerLikes.setCount(answerLikes.getCount()+1);
            answer.setAnswerLikes(answer.getAnswerLikes()+1);
        }
        answerLikes.setAnswer(answer);
        answerLikes.setMember(member);
        answerLikesService.saveAnswerLikes(answerLikes);
        Question question = answer.getQuestion();
//        questionService.downViewCount(question); 조회수 2개씩 올라가는 버그 있으면 사용
        answerRepository.save(answer);
    }

    public void downLikes(Answer answer, Member member) {
        AnswerLikes answerLikes = answerLikesService.findByMemberAndAnswer(member, answer);

        if (answerLikes.getCount()!=-1){
            answerLikes.setCount(answerLikes.getCount()-1);
            answer.setAnswerLikes(answer.getAnswerLikes()-1);
        }
        answerLikes.setAnswer(answer);
        answerLikes.setMember(member);
        answerLikesService.saveAnswerLikes(answerLikes);
        Question question = answer.getQuestion();
//        questionService.downViewCount(question); 조회수 2개씩 올라가는 버그 있으면 사용
        answerRepository.save(answer);
    }
}
