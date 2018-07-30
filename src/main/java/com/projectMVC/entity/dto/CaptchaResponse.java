package com.projectMVC.entity.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponse {

    private boolean success;

    @JsonAlias("error-codes")
    private Set<String> errorCpdes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Set<String> getErrorCpdes() {
        return errorCpdes;
    }

    public void setErrorCpdes(Set<String> errorCpdes) {
        this.errorCpdes = errorCpdes;
    }
}
