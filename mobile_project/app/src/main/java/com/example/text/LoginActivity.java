package com.example.text;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private ProgressBar progressBar;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Kiểm tra nếu đã đăng nhập
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            navigateToProfile();
        }

        // Ánh xạ UI
        progressBar = findViewById(R.id.progressBar);
        etEmail = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> userLogin());

        findViewById(R.id.tvRegister).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        });
    }

    private void userLogin() {
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Vui lòng nhập email");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Vui lòng nhập mật khẩu");
            etPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            Log.e("JSON Error", "Lỗi tạo JSON: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Lỗi tạo dữ liệu!", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                jsonBody,
                response -> {
                    progressBar.setVisibility(View.GONE);
                    Log.d("Response", "Server JSON: " + response.toString());

                    try {
                        boolean error = response.getBoolean("error");
                        String message = response.getString("message");

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        if (!error) {
                            Log.d("LoginSuccess", "Chuyển hướng đến ProfilesActivity");

                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(new User(
                                    response.getJSONObject("user").getInt("id"),
                                    response.getJSONObject("user").getString("username"),
                                    response.getJSONObject("user").getString("email"),
                                    response.getJSONObject("user").getString("gender"),
                                    response.getJSONObject("user").getString("images")
                            ));

                            navigateToProfile();
                        }
                    } catch (JSONException e) {
                        Log.e("JSON Error", "Lỗi phân tích JSON: " + e.getMessage());
                        Toast.makeText(getApplicationContext(), "Lỗi xử lý dữ liệu từ server!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    progressBar.setVisibility(View.GONE);
                    Log.e("Volley Error", "Lỗi kết nối: " + error.toString());
                    Toast.makeText(getApplicationContext(), "Lỗi kết nối hoặc dữ liệu không hợp lệ!", Toast.LENGTH_SHORT).show();
                }
        );

        VolleySingle.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void navigateToProfile() {
        Log.d("Navigation", "Chuyển hướng đến ProfilesActivity");
        startActivity(new Intent(LoginActivity.this, ProfilesActivity.class));
        finish();
    }
}
