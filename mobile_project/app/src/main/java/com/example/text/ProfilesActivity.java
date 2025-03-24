package com.example.text;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class ProfilesActivity extends AppCompatActivity implements View.OnClickListener {
    TextView id, userName, userEmail, gender;
    Button btnLogout;
    ImageView imageViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            id = findViewById(R.id.textViewId);
            userName = findViewById(R.id.textViewUsername);
            userEmail = findViewById(R.id.textViewEmail);
            gender = findViewById(R.id.textViewGender);
            btnLogout = findViewById(R.id.btnLogout);
            imageViewProfile = findViewById(R.id.imgProfile);

            User user = SharedPrefManager.getInstance(this).getUser();

            id.setText(String.valueOf(user.getId()));
            userEmail.setText(user.getEmail());
            gender.setText(user.getGender());
            userName.setText(user.getName());

            Glide.with(getApplicationContext())
                    .load(user.getImages())
                    .into(imageViewProfile);

            btnLogout.setOnClickListener(this);
        } else {
            Intent intent = new Intent(ProfilesActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnLogout)) {
            SharedPrefManager.getInstance(getApplicationContext()).logout();
            Intent intent = new Intent(ProfilesActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
