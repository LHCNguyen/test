package com.example.orderfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.Adaptor.CategoryAdaptor;
import com.example.orderfoodapp.Adaptor.PopularAdaptor;
import com.example.orderfoodapp.Database.CategoryDAO;
import com.example.orderfoodapp.Database.MonAnDAO;
import com.example.orderfoodapp.Domain.CategoryDomain;
import com.example.orderfoodapp.Domain.FoodDomain;
import com.example.orderfoodapp.Model.Category;
import com.example.orderfoodapp.Model.MonAn;

import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements PopularAdaptor.OnAddButtonClickListener {
    private RecyclerView.Adapter adapter, adapter2;
    private ArrayList<FoodDomain> cartList = new ArrayList<>();
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private TextView textViewTenKH;
    private CategoryDAO categoryDAO;
    private MonAnDAO monAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        categoryDAO = new CategoryDAO(this);
        monAnDAO = new MonAnDAO(this);

        Intent intent = getIntent();
        String tenDN = intent.getStringExtra("tendangnhap");

        textViewTenKH = findViewById(R.id.textViewTenKH);
        if (tenDN != null) {
            textViewTenKH.setText("Xin chào, " + tenDN + "!");
        }

        LinearLayout CartBTN = findViewById(R.id.cartBtn);
        CartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChuActivity.this, CartActivity.class);
                intent.putExtra("cartList", cartList); // Truyền giỏ hàng sang CartActivity
                startActivity(intent);
            }
        });

        recyclerViewCategoryList();
        recyclerViewPopularList(0); // Mặc định hiển thị tất cả món ăn
    }

    private void recyclerViewCategoryList() {
        categoryDAO.open();
        List<Category> categoryList = categoryDAO.getAllLoaiMonAn();
        categoryDAO.close();

        ArrayList<CategoryDomain> categoryDomainList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDomainList.add(new CategoryDomain(category.getTenLoai(), "hinh_" + category.getTenLoai()));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        adapter = new CategoryAdaptor(categoryDomainList, new CategoryAdaptor.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(int categoryId) {
                recyclerViewPopularList(categoryId);
            }
        });
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopularList(int maLoai) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        List<MonAn> monAnList;
        if (maLoai == 0) {
            monAnList = monAnDAO.getAllMonAn();
        } else {
            monAnList = monAnDAO.getMonAnByLoai(maLoai);
        }

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        for (MonAn monAn : monAnList) {
            foodList.add(new FoodDomain(monAn.getTenMonAn(), monAn.getHinhAnh(), String.valueOf(monAn.getMaMonAn()), Double.parseDouble(monAn.getGiaTien())));
        }

        adapter2 = new PopularAdaptor(foodList, this);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    @Override
    public void onAddButtonClick(int position) {
        FoodDomain selectedFood = ((PopularAdaptor) adapter2).popularFood.get(position);
        boolean isFoodExist = false;

        // Kiểm tra xem món ăn đã có trong giỏ hàng chưa
        for (FoodDomain food : cartList) {
            if (food.getId().equals(selectedFood.getId())) {
                // Nếu món ăn đã có, tăng số lượng
                food.setQuantity(food.getQuantity() + 1);
                isFoodExist = true;
                break;
            }
        }

        if (!isFoodExist) {
            // Nếu món ăn chưa có trong giỏ hàng, thêm món ăn vào giỏ hàng
            selectedFood.setQuantity(1); // Mặc định số lượng là 1 khi thêm món mới
            cartList.add(selectedFood);
        }

        Toast.makeText(this, selectedFood.getTitle() + " đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
    }
}
