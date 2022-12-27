package com.codestates.backend.pre_project.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    COMMENT_EXISTS(409,"Comment Exists"),
    PROFILE_EXISTS(409, "Profile Exists"),
    PROFILE_NOT_FOUND(404, "Profile not found"),
    //COFFEE_NOT_FOUND(404, "Coffee not found"),
    //COFFEE_CODE_EXISTS(409, "Coffee Code exists"),
    //ORDER_NOT_FOUND(404, "Order not found"),
    //CANNOT_CHANGE_ORDER(403, "Order can not change"),
    //NOT_IMPLEMENTATION(501, "Not Implementation"),
    //INVALID_MEMBER_STATUS(400, "Invalid member status"),  // TO 추가된 부분

    EDIT_NOT_ALLOWED(400, "No permission"),

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
