package com.baziuk.SpringSecuredDemo.dto;

import lombok.Data;

import java.util.Date;
@Data
public class AppError {
    private Integer status;
    private String message;
    private Date timeStamp;

    public AppError(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timeStamp = new Date();
    }
}
