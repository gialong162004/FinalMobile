package com.example.fashionstoreapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionstoreapp.Adapter.ProductAdapter;
import com.example.fashionstoreapp.Model.Category;
import com.example.fashionstoreapp.Model.Product;
import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.ProductAPI;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    ProductAdapter productAdapter;
    EditText etSearch;

    TextView tvResult;
    ImageView ivSearch, ivHome, ivUser, ivCart, ivHistory;
    RecyclerView rcProduct;
    boolean isGuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        isGuest = ObjectSharedPreferences.getSavedObjectFromPreference(ProductsActivity.this, "User", "MODE_PRIVATE", User.class) == null;
        AnhXa();
        appBarClick();
        Category category = (Category) getIntent().getSerializableExtra("category");
        if (category == null){
            etSearch.setText(getIntent().getSerializableExtra("searchContent").toString());
            loadProductSearch();
        }
        else {
            loadProductCategory(category);
        }
        ivSearchClick();
    }

    private void loadProductCategory(Category category) {
        tvResult.setText(category.getProduct().size()+ " Results of category: " + category.getCategory_Name());

        productAdapter=new ProductAdapter(category.getProduct(),ProductsActivity.this, isGuest);
        rcProduct.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
        rcProduct.setLayoutManager(layoutManager);
        rcProduct.setAdapter(productAdapter);
    }

    private void ivSearchClick() {
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this, ProductsActivity.class);
                intent.putExtra("searchContent", etSearch.getText().toString());
                startActivity(intent);
            }
        });

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Intent intent = new Intent(ProductsActivity.this, ProductsActivity.class);
                    intent.putExtra("searchContent", etSearch.getText().toString());
                    intent.putExtra("category_id", "-1");
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void loadProductSearch() {
        ProductAPI.productApi.search(etSearch.getText().toString()).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> newProductsList = response.body();
                tvResult.setText(newProductsList.size() +" Results");
                productAdapter=new ProductAdapter(newProductsList,ProductsActivity.this, isGuest);
                rcProduct.setHasFixedSize(true);
                GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
                rcProduct.setLayoutManager(layoutManager);
                rcProduct.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("====", "Call API Search fail");
            }
        });
    }

    private void appBarClick() {
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsActivity.this, MainActivity.class));
                finish();
            }
        });
    }
    private void AnhXa() {
        rcProduct = findViewById(R.id.view);
        etSearch = findViewById(R.id.etSearch);
        ivSearch = findViewById(R.id.ivSearch);
        //appBar
        ivHome = findViewById(R.id.ivHome);
        //
        tvResult = findViewById(R.id.tvResult);
    }
}
