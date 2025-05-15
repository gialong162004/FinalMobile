package com.example.text.model;



public class ResetPasswordRequest {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    private String otpCode;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    private String newPassword;

    public ResetPasswordRequest(String email, String otpCode, String newPassword) {
        this.email = email;
        this.otpCode = otpCode;
        this.newPassword = newPassword;
    }
    // getters/setters...

}

