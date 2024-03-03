package com.example.proje.core.results;

import lombok.Data;

@Data
public class Result {

    private boolean success;
    private String message;

    public Result(boolean success){
        this.success = success;
    }
    public Result(boolean success, String message){
        this.success = success;
        this.message = message;
    }

}
