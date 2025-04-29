package com.example.text;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.text.client.ApiClient;
import com.example.text.model.ResetPasswordRequest;
import com.example.text.service.AuthApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordActivity extends AppCompatActivity {
    private EditText etNewPass, etConfirm;
    private Button btnReset;
    private AuthApiService api;
    private String email, otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        // Ánh xạ view
        etNewPass = findViewById(R.id.etNewPass);
        etConfirm = findViewById(R.id.etConfirm);
        btnReset  = findViewById(R.id.btnReset);

        // Nhận email và otp từ Intent
        email = getIntent().getStringExtra("email");
        otp   = getIntent().getStringExtra("otp");

        // Tạo Retrofit API client
        api = ApiClient.getClient().create(AuthApiService.class);

        // Xử lý sự kiện nút Reset Password
        btnReset.setOnClickListener(v -> {
            String p1 = etNewPass.getText().toString().trim();
            String p2 = etConfirm.getText().toString().trim();

            // Kiểm tra các trường nhập vào
            if (p1.isEmpty() || p2.isEmpty()) {
                Toast.makeText(this, "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!p1.equals(p2)) {
                etConfirm.setError("Không khớp");
                return;
            }

            // Tạo đối tượng ResetPasswordRequest
            ResetPasswordRequest body = new ResetPasswordRequest(email, otp, p1);

            // Gọi API reset password
            api.resetPassword(body).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(NewPasswordActivity.this,
                                "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
                        finish(); // Quay lại màn hình Login
                    } else {
                        Toast.makeText(NewPasswordActivity.this,
                                "Thất bại: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(NewPasswordActivity.this,
                            "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
