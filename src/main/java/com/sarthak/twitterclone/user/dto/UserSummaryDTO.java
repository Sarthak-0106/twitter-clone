package com.sarthak.twitterclone.user.dto;

public class UserSummaryDTO {
    private long id;
    private String email;

    public UserSummaryDTO(long id, String email){
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
