package com.example.fashionstoreapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;

public class IntroActivity extends AppCompatActivity {
    TextView tvStart;
    VideoView videoView; // Thêm VideoView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        AnhXa();
        playVideo(); // Gọi hàm phát video
        tvStartClick();
    }

    private void AnhXa() {
        tvStart = findViewById(R.id.tvStart);
        videoView = findViewById(R.id.videoViewIntro); // Gắn videoView
    }

    private void playVideo() {
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro_video); // Tên file trong raw
        videoView.setVideoURI(videoUri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true); // Lặp video
                mp.setVolume(0f, 0f); // Tắt tiếng nếu muốn
            }
        });

        videoView.start();
    }

    private void tvStartClick() {
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User isLoged = ObjectSharedPreferences.getSavedObjectFromPreference(IntroActivity.this, "User", "MODE_PRIVATE", User.class);
                if (isLoged != null) {
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                }
                finish();
            }
        });
    }
}
