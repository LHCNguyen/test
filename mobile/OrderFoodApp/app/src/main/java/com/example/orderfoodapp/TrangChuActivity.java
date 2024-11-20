package com.example.orderfoodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.Adaptor.CategoryAdaptor;
import com.example.orderfoodapp.Adaptor.PopularAdaptor;
import com.example.orderfoodapp.Domain.CategoryDomain;
import com.example.orderfoodapp.Domain.FoodDomain;

import java.util.ArrayList;

public class TrangChuActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        Intent intent = getIntent();
        String tendn = intent.getStringExtra("tendangnhap");

        recyclerViewCategoryList();
        recyclerViewPopularList();

    }

    private void recyclerViewCategoryList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);


        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Nước lẩu", "hinhnuoclau1"));
        category.add(new CategoryDomain("Các loại thịt","hinhthit"));
        category.add(new CategoryDomain("Hải sản","hinhhaisan"));
        category.add(new CategoryDomain("Rau củ","rau1"));
        category.add(new CategoryDomain("Nước","nuoc"));
        category.add(new CategoryDomain("Nước chấm","nuoccham2"));

        adapter=new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyclerViewPopularList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist=new ArrayList<>();
        foodlist.add(new FoodDomain("thit bò","thitbo","3",100000.0));
        foodlist.add(new FoodDomain("tôm","hinhtom","4",100000.0));
        foodlist.add(new FoodDomain("pepsi","pepsi","5",75000.0));
        foodlist.add(new FoodDomain("Rau xà lách","rauxalach","6",80000.0));

        adapter2=new PopularAdaptor(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}
