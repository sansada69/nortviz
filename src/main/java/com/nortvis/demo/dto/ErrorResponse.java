package com.nortvis.demo.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private Integer status;

    public ErrorResponse(String message, Integer status) {
        this.message = message;
        this.status = status;
    }
}
