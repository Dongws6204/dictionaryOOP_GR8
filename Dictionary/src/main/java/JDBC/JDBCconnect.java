package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCconnect {

        public static void main(String[] args) {
            Connection connection = null;
            String jdbcURL = "jdbc:mysql://localhost:3306/dictionary";
            String username = "root";
            String password = "1616lclc";
            try {
                // Tạo kết nối tới cơ sở dữ liệu
                connection = DriverManager.getConnection(jdbcURL, username, password);
                System.out.println("Kết nối thành công đến cơ sở dữ liệu MySQL.");
            } catch (SQLException e) {
                System.out.println("Lỗi kết nối đến cơ sở dữ liệu MySQL: " + e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
                }
            }
        }
}
