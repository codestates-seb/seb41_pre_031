package com.codestates.backend.pre_project.post.question.service;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.likes.question.QuestionLikes;
import com.codestates.backend.pre_project.likes.question.QuestionLikesService;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.member.repository.MemberRepository;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.QuestionTag;
import com.codestates.backend.pre_project.post.question.repository.QuestionRepository;
import com.codestates.backend.pre_project.post.question.repository.QuestionTagRepository;
import com.codestates.backend.pre_project.profile.entity.Profile;
import com.codestates.backend.pre_project.profile.service.ProfileService;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.PostPersist;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionTagRepository questionTagRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final CustomBeanUtils<Question> beanUtils;
    private final QuestionLikesService questionLikesService;
    private final ProfileService profileService;



    public Question createQuestion(Question question){
        Member member = memberService.findMember(getCurrentMember().getMemberId());
        question.setMember(member);
        question.setMemberName(member.getMemberName());
        Question saveQuestion = questionRepository.save(question);

        return  saveQuestion;
    }



    public Question updateQuestion(Question question) throws BusinessLogicException{
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        if(findQuestion.getMember().getMemberId() != getCurrentMember().getMemberId())
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION);


        questionTagRepository.deleteByQuestion(findQuestion);


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
    public List<Question> findQuestions(){
        return questionRepository.findAll();
    }

    public Question findQuestion(long questionId){

        return findVerifiedQuestion(questionId);

    }

    public void addLikes(Question question, Member member) {
        QuestionLikes questionLikes = questionLikesService.findByMemberAndQuestion(member, question);
        Member requestMember = getCurrentMember();
        Profile findProfile = profileService.findProfile(question.getMember().getMemberId());

        if (questionLikes.getIsClicked()!=Boolean.TRUE) {
            if (questionLikes.getIsClicked()  == null) {
                questionLikes.setIsClicked(Boolean.TRUE);
                question.setQuestionLikes(question.getQuestionLikes() + 1);
                findProfile.setPoint(findProfile.getPoint()+10);
                requestMember.getProfile().setPoint(requestMember.getProfile().getPoint()+2);
            }else { //false ->true
                questionLikes.setIsClicked(Boolean.TRUE);
                question.setQuestionLikes(question.getQuestionLikes()+2);
                findProfile.setPoint(findProfile.getPoint()+20);
                requestMember.getProfile().setPoint(requestMember.getProfile().getPoint()+3);
            }

            questionLikes.setQuestion(question);
            questionLikes.setMember(member);
            questionLikesService.saveQuestionLikes(questionLikes);
            profileService.updatePoint(findProfile);
            questionRepository.save(question);
            profileService.updatePoint(requestMember.getProfile());
        }
    }

    public void downLikes(Question question, Member member) {
        QuestionLikes questionLikes = questionLikesService.findByMemberAndQuestion(member, question);
        Profile findProfile = profileService.findProfile(question.getMember().getMemberId());
        Member requestMember = getCurrentMember();

        if (questionLikes.getIsClicked() != FALSE) {
            if (questionLikes.getIsClicked() == null) {
                questionLikes.setIsClicked(FALSE);
                question.setQuestionLikes(question.getQuestionLikes()-1);
                findProfile.setPoint(findProfile.getPoint()-10);
                requestMember.getProfile().setPoint(getCurrentMember().getProfile().getPoint()-2);
            }
            else{//true -> false
                questionLikes.setIsClicked(Boolean.FALSE);
                question.setQuestionLikes(question.getQuestionLikes()-2);
                findProfile.setPoint(findProfile.getPoint()-20);
                requestMember.getProfile().setPoint(getCurrentMember().getProfile().getPoint()-3);
            }
        }
        questionLikes.setQuestion(question);
        questionLikes.setMember(member);
        questionLikesService.saveQuestionLikes(questionLikes);


//        questionService.downViewCount(question); 조회수 2개씩 올라가는 버그 있으면 사용
//        profileService.updatePoint(findProfile);  //questionLikes -> member ->profile 로 전파?  ->작성자
        profileService.updatePoint(requestMember.getProfile()); //-> 싫어요 누른사람
        questionRepository.save(question);
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
