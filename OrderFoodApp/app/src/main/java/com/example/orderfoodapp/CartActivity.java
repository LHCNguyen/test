package com.example.orderfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoodapp.Adaptor.CartAdaptor;
import com.example.orderfoodapp.Domain.FoodDomain;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ArrayList<FoodDomain> cartList;
    private ListView listViewCart;
    private CartAdaptor cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart);

        // Nhận giỏ hàng từ Intent
        Intent intent = getIntent();
        cartList = (ArrayList<FoodDomain>) intent.getSerializableExtra("cartList");

        // Gán ListView
        listViewCart = findViewById(R.id.listViewCart);

        // Kiểm tra xem giỏ hàng có trống không
        if (cartList != null && !cartList.isEmpty()) {
            // Khởi tạo adapter cho giỏ hàng
            cartAdapter = new CartAdaptor(this, cartList);
            listViewCart.setAdapter(cartAdapter);
        } else {
            // Nếu giỏ hàng trống, hiển thị thông báo
            Toast.makeText(this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
        }
    }
}
