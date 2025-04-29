package com.example.text.dto;

public class LoginRequest {
    private String email;
    private String matKhau;

    public LoginRequest(String email, String matKhau) {
        this.email = email;
        this.matKhau = matKhau;
    }

    // Getter Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}

