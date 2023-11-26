package com.renewr.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.renewr.global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.renewr.global.exception.GlobalErrorCode.SUCCESS;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonPropertyOrder({"status", "code", "message", "data"})
public class BaseResponse<T> {

    private int status;

    private String code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(T result) {
        this.status = SUCCESS.getStatus().value();
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
        this.data = result;
    }

    private BaseResponse(ErrorCode code) {
        this.status = code.getStatus().value();
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public BaseResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static BaseResponse<?> from(ErrorCode code) {
        return new BaseResponse<>(code);
    }

    public static BaseResponse<?> of(ErrorCode code, String message) {
        return new BaseResponse<>(code.getStatus().value(), code.getCode(), message);
    }

}
