package com.example.orderfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class TrangChuActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
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
        if(tenDN != null){
            textViewTenKH.setText("Xin chào, " +tenDN + "!" );
        }

        // Thiết lập danh sách category và popular list
        recyclerViewCategoryList();
        recyclerViewPopularList(0);  // Mặc định hiển thị tất cả món ăn khi mới vào trang
    }

    private void recyclerViewCategoryList(){
        categoryDAO.open();
        List<Category> categoryList = categoryDAO.getAllLoaiMonAn();
        categoryDAO.close();

        // Chuyển các category từ Category thành CategoryDomain
        ArrayList<CategoryDomain> categoryDomainList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDomainList.add(new CategoryDomain(category.getTenLoai(), "hinh_" + category.getTenLoai()));
        }

        // Thiết lập RecyclerView cho category
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        // Khởi tạo adapter với listener để xử lý khi người dùng chọn category
        adapter = new CategoryAdaptor(categoryDomainList, new CategoryAdaptor.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(int categoryId) {
                // Cập nhật danh sách món ăn dựa trên categoryId
                recyclerViewPopularList(categoryId);
            }
        });
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopularList(int maLoai){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        // Lấy danh sách món ăn từ database theo mã loại
        List<MonAn> monAnList;
        if (maLoai == 0) {
            // Nếu mã loại là 0 thì lấy tất cả món ăn
            monAnList = monAnDAO.getAllMonAn();
        } else {
            // Lấy món ăn theo loại cụ thể
            monAnList = monAnDAO.getMonAnByLoai(maLoai);
        }

        // Chuyển đổi danh sách MonAn thành danh sách FoodDomain cho adapter
        ArrayList<FoodDomain> foodList = new ArrayList<>();
        for (MonAn monAn : monAnList) {
            foodList.add(new FoodDomain(monAn.getTenMonAn(), monAn.getHinhAnh(), String.valueOf(monAn.getMaMonAn()), Double.parseDouble(monAn.getGiaTien())));
        }

        // Thiết lập adapter cho danh sách món ăn
        adapter2 = new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}
