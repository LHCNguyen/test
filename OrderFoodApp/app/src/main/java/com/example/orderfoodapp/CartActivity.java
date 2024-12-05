package com.example.orderfoodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoodapp.Domain.FoodDomain;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ListView listViewCart;
    private CartAdapter cartAdapter;
    private Button butonOnPlacerOrder;
    private ArrayList<FoodDomain> cartList;
    private TextView textViewTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart);

        listViewCart = findViewById(R.id.listViewCart);
        textViewTotalPrice = findViewById(R.id.textViewCartTitle);
        butonOnPlacerOrder = findViewById(R.id.buttonPlaceOrder);

        butonOnPlacerOrder.setOnClickListener(v -> {
            double totalPrice = 0;
            for (FoodDomain food : cartList) {
                totalPrice += food.getFee() * food.getQuantity();
            }
            // Truyền tổng giá sang màn hình thanh toán
            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            intent.putExtra("totalPrice", totalPrice); // Truyền tổng tiền
            startActivity(intent);
        });

        // Lấy giỏ hàng từ Intent
        Intent intent = getIntent();
        cartList = (ArrayList<FoodDomain>) intent.getSerializableExtra("cartList");

        // Thiết lập adapter cho ListView
        cartAdapter = new CartAdapter(this, cartList);
        listViewCart.setAdapter(cartAdapter);

        // Cập nhật tổng giá trị giỏ hàng
        updateTotalPrice();

    }

    public void updateTotalPrice() {
        double totalPrice = 0;
        for (FoodDomain food : cartList) {
            totalPrice += food.getFee() * food.getQuantity();
        }
        textViewTotalPrice.setText("Tổng giá: " + totalPrice + " VND");
    }

    // Adapter để hiển thị các món ăn trong giỏ hàng
    public class CartAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<FoodDomain> cartList;

        public CartAdapter(Context context, ArrayList<FoodDomain> cartList) {
            this.context = context;
            this.cartList = cartList;
        }

        @Override
        public int getCount() {
            return cartList.size();
        }

        @Override
        public Object getItem(int position) {
            return cartList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.viewholder_cart, null);
            }

            ImageView imageViewFood = convertView.findViewById(R.id.imageViewFood);
            TextView textViewFoodTitle = convertView.findViewById(R.id.textViewFoodTitle);
            TextView textViewFoodPrice = convertView.findViewById(R.id.textViewFoodPrice);
            final TextView textViewQuantity = convertView.findViewById(R.id.textViewQuantity);
            Button buttonIncrease = convertView.findViewById(R.id.buttonIncrease);
            Button buttonDecrease = convertView.findViewById(R.id.buttonDecrease);
            ImageButton buttonRemove = convertView.findViewById(R.id.buttonRemove);

            final FoodDomain food = cartList.get(position);

            // Set thông tin món ăn
            textViewFoodTitle.setText(food.getTitle());
            textViewFoodPrice.setText(food.getFee() + " VND");
            textViewQuantity.setText(String.valueOf(food.getQuantity()));

            // Xử lý tăng số lượng
            buttonIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    food.setQuantity(food.getQuantity() + 1);
                    textViewQuantity.setText(String.valueOf(food.getQuantity()));
                    updateTotalPrice();
                }
            });

            // Xử lý giảm số lượng
            buttonDecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (food.getQuantity() > 1) {
                        food.setQuantity(food.getQuantity() - 1);
                        textViewQuantity.setText(String.valueOf(food.getQuantity()));
                        updateTotalPrice();
                    }
                }
            });

            // Xử lý xóa món ăn
            buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartList.remove(position);
                    notifyDataSetChanged();
                    updateTotalPrice();
                }
            });

            return convertView;
        }
    }
}
