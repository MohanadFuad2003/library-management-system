package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // رابط الاتصال بقاعدة البيانات
    private static final String USERNAME = "DEEMA"; // اسم المستخدم (تحقق أن SYSTEM1 موجود فعلاً، عادةً يكون SYSTEM)
    private static final String PASSWORD = "1234";   // كلمة المرور الخاصة بالمستخدم

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // إنشاء الاتصال وإرجاعه
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
