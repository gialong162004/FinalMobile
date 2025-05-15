package com.example.fashionstoreapp.Activity;

import static java.lang.Integer.parseInt;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fashionstoreapp.Adapter.SliderAdapter;
import com.example.fashionstoreapp.Model.Cart;
import com.example.fashionstoreapp.Model.Product;
import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.CartAPI;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetailActivity extends AppCompatActivity {
    TextView tvTitle, tvdescription, tvPrice, tvTotalPrice, tvSold, tvAvailable, tvNumber;
    ImageView ivMinus, ivPlus, ivAddToCart;
    Product product;

    ConstraintLayout clBack;

    boolean isGuest = false; // biến lưu trạng thái khách

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        product = (Product) getIntent().getSerializableExtra("product");
        isGuest = getIntent().getBooleanExtra("isGuest", false);

        AnhXa();
        LoadProduct();
        ivMinusClick();
        ivPlusClick();
        tvAddToCartClick();
        clBackClick();

        // Nếu là Guest, disable nút thêm giỏ hàng
        if (isGuest) {
            ivAddToCart.setColorFilter(Color.GRAY);
            ivAddToCart.setEnabled(false);
            ivAddToCart.setOnClickListener(v -> {
                Toast.makeText(ShowDetailActivity.this, "Vui lòng đăng nhập để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void clBackClick() {
        clBack.setOnClickListener(v -> onBackPressed());
    }

    private void tvAddToCartClick() {
        if (isGuest) {
            // Đã xử lý ở trên rồi, không cho thêm giỏ hàng
            return;
        }

        ivAddToCart.setOnClickListener(v -> {
            User user = ObjectSharedPreferences.getSavedObjectFromPreference(ShowDetailActivity.this, "User", "MODE_PRIVATE", User.class);
            int count = parseInt(tvNumber.getText().toString());
            CartAPI.cartAPI.addToCart(user.getId(), product.getId(), count).enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    Cart cart = response.body();
                    if(cart !=null){
                        Toast.makeText(ShowDetailActivity.this.getApplicationContext(), "Thêm vào giỏ thành công", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {
                    Toast.makeText(ShowDetailActivity.this.getApplicationContext(), "Thêm vào giỏ thất bại", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void LoadProduct() {
        LoadImage();
        tvTitle.setText(product.getProduct_Name());
        tvdescription.setText(product.getDescription());
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        tvPrice.setText(en.format(product.getPrice()));
        tvSold.setText(String.valueOf(product.getSold()));
        tvAvailable.setText(String.valueOf(product.getQuantity()));
        tvTotalPrice.setText(en.format(product.getPrice()));
        tvNumber.setText("1");
    }

    private void LoadImage() {
        SliderView sliderView = findViewById(R.id.imageSlider);
        SliderAdapter adapter = new SliderAdapter(ShowDetailActivity.this, product.getProductImage());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();
    }

    private void ivPlusClick() {
        ivPlus.setOnClickListener(v -> {
            int number = parseInt(tvNumber.getText().toString()) + 1;
            if (number <= product.getQuantity()) {
                tvNumber.setText(String.valueOf(number));
                Locale localeEN = new Locale("en", "EN");
                NumberFormat en = NumberFormat.getInstance(localeEN);
                tvTotalPrice.setText(en.format(product.getPrice() * number));
            }
        });
    }

    private void ivMinusClick() {
        ivMinus.setOnClickListener(v -> {
            int number = parseInt(tvNumber.getText().toString()) - 1;
            if (number >= 1) {
                tvNumber.setText(String.valueOf(number));
                Locale localeEN = new Locale("en", "EN");
                NumberFormat en = NumberFormat.getInstance(localeEN);
                tvTotalPrice.setText(en.format(product.getPrice() * number));
            }
        });
    }

    private void AnhXa() {
        ivAddToCart = findViewById(R.id.ivAddToCart);
        tvTitle = findViewById(R.id.tvTitle);
        tvdescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvSold = findViewById(R.id.tvSold);
        tvAvailable = findViewById(R.id.tvAvailable);
        tvNumber = findViewById(R.id.tvNumber);
        ivMinus = findViewById(R.id.ivMinus);
        ivPlus = findViewById(R.id.ivPlus);
        clBack = findViewById(R.id.clBack);
    }
}
