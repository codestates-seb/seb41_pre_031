package com.codestates.backend.pre_project.post.comment.service;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.comment.repository.CommentRepository;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.codestates.backend.pre_project.member.entity.Member;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final CustomBeanUtils<Comment> beanUtils;
    private final MemberService memberService;

    public CommentService(CommentRepository commentRepository, CustomBeanUtils<Comment> beanUtils, MemberService memberService) {
        this.commentRepository = commentRepository;
        this.beanUtils = beanUtils;
        this.memberService = memberService;
    }

    public Comment createComment(Comment comment) {

        return commentRepository.save(comment);
    }

    public Comment createQuestionComment(Comment comment) {
        Member member = memberService.findMember(getCurrentMember().getMemberId());
        comment.setMember(member);
        Question question = questionService.findQuestion(comment.getQuestion().getQuestionId());
        comment.setQuestion(question);
        comment.setCommentRegDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getCommentId());
        Member commentMember = memberService.findVerifiedMember(findComment.getMember().getMemberId());
        if (memberService.getCurrentMember().getMemberId() != commentMember.getMemberId())
            throw new BusinessLogicException(ExceptionCode.EDIT_NOT_ALLOWED);

        Comment updateComment = beanUtils.copyNonNullProperties(comment, findComment);

        return commentRepository.save(updateComment);
    }

    public Comment findComment(long commentId) {return findVerifiedComment(commentId);}

    public Page<Comment> findComments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(
                page,size, Sort.by("commentId").descending()));
    }

    public void deleteComment(long commentId) {
        Comment findComment = findVerifiedComment(commentId);
        Member commentMember = memberService.findVerifiedMember(findComment.getMember().getMemberId());
        if (memberService.getCurrentMember().getMemberId() != commentMember.getMemberId())
            throw new BusinessLogicException(ExceptionCode.EDIT_NOT_ALLOWED);
//        작성자에게만 수정 권한을 주는 코드. 멤버서비스에 코드 구현 필요

        commentRepository.delete(findComment);
    }

    private Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment =
                commentRepository.findById(commentId);
        Comment findComment =
                optionalComment.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.COMMENT_EXISTS));
        return findComment;
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
