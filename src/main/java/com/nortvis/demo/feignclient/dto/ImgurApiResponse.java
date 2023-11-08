package com.nortvis.demo.feignclient.dto;

import lombok.Data;

@Data
public class ImgurApiResponse<T> {
    private T data;
    private Boolean success;
    private Integer status;
}
