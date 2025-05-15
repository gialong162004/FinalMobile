package com.example.text;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.text.client.ApiClient;
import com.example.text.dto.User;
import com.example.text.service.AuthApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etAddress, etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Khởi tạo các View
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Set sự kiện cho nút Đăng ký
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu người dùng nhập vào
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                String password = etPassword.getText().toString();

                // Kiểm tra các trường nhập vào (bạn có thể thêm điều kiện kiểm tra hợp lệ)
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo đối tượng User từ dữ liệu nhập vào
                User user = new User();
                user.setHoTen(name);
                user.setEmail(email);
                user.setSdt(phone);
                user.setDiaChi(address);
                user.setMatKhau(password);
                user.setAvatar("link_avatar");  // Có thể thay đổi thành một URL ảnh nếu cần

                // Tạo Retrofit instance và gọi API
                AuthApiService apiService = ApiClient.getClient().create(AuthApiService.class);
                apiService.register(user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            // Nếu đăng ký thành công
                            Log.d("Register", "Đăng ký thành công: " + response.body().getEmail());
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Nếu đăng ký thất bại
                            Log.e("Register", "Đăng ký thất bại");
                            Toast.makeText(RegisterActivity.this, "Đăng ký thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // Nếu có lỗi khi kết nối API
                        Log.e("Register", "Lỗi: " + t.getMessage());
                        Toast.makeText(RegisterActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

