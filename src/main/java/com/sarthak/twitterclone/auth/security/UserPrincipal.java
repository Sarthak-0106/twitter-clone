package com.sarthak.twitterclone.auth.security;

// a class that will be used to store the context in the security context, and we can get
// the userId and role by storing this principal object in the security context
public class UserPrincipal {

    private final Long userId;
    private final String role;

    public UserPrincipal(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }
}
