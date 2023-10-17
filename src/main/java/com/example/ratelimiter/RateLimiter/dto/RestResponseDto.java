package com.example.ratelimiter.RateLimiter.dto;


import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
@Data

public class RestResponseDto {

    private int status;

    private String message;

    private Object detail;

    private List<Object> column;

    private HashMap<String, String> response;


    public static RestResponseDto INSTANCE() {
        return new RestResponseDto();
    }


    public RestResponseDto success() {
        this.status = HttpStatus.OK.value();
        return this;
    }

    public RestResponseDto notFound() {
        this.status = HttpStatus.NOT_FOUND.value();
        return this;
    }

    public RestResponseDto validationError() {
        this.status = HttpStatus.BAD_REQUEST.value();
        return this;
    }

    public RestResponseDto internalServerError() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        return this;
    }


    public RestResponseDto message(String message) {
        this.message = message;
        return this;
    }

    public RestResponseDto detail(Object detail) {
        this.detail = detail;
        return this;
    }

    public RestResponseDto column(List<Object> column) {
        this.column = column;
        return this;
    }
}

