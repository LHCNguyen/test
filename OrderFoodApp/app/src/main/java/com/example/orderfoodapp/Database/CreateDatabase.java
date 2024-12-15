package com.example.orderfoodapp.Database;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.orderfoodapp.Utils.PasswordUtils;




public class CreateDatabase extends SQLiteOpenHelper {

    public static String TB_USER = "USER";
    public static String TB_MONAN = "MONAN";
    public static String TB_LOAIMONAN = "LOAIMONAN";
    public static String TB_BANAN = "BANAN";
    public static String TB_GOIMON = "GOIMON";
    public static String TB_CHITIETGOIMON = "CHITIETGOIMON";
    public static String TB_ROLE = "ROLE";
    public static String TB_NGUYENLIEU = "NGUYENLIEU";
    public static String TB_NGUYENLIEUMONAN = "NGUYENLIEUMONAN";
    public static String TB_PHUONGTHUCTHANHTOAN = "PHUONGTHUCTHANHTOAN";

    public static String TB_USER_MAUSER = "MAUSER";
    public static String TB_USER_TENDN = "TENDN";
    public static String TB_USER_MATKHAU = "MATKHAU";
    public static String TB_USER_GIOITINH = "GIOITINH";
    public static String TB_USER_NGAYSINH = "NGAYSINH";
    public static String TB_USER_EMAIL = "EMAIL";
    public static String TB_USER_MAROLE = "MAROLE";
    public static String TB_USER_SDT = "SDT";
    public static String TB_USER_FULLNAME = "FULLNAME";
    public static String TB_USER_NGAYTAO = "NGAYTAO";
    public static String TB_USER_DIEMTICHLUY = "DIEMTICHLUY";

    public static String TB_MONAN_MAMONAN = "MAMONAN";
    public static String TB_MONAN_TENMONAN = "TENMONAN";
    public static String TB_MONAN_GIATIEN = "GIATIEN";
    public static String TB_MONAN_MALOAI = "MALOAI";
    public static String TB_MONAN_HINHANH = "HINHANH";
    public static String TB_MONAN_MANGUYENLIEU = "MANGUYENLIEU";

    public static String TB_LOAIMONAN_MALOAI = "MALOAI";
    public static String TB_LOAIMONAN_TENLOAI = "TENLOAI";
    public static String TB_LOAIMONAN_MOTA = "MOTA";

    public static String TB_BANAN_MABAN = "MABAN";
    public static String TB_BANAN_TENBAN = "TENBAN";
    public static String TB_BANAN_TINHTRANG = "TINHTRANG";

    public static String TB_GOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_GOIMON_MAUSER = "MAUSER";
    public static String TB_GOIMON_NGAYGOI = "NGAYGOI";
    public static String TB_GOIMON_MABAN = "MABAN";
    public static String TB_GOIMON_NGAYTHANHTOAN = "NGAYTHANHTOAN";
    public static String TB_GOIMON_TONGSOMON = "TONGSOMON";
    public static String TB_GOIMON_TONGTIEN = "TONGTIEN";
    public static String TB_GOIMON_THUEVAT = "THUEVAT";
    public static String TB_GOIMON_MAPHUONGTHUCTHANHTOAN = "MAPHUONGTHUCTHANHTOAN";
    public static String TB_GOIMON_MAMONAN = "MAMONAN";

    public static String TB_CHITIETGOIMON_MACTGM = "MACTGM";
    public static String TB_CHITIETGOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_CHITIETGOIMON_MAMONAN = "MAMONAN";
    public static String TB_CHITIETGOIMON_SOLUONG = "SOLUONG";
    public static String TB_CHITIETGOIMON_TRANGTHAI = "TRANGTHAI";

    public static String TB_ROLE_MAROLE = "MAROLE";
    public static String TB_ROLE_CHUCVU = "CHUCVU";
    public static String TB_ROLE_MOTA = "MOTA";

    public static String TB_NGUYENLIEU_MANGUYENLIEU = "MANGUYENLIEU";
    public static String TB_NGUYENLIEU_TENNGUYENLIEU = "TENNGUYENLIEU";
    public static String TB_NGUYENLIEU_KHOILUONG = "KHOILUONG";
    public static String TB_NGUYENLIEU_MOTTA = "MOTA";

    public static String TB_NGUYENLIEUMONAN_MANLMA = "MANLMA";
    public static String TB_NGUYENLEUMONAN_MAMONAN = "MAMONAN";
    public static String TB_NGUYENLIEUMONAN_MANGUYENLIEU = "MANGUYENLIEU";

    public static String TB_PHUONGTHUCTHANHTOAN_MAPTTH = "MAPTTH";
    public static String TB_PHUONGTHUCTHANHTOAN_NAME = "NAME";

    private static final String DATABASE_NAME = "OrderFoodApp.db";

    public CreateDatabase(Context context) {

        super(context, DATABASE_NAME, null, 14);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbUSER = "CREATE TABLE " + TB_USER + " ( "
                + TB_USER_MAUSER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_USER_TENDN + " TEXT, "
                + TB_USER_MATKHAU + " TEXT, "
                + TB_USER_GIOITINH + " TEXT, "
                + TB_USER_NGAYSINH + " TEXT, "
                + TB_USER_EMAIL + " TEXT , "
                + TB_USER_DIEMTICHLUY + " TEXT , "
                + TB_USER_NGAYTAO + " TEXT, "
                + TB_USER_FULLNAME + " TEXT, "
                + TB_USER_SDT + " TEXT, "
                + TB_USER_MAROLE + " INTEGER )";

        String tbBANAN = "CREATE TABLE " + TB_BANAN + " ( "
                + TB_BANAN_MABAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BANAN_TENBAN + " TEXT, "
                + TB_BANAN_TINHTRANG + " TEXT )";

        String tbMONAN = "CREATE TABLE " + TB_MONAN + " ( "
                + TB_MONAN_MAMONAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_MONAN_TENMONAN + " TEXT NOT NULL, "
                + TB_MONAN_MALOAI + " INTEGER, "
                + TB_MONAN_GIATIEN + " TEXT NOT NULL,"
                + TB_MONAN_HINHANH + " TEXT, "
                + TB_MONAN_MANGUYENLIEU + " INTEGER, "
                + "FOREIGN KEY(" + TB_MONAN_MALOAI + ") REFERENCES " + TB_LOAIMONAN + "(" + TB_LOAIMONAN_MALOAI + "))";

        String tbLOAIMONAN = "CREATE TABLE " + TB_LOAIMONAN + " ( "
                + TB_LOAIMONAN_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_LOAIMONAN_TENLOAI + " TEXT ,"
                + TB_LOAIMONAN_MOTA + " TEXT )";

        String tbGOIMON = "CREATE TABLE " + TB_GOIMON + " ( "
                + TB_GOIMON_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_GOIMON_MABAN + " INTEGER, "
                + TB_GOIMON_MAUSER + " INTEGER, "
                + TB_GOIMON_NGAYGOI + " TEXT, "
                + TB_GOIMON_NGAYTHANHTOAN + " TEXT,"
                + TB_GOIMON_TONGSOMON + " TEXT ,"
                + TB_GOIMON_TONGTIEN + " TEXT, "
                + TB_GOIMON_THUEVAT + " TEXT, "
                + TB_GOIMON_MAPHUONGTHUCTHANHTOAN + " INTEGER, "
                + TB_GOIMON_MAMONAN + " INTEGER, "
                + "FOREIGN KEY(" + TB_GOIMON_MABAN + ") REFERENCES " + TB_BANAN + "(" + TB_BANAN_MABAN + "), "
                + "FOREIGN KEY(" + TB_GOIMON_MAPHUONGTHUCTHANHTOAN + ") REFERENCES " + TB_PHUONGTHUCTHANHTOAN + "(" + TB_PHUONGTHUCTHANHTOAN_MAPTTH + "), "
                + "FOREIGN KEY(" + TB_GOIMON_MAUSER + ") REFERENCES " + TB_USER + "(" + TB_USER_MAUSER + "), "
                + "FOREIGN KEY(" + TB_GOIMON_MAMONAN + ") REFERENCES " + TB_MONAN + "(" + TB_MONAN_MAMONAN + "))";

        String tbCHITETGOIMON = "CREATE TABLE " + TB_CHITIETGOIMON + " ( "
                + TB_CHITIETGOIMON_MACTGM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_CHITIETGOIMON_MAGOIMON + " INTEGER, "
                + TB_CHITIETGOIMON_MAMONAN + " INTEGER, "
                + TB_CHITIETGOIMON_SOLUONG + " INTEGER, "
                + TB_CHITIETGOIMON_TRANGTHAI + " TEXT, "
                + "FOREIGN KEY(" + TB_CHITIETGOIMON_MAGOIMON + ") REFERENCES " + TB_GOIMON + "(" + TB_GOIMON_MAGOIMON + "), "
                + "FOREIGN KEY(" + TB_CHITIETGOIMON_MAMONAN + ") REFERENCES " + TB_MONAN + "(" + TB_MONAN_MAMONAN + "))";

        String tbROLE = "CREATE TABLE " + TB_ROLE + " ( "
                + TB_ROLE_MAROLE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ROLE_CHUCVU + " TEXT, "
                + TB_ROLE_MOTA + " TEXT )";

        String tbNGUYENLIEU = "CREATE TABLE " + TB_NGUYENLIEU + " ( "
                + TB_NGUYENLIEU_MANGUYENLIEU + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_NGUYENLIEU_TENNGUYENLIEU + " TEXT ,"
                + TB_NGUYENLIEU_KHOILUONG + " TEXT, "
                + TB_NGUYENLIEU_MOTTA + " TEXT )";

        String tbNGUYENLIEUMONAN = "CREATE TABLE " + TB_NGUYENLIEUMONAN + " ( "
                + TB_NGUYENLIEUMONAN_MANLMA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_NGUYENLIEUMONAN_MANGUYENLIEU + " INTEGER, "
                + TB_NGUYENLEUMONAN_MAMONAN + " INTEGER, "
                + "FOREIGN KEY(" + TB_NGUYENLIEUMONAN_MANGUYENLIEU + ") REFERENCES " + TB_NGUYENLIEU + "(" + TB_NGUYENLIEU_MANGUYENLIEU + "), "
                + "FOREIGN KEY(" + TB_NGUYENLEUMONAN_MAMONAN + ") REFERENCES " + TB_MONAN + "(" + TB_MONAN_MAMONAN + "))";

        String tbPHUONGTHUCTHANHTOAN = "CREATE TABLE " + TB_PHUONGTHUCTHANHTOAN + " ( "
                + TB_PHUONGTHUCTHANHTOAN_MAPTTH + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_PHUONGTHUCTHANHTOAN_NAME + " TEXT )";

        db.execSQL(tbUSER);
        db.execSQL(tbROLE);
        db.execSQL(tbBANAN);
        db.execSQL(tbMONAN);
        db.execSQL(tbLOAIMONAN);
        db.execSQL(tbGOIMON);
        db.execSQL(tbCHITETGOIMON);
        db.execSQL(tbNGUYENLIEU);
        db.execSQL(tbNGUYENLIEUMONAN);
        db.execSQL(tbPHUONGTHUCTHANHTOAN);
        themTaiKhoanMacDinh(db);

        // Thêm dữ liệu mặc định cho bảng ROLE
        db.execSQL("INSERT INTO ROLE (CHUCVU, MOTA) VALUES ('Admin', 'Quản trị hệ thống')");
        db.execSQL("INSERT INTO ROLE (CHUCVU, MOTA) VALUES ('Nhân viên', 'Nhân viên phục vụ')");
        db.execSQL("INSERT INTO ROLE (CHUCVU, MOTA) VALUES ('Khách hàng', 'Khách hàng sử dụng dịch vụ')");

        // Thêm dữ liệu mặc định cho bảng PHUONGTHUCTHANHTOAN
        db.execSQL("INSERT INTO PHUONGTHUCTHANHTOAN (NAME) VALUES ('Tiền mặt')");
        db.execSQL("INSERT INTO PHUONGTHUCTHANHTOAN (NAME) VALUES ('Thẻ tín dụng')");
        db.execSQL("INSERT INTO PHUONGTHUCTHANHTOAN (NAME) VALUES ('Chuyển khoản ngân hàng')");
        db.execSQL("INSERT INTO PHUONGTHUCTHANHTOAN (NAME) VALUES ('Momo')");

        //Thêm dữ liệu vào bảng món ăn
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Gỏi cuốn', 1, '50000', ' @drawable/thitbo.png', 1)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Bún chay', 1, '40000', '@drawable/thitbo.png', 2)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Gà nướng', 2, '120000', '@drawable/thitbo.png', 3)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Sườn nướng', 2, '150000', '@drawable/thitbo.png', 4)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Cá hấp', 3, '130000', '@drawable/thitbo.png', 5)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Mực hấp', 3, '140000', '@drawable/thitbo.png', 6)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Bò xào', 4, '160000', '@drawable/thitbo.png', 7)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Gà xào', 4, '110000', '@drawable/thitbo.png', 8)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Lẩu thập cẩm', 5, '200000', '@drawable/thitbo.png', 9)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Lẩu gà', 5, '180000', '@drawable/thitbo.png', 10)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Cá nướng', 6, '150000', '@drawable/thitbo.png', 11)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Tôm nướng', 6, '160000', '@drawable/thitbo.png', 12)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Bò hầm', 7, '170000', '@drawable/thitbo.png', 13)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Heo hầm', 7, '160000', '@drawable/thitbo.png', 14)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Bánh xèo', 8, '60000', '@drawable/thitbo.png', 15)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Phở bò', 9, '75000', '@drawable/thitbo.png', 16)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Phở gà', 9, '70000', '@drawable/thitbo.png', 17)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Bánh cuốn', 8, '50000', '@drawable/thitbo.png', 18)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Mì xào hải sản', 4, '85000', '@drawable/thitbo.png', 19)");
        db.execSQL("INSERT INTO MONAN (TENMONAN, MALOAI, GIATIEN, HINHANH, MANGUYENLIEU) VALUES ('Cơm tấm', 2, '55000', '@drawable/thitbo.png', 20)");

        // Thêm dữ liệu mặc định cho bảng LOAIMONAN
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Món ăn chay', 'Các món ăn không chứa thịt hoặc sản phẩm từ động vật')");
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Món nướng', 'Các món ăn được chế biến bằng phương pháp nướng')");
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Món hấp', 'Các món ăn được chế biến bằng phương pháp hấp, giữ nguyên hương vị tươi ngon')");
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Món xào', 'Các món ăn chế biến bằng cách xào nhanh trên chảo')");
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Món lẩu', 'Các món ăn nấu trong nồi lẩu, thường được ăn kèm với các loại rau và gia vị')");
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Món hải sản', 'Các món ăn được chế biến từ hải sản như cá, tôm, mực, v.v.')");
        db.execSQL("INSERT INTO LOAIMONAN (TENLOAI, MOTA) VALUES ('Món thịt', 'Các món ăn được chế biến từ thịt động vật như bò, heo, gà, v.v.')");

        // Thêm dữ liệu mặc định cho bảng BANAN
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 1', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 2', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 3', 'Đang sử dụng')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 4', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 5', 'Đang sử dụng')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 6', 'Đang sử dụng')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 7', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 8', 'Đang sử dụng')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 9', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 10', 'Đang sử dụng')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 11', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 12', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 13', 'Đang sử dụng')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 14', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 15', 'Đang sử dụng')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 16', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 17', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 18', 'Đang sử dụng')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 19', 'Trống')");
        db.execSQL("INSERT INTO BANAN (TENBAN, TINHTRANG) VALUES ('Bàn 20', 'Đang sử dụng')");

        // Thêm dữ liệu mặc định cho bảng NGUYENLIEU
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Thịt bò', '100 kg', 'Thịt bò tươi')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Rau xà lách', '50 kg', 'Rau sạch')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Gạo', '200 kg', 'Gạo thơm')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Tôm tươi', '30 kg', 'Tôm sạch, tươi ngon')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Cà chua', '60 kg', 'Cà chua chín mọng')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Cà rốt', '40 kg', 'Cà rốt tươi, ngọt')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Hành tây', '30 kg', 'Hành tây tươi')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Ớt đỏ', '15 kg', 'Ớt tươi, cay nồng')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Khoai tây', '80 kg', 'Khoai tây ngon, chắc')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Bột mì', '100 kg', 'Bột mì chất lượng cao')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Trứng gà', '150 kg', 'Trứng gà tươi, sạch')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Sữa tươi', '200 lít', 'Sữa tươi nguyên chất')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Bơ', '50 kg', 'Bơ sữa ngon, mềm')");db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Tỏi', '20 kg', 'Tỏi tươi, thơm')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Nấm hương', '10 kg', 'Nấm hương tươi, thơm ngon')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Sả', '15 kg', 'Sả tươi, thơm')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Lá chanh', '5 kg', 'Lá chanh tươi, thơm')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Nước mắm', '100 lít', 'Nước mắm nguyên chất')");
        db.execSQL("INSERT INTO NGUYENLIEU (TENNGUYENLIEU, KHOILUONG, MOTA) VALUES ('Đường', '150 kg', 'Đường trắng, tinh khiết')");


        // Thêm dữ liệu vào bảng GOIMON
        db.execSQL("INSERT INTO GOIMON (MABAN, MAUSER, NGAYGOI, NGAYTHANHTOAN, TONGSOMON, TONGTIEN, THUEVAT, MAPHUONGTHUCTHANHTOAN, MAMONAN) VALUES (1, 1, '2024-12-01', '2024-12-01', '1', '500000', '10', 1, 1)");
        db.execSQL("INSERT INTO GOIMON (MABAN, MAUSER, NGAYGOI, NGAYTHANHTOAN, TONGSOMON, TONGTIEN, THUEVAT, MAPHUONGTHUCTHANHTOAN, MAMONAN) VALUES (2, 2, '2024-12-01', '2024-12-01', '1', '300000', '10', 2, 2)");

        // Thêm dữ liệu vào bảng CHITIETGOIMON
        db.execSQL("INSERT INTO CHITIETGOIMON (MAGOIMON, MAMONAN, SOLUONG, TRANGTHAI) VALUES (1, 1, 2, 'Đã chuẩn bị')");
        db.execSQL("INSERT INTO CHITIETGOIMON (MAGOIMON, MAMONAN, SOLUONG, TRANGTHAI) VALUES (1, 2, 1, 'Đang chuẩn bị')");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TB_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TB_ROLE);
            db.execSQL("DROP TABLE IF EXISTS " + TB_BANAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_MONAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_LOAIMONAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_GOIMON);
            db.execSQL("DROP TABLE IF EXISTS " + TB_CHITIETGOIMON);
            db.execSQL("DROP TABLE IF EXISTS " + TB_NGUYENLIEU);
            db.execSQL("DROP TABLE IF EXISTS " + TB_NGUYENLIEUMONAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_PHUONGTHUCTHANHTOAN);
            onCreate(db);
        }

    }

    private void themTaiKhoanMacDinh(SQLiteDatabase db) {
        // Mã hóa mật khẩu
        String matKhauAdmin = PasswordUtils.bamMatKhau("admin123456789");
        String matKhauStaff = PasswordUtils.bamMatKhau("staff123456789");

        ContentValues adminValues = new ContentValues();
        adminValues.put(TB_USER_TENDN, "admin");
        adminValues.put(TB_USER_MATKHAU, matKhauAdmin);
        adminValues.put(TB_USER_GIOITINH, "Nam");
        adminValues.put(TB_USER_NGAYSINH, "01/01/1990");
        adminValues.put(TB_USER_EMAIL, "admin@example.com");
        adminValues.put(TB_USER_MAROLE, 1); // Admin
        adminValues.put(TB_USER_SDT, "0123456789");
        adminValues.put(TB_USER_FULLNAME, "Admin User");

        ContentValues staffValues = new ContentValues();
        staffValues.put(TB_USER_TENDN, "staff");
        staffValues.put(TB_USER_MATKHAU, matKhauStaff);
        staffValues.put(TB_USER_GIOITINH, "Nữ");
        staffValues.put(TB_USER_NGAYSINH, "01/01/1995");
        staffValues.put(TB_USER_EMAIL, "staff@example.com");
        staffValues.put(TB_USER_MAROLE, 2); // Nhân viên
        staffValues.put(TB_USER_SDT, "0987654321");
        staffValues.put(TB_USER_FULLNAME, "Staff User");

        // Thêm admin và staff vào bảng USER
        db.insertWithOnConflict(TB_USER, null, adminValues, SQLiteDatabase.CONFLICT_REPLACE);
        db.insertWithOnConflict(TB_USER, null, staffValues, SQLiteDatabase.CONFLICT_REPLACE);

    }

    public  SQLiteDatabase open() {
        return this.getWritableDatabase();
    }
}