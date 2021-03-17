package com.mybestcoding.hmt.constant;

public enum NodeStatus {
    OK("ok"),
    ERROR("error");

    private String status;

    NodeStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
