package com.example.orderfoodapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.Database.MonAnDAO;

public class AddFoodFragment extends Fragment {

    private EditText foodNameEditText;
    private EditText foodPriceEditText;
    private EditText foodCategoryEditText; // Mã loại món ăn (Ví dụ: 1 cho món chính, 2 cho món phụ)
    private EditText foodImagePathEditText; // Đường dẫn hình ảnh món ăn
    private Button addFoodButton;

    private MonAnDAO monAnDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addfood, container, false);

        // Kết nối các View
        foodNameEditText = view.findViewById(R.id.foodNameEditText);
        foodPriceEditText = view.findViewById(R.id.foodPriceEditText);
        foodCategoryEditText = view.findViewById(R.id.foodCategoryEditText);
        foodImagePathEditText = view.findViewById(R.id.foodImagePathEditText);
        addFoodButton = view.findViewById(R.id.addFoodButton);

        // Khởi tạo MonAnDAO
        monAnDAO = new MonAnDAO(getActivity());

        // Xử lý sự kiện khi nhấn nút Add Food
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = foodNameEditText.getText().toString();
                String foodPrice = foodPriceEditText.getText().toString();
                String foodCategory = foodCategoryEditText.getText().toString();
                String foodImagePath = foodImagePathEditText.getText().toString();

                // Kiểm tra xem người dùng đã nhập tên, giá món ăn và thông tin khác chưa
                if (foodName.isEmpty() || foodPrice.isEmpty() || foodCategory.isEmpty() || foodImagePath.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Chuyển mã loại món ăn từ String thành Integer
                    int category = Integer.parseInt(foodCategory);

                    // Thêm món ăn vào cơ sở dữ liệu
                    boolean result = monAnDAO.insertMonAn(foodName, foodPrice, category, foodImagePath);

                    // Kiểm tra kết quả và hiển thị thông báo
                    if (result) {
                        Toast.makeText(getActivity(), "Food added successfully: " + foodName, Toast.LENGTH_SHORT).show();

                        // Reset các trường nhập sau khi thêm món ăn
                        foodNameEditText.setText("");
                        foodPriceEditText.setText("");
                        foodCategoryEditText.setText("");
                        foodImagePathEditText.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Failed to add food", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}
