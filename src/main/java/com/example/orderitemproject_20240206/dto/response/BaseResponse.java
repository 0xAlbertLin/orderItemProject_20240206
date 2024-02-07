package com.example.orderitemproject_20240206.dto.response;

import lombok.Data;

@Data
public class BaseResponse<T> {

    public String message;
    public Integer code;
    public T response;

    public BaseResponse(String message, Integer code, T response) {
        this.message = message;
        this.code = code;
        this.response = response;
    }

}
