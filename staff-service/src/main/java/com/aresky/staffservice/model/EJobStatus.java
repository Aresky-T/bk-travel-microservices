package com.aresky.staffservice.model;

public enum EJobStatus {
    CURRENT_JOB("Công việc hiện tại"),
    END_JOB("Công việc trong quá khứ");

    private final String value;

    public String getValue(){
        return value;
    }

    private EJobStatus(String value) {
        this.value = value;
    }
}
