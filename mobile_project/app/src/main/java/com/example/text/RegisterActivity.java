package com.example.text;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        progressBar = findViewById(R.id.progressBar);

        findViewById(R.id.btnRegister).setOnClickListener(view -> userRegister());
    }

    private void userRegister() {
        final String username = etUsername.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        if (!validateInputs(username, email, password)) return;

        progressBar.setVisibility(View.VISIBLE);

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Lỗi khi tạo dữ liệu!", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, Constants.URL_REGISTER, jsonBody,
                this::handleResponse,
                this::handleError) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        VolleySingle.getInstance(this).addToRequestQueue(jsonRequest);
    }

    private boolean validateInputs(String username, String email, String password) {
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Vui lòng nhập tên đăng nhập");
            etUsername.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Vui lòng nhập email hợp lệ");
            etEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            etPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void handleResponse(JSONObject response) {
        progressBar.setVisibility(View.GONE);
        try {
            // 🛠 In JSON trả về từ API để debug
            System.out.println("Response từ server: " + response.toString());

            if (!response.getBoolean("error")) {
                Toast.makeText(getApplicationContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Lỗi xử lý dữ liệu từ server!", Toast.LENGTH_SHORT).show();
        }
    }


    private void handleError(Throwable error) {
        progressBar.setVisibility(View.GONE);

        String message = "Lỗi kết nối!";
        if (error instanceof com.android.volley.TimeoutError) {
            message = "Lỗi timeout! Kiểm tra kết nối internet.";
        } else if (error instanceof com.android.volley.NoConnectionError) {
            message = "Không có kết nối internet!";
        } else if (error instanceof com.android.volley.AuthFailureError) {
            message = "Lỗi xác thực!";
        } else if (error instanceof com.android.volley.ServerError) {
            message = "Lỗi máy chủ!";
        } else if (error instanceof com.android.volley.NetworkError) {
            message = "Lỗi mạng!";
        } else if (error instanceof com.android.volley.ParseError) {
            message = "Lỗi phân tích dữ liệu!";
        }

        // 🛠 Ghi Log lỗi
        error.printStackTrace();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
