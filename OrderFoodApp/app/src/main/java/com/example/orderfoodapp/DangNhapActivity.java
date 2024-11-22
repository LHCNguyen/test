package com.example.orderfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoodapp.Database.RoleDAO;
import com.example.orderfoodapp.Database.UserDAO;
import com.example.orderfoodapp.Model.Role;
import com.example.orderfoodapp.Model.User;

public class DangNhapActivity extends AppCompatActivity {

    private Button btnDongYDN, btnDangKyDN;
    private EditText edtTaiKhoan, editMatKhauDN;
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        // Khởi tạo UserDAO
        userDAO = new UserDAO(this);
        roleDAO = new RoleDAO(this);

        // Liên kết các view
        btnDongYDN = findViewById(R.id.btnDongYDN);
        btnDangKyDN = findViewById(R.id.btnDangKyDN);
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        editMatKhauDN = findViewById(R.id.edtMatKhauDN);

        btnDangKyDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });

        // Sự kiện khi người dùng nhấn nút Đăng nhập
        btnDongYDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDangNhap = edtTaiKhoan.getText().toString().trim();
                String matKhau = editMatKhauDN.getText().toString().trim();

                // Kiểm tra tên đăng nhập và mật khẩu có trống không
                if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
                    Toast.makeText(DangNhapActivity.this, "Vui lòng nhập tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = userDAO.kiemTraDangNhap(tenDangNhap, matKhau);
                if (user != null) {
                    // Đăng nhập thành công, lấy thông tin vai trò
                    int maRole = user.getMaRole();
                    Role role = roleDAO.layRoleTheoMa(maRole);

                    if (role != null) {
                        String chucVu = role.getChucVu();
                        Toast.makeText(DangNhapActivity.this, "Chào mừng " + chucVu + "!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                        intent.putExtra("tendangnhap", tenDangNhap);

                        // Chuyển hướng dựa trên vai trò người dùng
                        switch (maRole) {
                            case 1:
                                // Vai trò Admin
                                startActivity(new Intent(DangNhapActivity.this, AdminActivity.class));
                                break;
                            case 2:
                                // Vai trò Nhân viên

                                break;
                            case 3:
                                // Vai trò Khách hàng
                                startActivity(intent);
                                break;
                            default:
                                // Vai trò không xác định
                                Toast.makeText(DangNhapActivity.this, "Vai trò người dùng không hợp lệ", Toast.LENGTH_SHORT).show();
                                break;
                        }

                        // Kết thúc màn hình đăng nhập
                        finish();
                    } else {
                        Toast.makeText(DangNhapActivity.this, "Không tìm thấy thông tin vai trò", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(DangNhapActivity.this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
