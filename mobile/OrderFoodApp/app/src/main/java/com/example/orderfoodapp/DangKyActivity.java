package com.example.orderfoodapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoodapp.Database.UserDAO;
import com.example.orderfoodapp.Model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DangKyActivity extends AppCompatActivity {

    private EditText edtTenDN, edtMatKhau, edtNgaySinh, edtEmail, edtXacNhanMatKhau;
    private Button btnDongY, btnThoat;
    private RadioButton radGioiTinhNam, radGioiTinhNu;
    private RadioGroup radGroupGioiTinh;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        // Khởi tạo UserDAO
        userDAO = new UserDAO(this);

        // Liên kết các view với layout
        edtTenDN = findViewById(R.id.edtTenDN);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtXacNhanMatKhau = findViewById(R.id.edtXacNhanMatKhau);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtEmail = findViewById(R.id.edtEmail);
        radGroupGioiTinh = findViewById(R.id.radGroupGioiTinh);
        radGioiTinhNam = findViewById(R.id.radGioiTinhNam);
        radGioiTinhNu = findViewById(R.id.radGioiTinhNu);
        btnDongY = findViewById(R.id.btnDongY);
        btnThoat = findViewById(R.id.btnThoat);

        // Thêm kí tự "/" vào ngày sinh để giúp người dùng không phải nhập dấu "/"
        edtNgaySinh.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "ddmmyyyy";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d]", "");
                    StringBuilder formatted = new StringBuilder();
                    if (clean.length() > 0) {
                        formatted.append(clean.substring(0, Math.min(2, clean.length()))); // ngày
                    }
                    if (clean.length() >= 3) {
                        formatted.append("/").append(clean.substring(2, Math.min(4, clean.length()))); // tháng
                    }
                    if (clean.length() >= 5) {
                        formatted.append("/").append(clean.substring(4, Math.min(clean.length(), 8))); // năm
                    }

                    current = formatted.toString();
                    edtNgaySinh.setText(current);
                    edtNgaySinh.setSelection(current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Xử lý sự kiện khi người dùng nhấn nút đồng ý đăng ký
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDN = edtTenDN.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String xacNhanMatKhau = edtXacNhanMatKhau.getText().toString();
                String ngaySinh = edtNgaySinh.getText().toString();
                String email = edtEmail.getText().toString().trim();
                String gioiTinh = radGioiTinhNam.isChecked() ? "Nam" : "Nữ";
                int maRole = 3;

                // Kiểm tra các điều kiện hợp lệ
                if (tenDN.isEmpty() || matKhau.isEmpty() || ngaySinh.isEmpty() || email.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                // Kiểm tra tên đăng nhập hợp lệ
                else if (tenDN.length() < 6 || tenDN.length() > 20) {
                    Toast.makeText(DangKyActivity.this, "Tên đăng nhập phải từ 6 đến 20 ký tự", Toast.LENGTH_SHORT).show();
                }
                else if (tenDN.contains(" ")) {
                    Toast.makeText(DangKyActivity.this, "Tên đăng nhập không được có khoảng cách", Toast.LENGTH_SHORT).show();
                }
                // Kiểm tra mật khẩu hợp lệ
                else if (matKhau.length() < 8) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu phải có ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();
                } else if (matKhau.contains(" ")) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không được có khoảng cách", Toast.LENGTH_SHORT).show();
                }
                // Kiểm tra mật khẩu xác nhận
                else if (!matKhau.equals(xacNhanMatKhau)) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
                else if (xacNhanMatKhau.contains(" ")) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu xác nhận không được có khoảng cách", Toast.LENGTH_SHORT).show();
                }
                // Kiểm tra giới tính
                else if (radGroupGioiTinh.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
                }
                // Kiểm tra email hợp lệ
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(DangKyActivity.this, "EMAIL không đúng định dạng", Toast.LENGTH_SHORT).show();
                }
                // Kiểm tra định dạng ngày sinh
                else if (!isValidDate(ngaySinh)) {
                    Toast.makeText(DangKyActivity.this, "Ngày sinh không đúng định dạng (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
                }
                // Nếu tất cả hợp lệ, tiến hành đăng ký
                else {
                    // Tạo đối tượng User
                    User user = new User(tenDN, matKhau, gioiTinh, ngaySinh, email, maRole);

                    if (userDAO.kiemTraTenDangNhap(tenDN)) {
                        Toast.makeText(DangKyActivity.this, "Tên đăng nhập đã tồn tại, vui lòng chọn tên khác", Toast.LENGTH_SHORT).show();
                    } else {
                        // Thêm người dùng vào cơ sở dữ liệu
                        long result = userDAO.insertUSER(user);
                        if (result != -1) {
                            Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DangKyActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        // Xử lý sự kiện khi người dùng nhấn nút thoát
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Kiểm tra định dạng ngày tháng
    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Không cho phép các ngày không hợp lệ
        try {
            Date parsedDate = sdf.parse(date);
            return parsedDate != null;
        } catch (ParseException e) {
            return false;
        }
    }
}
