package com.projectMVC.entity.Valid;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ValidProfile {


    @Email(message = "Email not correct")
    @NotBlank(message = "Email can't be empty")
    private String email;

    @NotBlank(message = "Password cant be empty")
    private String password;

    @NotBlank(message = "Confirm password cant be empty")
    private String password2;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
