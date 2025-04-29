package com.example.text;



import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.text.client.ApiClient;
import com.example.text.model.SendOtpRequest;
import com.example.text.service.AuthApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetEmailActivity extends AppCompatActivity {
    private EditText etResetEmail;
    private Button btnSendOtp;
    private AuthApiService api;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_reset_email);

        etResetEmail = findViewById(R.id.etResetEmail);
        btnSendOtp   = findViewById(R.id.btnSendOtp);
        api          = ApiClient.getClient().create(AuthApiService.class);

        btnSendOtp.setOnClickListener(v -> {
            String email = etResetEmail.getText().toString().trim();
            if (email.isEmpty()) {
                etResetEmail.setError("Nhập email");
                return;
            }
            // Gửi JSON
            SendOtpRequest req = new SendOtpRequest(email);
            api.sendOtp(req).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> r) {
                    if (r.isSuccessful()) {
                        // Chuyển qua nhập OTP
                        Intent i = new Intent(ResetEmailActivity.this, EnterOtpActivity.class);
                        i.putExtra("email", email);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(ResetEmailActivity.this,
                                "Gửi OTP thất bại: " + r.code(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(ResetEmailActivity.this,
                            "Lỗi: " + t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
