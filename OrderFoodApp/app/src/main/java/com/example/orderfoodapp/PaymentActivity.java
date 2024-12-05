package com.example.orderfoodapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    private TextView displayTotalPrice, displayVAT, displayTotalWithVAT;
    private Spinner selectPaymentMethods;
    private Button btnConfirmPayment;
    private ArrayList<String> paymentMethodsList;
    private double totalPrice = 100000; // Giá tiền từ giỏ hàng (thay đổi giá trị này)
    private double vatRate = 0.1; // Thuế VAT 10%

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment);

        paymentMethodsList = new ArrayList<>();

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

        //GỌi hàm tải phương thức thanh toán lên giao diện
        loadPaymentMethodsFromDatabase();

        // Xử lý khi nhấn nút Thanh Toán
        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void loadPaymentMethodsFromDatabase(){
        SQLiteDatabase database = openOrCreateDatabase("OrderFoodApp.db", MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("SELECT NAME FROM PHUONGTHUCTHANHTOAN",null);

        if(cursor.moveToFirst()){
            do {
                String methodName = cursor.getString(0);
                paymentMethodsList.add(methodName);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        //Gắn dữ liệu đã lấy từ database và Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethodsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectPaymentMethods.setAdapter(adapter);

    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận thanh toán");
        builder.setMessage("Bạn có muốn thanh toán không?");

        builder.setPositiveButton("Có", (dialog, which) -> {
            // Thanh toán thành công
            Toast.makeText(PaymentActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Không", (dialog, which) -> {
            // Hủy thanh toán
            Toast.makeText(PaymentActivity.this, "Bạn chưa thanh toán.", Toast.LENGTH_SHORT).show();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
