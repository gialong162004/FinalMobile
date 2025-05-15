package com.example.text.model;



public class SendOtpRequest {
    private String email;

    public SendOtpRequest(String email) {
        this.email = email;
    }

    // getter + setter
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

