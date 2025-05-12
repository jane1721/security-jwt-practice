package com.jane.securityjwtpractice.user.domain;

public enum Role {
    USER, ADMIN;

    public String getRoleName() {
        return "ROLE_" + this.name();
    }
}
