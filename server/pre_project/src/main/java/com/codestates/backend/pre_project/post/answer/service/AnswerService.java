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
import com.codestates.backend.pre_project.post.question.repository.QuestionRepository;
import com.codestates.backend.pre_project.post.question.service.QuestionService;
import com.codestates.backend.pre_project.profile.entity.Profile;
import com.codestates.backend.pre_project.profile.service.ProfileService;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.codestates.backend.pre_project.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class AnswerService {

    private final QuestionService questionService;
    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final CustomBeanUtils<Answer> beanUtils;
    private final AnswerLikesService answerLikesService;
    private final MemberRepository memberRepository;
    private final ProfileService profileService;
    private final QuestionRepository questionRepository;


    public Answer createAnswer(Answer answer) {
        Member member = memberService.findMember(getCurrentMember().getMemberId());
        answer.setMember(member);
        Question question =questionService.findQuestion(answer.getQuestion().getQuestionId());
        answer.setQuestion(question);
        return answerRepository.save(answer);
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


    public List<Answer> findAnswers() {
        return answerRepository.findAll();
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
        Member requestMember = getCurrentMember();
        Profile findProfile = profileService.findProfile(answer.getMember().getMemberId());

        if (answerLikes.getIsClicked()!=Boolean.TRUE){
            if(answerLikes.getIsClicked()  == null){
                answerLikes.setIsClicked(Boolean.TRUE);
                answer.setAnswerLikes(answer.getAnswerLikes()+1);
                findProfile.setPoint(findProfile.getPoint()+10);
                requestMember.getProfile().setPoint(getCurrentMember().getProfile().getPoint()+2);
            }
            else{ //false -> true
                answerLikes.setIsClicked(Boolean.TRUE);
                answer.setAnswerLikes(answer.getAnswerLikes()+2);
                findProfile.setPoint(findProfile.getPoint()+20);
                requestMember.getProfile().setPoint(getCurrentMember().getProfile().getPoint()+3);
            }
        }

        answerLikes.setAnswer(answer);
        answerLikes.setMember(member);
        answerLikesService.saveAnswerLikes(answerLikes);
        profileService.updatePoint(findProfile);

        profileService.updatePoint(requestMember.getProfile());
        answerRepository.save(answer);
    }

    public void downLikes(Answer answer, Member member) {
        AnswerLikes answerLikes = answerLikesService.findByMemberAndAnswer(member, answer);

        Profile findProfile = profileService.findProfile(answer.getMember().getMemberId());
        Member requestMember = getCurrentMember();

        if (answerLikes.getIsClicked() != Boolean.FALSE){
            if(answerLikes.getIsClicked()  == null){ //null
                answerLikes.setIsClicked(Boolean.FALSE);
                answer.setAnswerLikes(answer.getAnswerLikes()-1);
                findProfile.setPoint(findProfile.getPoint()-10);
                requestMember.getProfile().setPoint(getCurrentMember().getProfile().getPoint()-1);

            }
            else{//true->false
                answerLikes.setIsClicked(Boolean.FALSE);
                answer.setAnswerLikes(answer.getAnswerLikes()-2);
                findProfile.setPoint(findProfile.getPoint()-20);
                requestMember.getProfile().setPoint(getCurrentMember().getProfile().getPoint()-3);
            }
        }
        answerLikes.setAnswer(answer);
        answerLikes.setMember(member);
        answerLikesService.saveAnswerLikes(answerLikes);
        profileService.updatePoint(findProfile);
        profileService.updatePoint(requestMember.getProfile());
        answerRepository.save(answer);
    }

    public void selectAnswer(Answer answer, Member member) {
        Profile findQuestionProfile = profileService.findProfile(answer.getQuestion().getMember().getMemberId());
        Profile findAnswerProfile = profileService.findProfile(answer.getMember().getMemberId());
        Question question = answer.getQuestion();
        if(member == answer.getQuestion().getMember() && answer.getQuestion().getIsSelectAnswer() != Boolean.TRUE) {
            if(answer.getAnswerSelected() != Boolean.TRUE) {
                answer.setAnswerSelected(Boolean.TRUE);
                findQuestionProfile.setPoint(findQuestionProfile.getPoint()+2);
                findAnswerProfile.setPoint(findAnswerProfile.getPoint()+15);

                profileService.updatePoint(findQuestionProfile);
                profileService.updatePoint(findAnswerProfile);
                question.setIsSelectAnswer(Boolean.TRUE);

            }
        }
        answerRepository.save(answer);
        questionRepository.save(question);
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
