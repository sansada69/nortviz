package com.nortvis.demo.dto;

import com.nortvis.demo.constants.ApiConstants;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private T data;
    private String message = ApiConstants.SUCCESS;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(String message) {
        this.message = message;
    }
}
