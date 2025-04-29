package com.example.text;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.text.client.ApiClient;
import com.example.text.dto.LoginRequest;
import com.example.text.dto.User;
import com.example.text.service.AuthApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvForgot, tvRegister;
    private AuthApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin    = findViewById(R.id.btnLogin);
        tvForgot    = findViewById(R.id.tvForgotPassword);
        tvRegister       = findViewById(R.id.tvRegister);
        api         = ApiClient.getClient().create(AuthApiService.class);

        btnLogin.setOnClickListener(v -> {
            String email = etUsername.getText().toString().trim();
            String pass  = etPassword.getText().toString().trim();
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }
            api.login(new LoginRequest(email, pass))
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> c, Response<User> r) {
                            if (r.isSuccessful()) {
                                Toast.makeText(LoginActivity.this,
                                        "Chào " + r.body().getHoTen(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        "Sai thông tin đăng nhập",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<User> c, Throwable t) {
                            Toast.makeText(LoginActivity.this,
                                    "Lỗi: "+t.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        tvForgot.setOnClickListener(v -> {
            startActivity(new Intent(this, ResetEmailActivity.class));
        });
        // Khi nhấn Đăng ký tài khoản mới
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
