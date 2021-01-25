package com.lp.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 3724114773472447545L;

    private HttpStatus status;

    private String code;

    private T data;

    private String msg;

    public BaseResponse() {
        this(HttpStatus.OK, "1");
    }

    public BaseResponse(T data) {
        this();
        this.data = data;
    }

    public BaseResponse(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    public BaseResponse(HttpStatus status, String code, String msg) {
        this(status, code);
        this.msg = msg;
    }

}
