package com.example.text;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ProfileActivity extends Activity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imgAvatar;
    private Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgAvatar = findViewById(R.id.imgAvatar);
        btnUpload = findViewById(R.id.btnUpload);

        // Xử lý sự kiện di chuột vào ảnh
        imgAvatar.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_HOVER_ENTER:
                        // Hiển thị nút upload khi chuột vào ảnh
                        btnUpload.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.ACTION_HOVER_EXIT:
                        // Ẩn nút upload khi chuột rời khỏi ảnh
                        btnUpload.setVisibility(View.GONE);
                        return true;
                }
                return false;
            }
        });

        // Xử lý sự kiện khi người dùng nhấn vào nút upload
        btnUpload.setOnClickListener(v -> {
            // Mở bộ chọn ảnh (mã mở bộ chọn ảnh giống như phần trước)
            openImagePicker();
        });
    }

    // Mở bộ chọn ảnh
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Nhận kết quả chọn ảnh từ Intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                // Chuyển đổi Uri thành Bitmap và hiển thị lên ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imgAvatar.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Lỗi tải ảnh", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
