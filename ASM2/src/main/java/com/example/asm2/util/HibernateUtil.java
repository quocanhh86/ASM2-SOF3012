package com.example.asm2.util;

import com.example.asm2.entity.LoaiSp;
import com.example.asm2.entity.SanPham;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory FACTORY;
    // dto : Data Tranfer Object: Chuyển đổi dữ liệu object
    //  entity (Object)
    // Chuyển đổi data: tạo các class có các dữ liệu phù hợp theo mong muốn
    // Dựa vào object trong entity
    // request & response
    // request: yêu cầu của người dùng
    // respone: phản hồi
    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2016Dialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=PTPM_FINALLY_SOF3012;encrypt=true;trustServerCertificate=true;");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456aA@$");
        properties.put(Environment.SHOW_SQL, "true"); // gen ra cau sql ma hibernate gen

        conf.setProperties(properties);
//      conf.addAnnotatedClass(SinhVien1.class); // dang ky hibernate nhan truy van
        Class<?>[] annotatedClasses = {
//                KhachHang.class,
//                NhanVien.class,
//                ChucVu.class,
//                ChiTietSP.class,
//                SanPham.class,
//                DongSP.class,
//                MauSac.class,
//                Nsx.class,
//                TaiKhoan.class,
                    SanPham.class,
                    LoaiSp.class,
        };

        // Thêm tất cả các lớp trong mảng vào Configuration
        for (Class<?> annotatedClass : annotatedClasses) {
            conf.addAnnotatedClass(annotatedClass);
        }
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        System.out.println(getFACTORY());
    }
}
