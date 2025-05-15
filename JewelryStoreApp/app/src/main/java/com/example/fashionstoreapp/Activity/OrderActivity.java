package com.example.fashionstoreapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fashionstoreapp.Adapter.OrderFragmentAdapter;
import com.example.fashionstoreapp.Model.Order;
import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.OrderAPI;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    OrderFragmentAdapter orderFragmentAdapter;
    ImageView ivHome, ivUser, ivHistory;
    ConstraintLayout clOrder, clEmptyOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        AnhXa();
        appBarClick();
        CheckEmpty();

        // Set adapter chỉ hiển thị 1 fragment (All Order)
        FragmentManager fragmentManager = getSupportFragmentManager();
        orderFragmentAdapter = new OrderFragmentAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(orderFragmentAdapter);
    }

    private void CheckEmpty() {
        User user = ObjectSharedPreferences.getSavedObjectFromPreference(OrderActivity.this, "User", "MODE_PRIVATE", User.class);
        OrderAPI.orderAPI.getOrderByUserId(user.getId()).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.body() != null && response.body().isEmpty()) {
                    clOrder.setVisibility(View.GONE);
                    clEmptyOrder.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                // Xử lý lỗi nếu cần
            }
        });
    }

    private void appBarClick() {
        ivHome.setOnClickListener(v -> {
            startActivity(new Intent(OrderActivity.this, MainActivity.class));
            finish();
        });
        ivUser.setOnClickListener(v -> {
            startActivity(new Intent(OrderActivity.this, UserActivity.class));
            finish();
        });
        ivHistory.setOnClickListener(v -> {
            startActivity(new Intent(OrderActivity.this, OrderActivity.class));
            finish();
        });
    }

    private void AnhXa() {
        ivHome = findViewById(R.id.ivHome);
        ivUser = findViewById(R.id.ivUser);
        ivHistory = findViewById(R.id.ivHistory);
        viewPager2 = findViewById(R.id.viewPager2);
        clOrder = findViewById(R.id.clOrder);
        clEmptyOrder = findViewById(R.id.clEmptyOrder);
    }
}
