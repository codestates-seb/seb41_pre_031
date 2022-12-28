package com.codestates.backend.pre_project.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    COMMENT_EXISTS(409,"Comment Exists"),
    PROFILE_EXISTS(409, "Profile Exists"),
    PROFILE_NOT_FOUND(404, "Profile not found"),
    EDIT_NOT_ALLOWED(400, "No permission"),
    NO_PERMISSION(400, "No permission"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    ALREADY_LIKE_ADDED(404, "Already added to like"),
    DELETE_NOT_ALLOWED(400, "No permission"),
    QUESTION_NOT_FOUND(404, "Question not found");
    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
