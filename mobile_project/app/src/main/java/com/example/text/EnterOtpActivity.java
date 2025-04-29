package com.example.text;



import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class EnterOtpActivity extends AppCompatActivity {
    private EditText etOtp;
    private Button btnNext;
    private String email;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_enter_otp);

        etOtp   = findViewById(R.id.etOtp);
        btnNext = findViewById(R.id.btnNext);
        email   = getIntent().getStringExtra("email");

        btnNext.setOnClickListener(v -> {
            String otp = etOtp.getText().toString().trim();
            if (otp.length() != 6) {
                etOtp.setError("OTP 6 chữ số");
                return;
            }
            Intent i = new Intent(EnterOtpActivity.this, NewPasswordActivity.class);
            i.putExtra("email", email);
            i.putExtra("otp", otp);
            startActivity(i);
        });
    }
}

