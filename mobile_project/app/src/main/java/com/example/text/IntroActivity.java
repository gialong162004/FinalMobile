package com.example.text;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        new Thread(() -> {
            int n = 8;
            try {
                do {
                    if (n >= 2000) {
                        Intent intent = new Intent(IntroActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish(); // Gọi finish() sau khi startActivity()
                        return;
                    }
                    Thread.sleep(100);
                    n += 100;
                } while (true);
            } catch (InterruptedException interruptedException) {
                IntroActivity.this.finish();
                Intent intent = new Intent(IntroActivity.this.getApplicationContext(), HomeActivity.class);
                IntroActivity.this.startActivity(intent);
            } catch (Throwable throwable) {
                IntroActivity.this.finish();
                Intent intent = new Intent(IntroActivity.this.getApplicationContext(), HomeActivity.class);
                IntroActivity.this.startActivity(intent);
                throw throwable;
            }
        }).start();

    }
}