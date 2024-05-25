package com.example.project_dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrException {
    USER_EXISTED(101,"User existed"),
    USER_NO_EXISTED(102,"User not existed"),
    INVALID_USERNAME(103,"Username must be at 3 character"),
    INVALID_PASSWORD(104,"Password must be at 4 character"),
    INVALID_KEY(105,"Invalid message key err"),
    ROLE_NO_EXISTED(106,"role not existed"),
    ERR_EXCEPTION(99,"err exception")
    ;
    private int code;
    private String message;
}
