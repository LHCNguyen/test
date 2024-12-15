package com.example.orderfoodapp.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.Adaptor.EditMonAnAdapter;
import com.example.orderfoodapp.Database.MonAnDAO;
import com.example.orderfoodapp.Model.MonAn;
import com.example.orderfoodapp.R;

import java.util.List;

public class RepairFoodFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditMonAnAdapter adapter;
    private List<MonAn> monAnList;
    private MonAnDAO monAnDAO;

    public RepairFoodFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repairfood, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        monAnDAO = new MonAnDAO(getContext());
        monAnList = monAnDAO.getAllMonAn();

        adapter = new EditMonAnAdapter(getContext(), monAnList,this);  // Chỉnh sửa adapter nếu cần
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        Button btnAddMonAn = view.findViewById(R.id.btnAddMonAn); // Tương tự như trên, sử dụng view.findViewById
        btnAddMonAn.setOnClickListener(v -> {
            // Ví dụ thêm món ăn
            monAnDAO.insertMonAn("Món ăn mới", "50000", 1, "image.jpg");
            monAnList = monAnDAO.getAllMonAn(); // Lấy lại danh sách sau khi thêm
            adapter.notifyDataSetChanged();
        });

        return view;  // Trả về view của fragment
    }

    // Mở hộp thoại sửa món ăn
    public void openEditDialog(MonAn monAn) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_edit_mon_an);

        EditText edtTenMonAn = dialog.findViewById(R.id.edtTenMonAn);
        EditText edtGiaTien = dialog.findViewById(R.id.edtGiaTien);
        Spinner spnLoai = dialog.findViewById(R.id.spnLoai);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        // Gán dữ liệu hiện tại vào các trường
        edtTenMonAn.setText(monAn.getTenMonAn());
        edtGiaTien.setText(monAn.getGiaTien());

        // Cập nhật loại món ăn (Spinner có thể được setup trước đó, ví dụ như ArrayAdapter cho spnLoai)

        // Lưu thay đổi khi nhấn nút Lưu
        btnSave.setOnClickListener(v -> {
            String tenMonAn = edtTenMonAn.getText().toString();
            String giaTien = edtGiaTien.getText().toString();
            int maLoai = spnLoai.getSelectedItemPosition();  // Cập nhật giá trị Mã Loại từ Spinner

            // Cập nhật món ăn
            boolean isUpdated = monAnDAO.updateMonAn(monAn.getMaMonAn(), tenMonAn, giaTien, maLoai, monAn.getHinhAnh());

            if (isUpdated) {
                Toast.makeText(getContext(), "Cập nhật món ăn thành công", Toast.LENGTH_SHORT).show();
                // Cập nhật lại danh sách món ăn và adapter
                monAnList = monAnDAO.getAllMonAn();
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Cập nhật món ăn thất bại", Toast.LENGTH_SHORT).show();
            }

            dialog.dismiss();
        });

        dialog.show();
    }

    // Xác nhận và xóa món ăn
    public void showDeleteConfirmation(int maMonAn) {
        new AlertDialog.Builder(getContext())
                .setMessage("Bạn có chắc chắn muốn xóa món ăn này?")
                .setCancelable(false)
                .setPositiveButton("Có", (dialog, which) -> {
                    // Xóa món ăn
                    boolean isDeleted = monAnDAO.deleteMonAn(maMonAn);
                    if (isDeleted) {
                        Toast.makeText(getContext(), "Xóa món ăn thành công", Toast.LENGTH_SHORT).show();
                        // Cập nhật lại danh sách món ăn và adapter
                        monAnList = monAnDAO.getAllMonAn();
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Xóa món ăn thất bại", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
