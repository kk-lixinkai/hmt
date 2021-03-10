package com.mybestcoding.hmt.constant;

public enum Status {
    OK("ok"),
    ERROR("error");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
