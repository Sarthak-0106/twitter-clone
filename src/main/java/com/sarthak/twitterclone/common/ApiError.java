package com.sarthak.twitterclone.common;

import java.time.LocalDateTime;

// created this class for consistency in api errors
public class ApiError {
    private final LocalDateTime timestamp;
    private final int status;
    private final String message;
    private final String path;

    public ApiError(int status, String message, String path){
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
