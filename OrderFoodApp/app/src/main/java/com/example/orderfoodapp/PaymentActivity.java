package com.example.orderfoodapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoodapp.Domain.FoodOrderItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {
    private TextView displayTotalPrice, displayVAT, displayTotalWithVAT;
    private Spinner selectPaymentMethods;
    private Button btnConfirmPayment;
    private ArrayList<String> paymentMethodsList;
    private double vatRate = 0.1; // Thuế VAT 10%

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment);

        paymentMethodsList = new ArrayList<>();
        Intent intent = getIntent();
        double totalPrice = intent.getDoubleExtra("totalPrice", 0.0);  // Nhận tổng giá
        ArrayList<FoodOrderItem> cartList = (ArrayList<FoodOrderItem>) intent.getSerializableExtra("cartList");

        // Ánh xạ các View
        displayTotalPrice = findViewById(R.id.displayTotalPrice);
        displayVAT = findViewById(R.id.displayVAT);
        displayTotalWithVAT = findViewById(R.id.displayTotalWithVAT);
        selectPaymentMethods = findViewById(R.id.selectPaymentMethods);
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);

        // Tính toán và hiển thị giá tiền
        double vatAmount = totalPrice * vatRate;
        double totalWithVAT = totalPrice + vatAmount;

        displayTotalPrice.setText("Tổng tiền: " + totalPrice + " VND");
        displayVAT.setText("Thuế VAT (10%): " + vatAmount + " VND");
        displayTotalWithVAT.setText("Tổng cộng: " + totalWithVAT + " VND");

        // Gọi hàm tải phương thức thanh toán lên giao diện
        loadPaymentMethodsFromDatabase();

        // Xử lý khi nhấn nút Thanh Toán
        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog(cartList, totalPrice, vatAmount, totalWithVAT);
            }
        });
    }

    private void loadPaymentMethodsFromDatabase(){
        SQLiteDatabase database = openOrCreateDatabase("OrderFoodApp.db", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT NAME FROM PHUONGTHUCTHANHTOAN", null);

        if (cursor.moveToFirst()) {
            do {
                String methodName = cursor.getString(0);
                paymentMethodsList.add(methodName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        // Gắn dữ liệu đã lấy từ database và Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethodsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectPaymentMethods.setAdapter(adapter);
    }

    private void showConfirmationDialog(ArrayList<FoodOrderItem> cartList, double totalPrice, double vatAmount, double totalWithVAT) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận thanh toán");
        builder.setMessage("Bạn có muốn thanh toán không?");

        builder.setPositiveButton("Có", (dialog, which) -> {
            // Thanh toán thành công
            Toast.makeText(PaymentActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
            // Lưu thông tin thanh toán vào cơ sở dữ liệu
            saveOrderToDatabase(cartList, totalPrice, vatAmount, totalWithVAT);
        });

        builder.setNegativeButton("Không", (dialog, which) -> {
            // Hủy thanh toán
            Toast.makeText(PaymentActivity.this, "Bạn chưa thanh toán.", Toast.LENGTH_SHORT).show();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveOrderToDatabase(ArrayList<FoodOrderItem> cartList, double totalPrice, double vatAmount, double totalWithVAT) {
        SQLiteDatabase database = openOrCreateDatabase("OrderFoodApp.db", MODE_PRIVATE, null);

        int tableId = 3;  // Ví dụ: Mã bàn
        int userId = 3;   // Ví dụ: Mã người dùng

        // Thêm dữ liệu vào bảng GOIMON
        String insertOrderQuery = "INSERT INTO GOIMON (MAUSER, NGAYGOI, MABAN, NGAYTHANHTOAN, TONGSOMON, TONGTIEN, THUEVAT, MAPHUONGTHUCTHANHTOAN) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement orderStatement = database.compileStatement(insertOrderQuery);

        orderStatement.bindLong(1, userId);  // MAUSER
        orderStatement.bindString(2, getCurrentDate());  // NGAYGOI
        orderStatement.bindLong(3, tableId);  // MABAN
        orderStatement.bindString(4, getCurrentDate());  // NGAYTHANHTOAN
        orderStatement.bindLong(5, cartList.size());  // TONGSOMON
        orderStatement.bindDouble(6, totalPrice);  // TONGTIEN
        orderStatement.bindDouble(7, vatAmount);  // THUEVAT
        orderStatement.bindLong(8, selectPaymentMethods.getSelectedItemPosition() + 1);  // MAPHUONGTHUCTHANHTOAN

        // Lưu dữ liệu vào bảng GOIMON và lấy MAGOIMON vừa tạo
        long insertedOrderId = orderStatement.executeInsert();

        // Lưu dữ liệu vào bảng CHITIETGOIMON
        for (FoodOrderItem food : cartList) {
            String insertDetailQuery = "INSERT INTO CHITIETGOIMON (MAGOIMON, MAMONAN, SOLUONG, TRANGTHAI) VALUES (?, ?, ?, ?)";
            SQLiteStatement detailStatement = database.compileStatement(insertDetailQuery);

            detailStatement.bindLong(1, insertedOrderId);  // MAGOIMON
            detailStatement.bindLong(2, 0);  // MAMONAN (Nếu không có ID món ăn, bạn có thể bỏ qua hoặc thêm mã món ăn thực tế)
            detailStatement.bindLong(3, food.getQuantity());  // SOLUONG (Lấy từ FoodOrderItem)
            detailStatement.bindString(4, "Chưa chế biến");  // TRANGTHAI (Mặc định là "Chưa chế biến")

            detailStatement.executeInsert();
        }

        database.close();
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }
}
