package com.codestates.backend.pre_project.post.comment.service;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.comment.repository.CommentRepository;
import com.codestates.backend.pre_project.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final CustomBeanUtils<Comment> beanUtils;

    public CommentService(CommentRepository commentRepository, CustomBeanUtils<Comment> beanUtils) {
        this.commentRepository = commentRepository;
        this.beanUtils = beanUtils;
    }

    public Comment createComment(Comment comment) {

        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getCommentId());

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
}
