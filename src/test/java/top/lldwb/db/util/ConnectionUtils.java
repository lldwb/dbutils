package top.lldwb.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    static {
        /*
         * 加载驱动程序
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序失败...");
            e.printStackTrace();
        }
    }

    private static final String URL = "jdbc:mysql://mysql.lldwb.top:3306/sae?serverTimezone=Asia/Shanghai&useSSL=false";
    //账号
    private static final String USER_NAME = "sae";
    //密码
    private static final String PASSWORD = "PBERGLmSDfr2Mzxi";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
