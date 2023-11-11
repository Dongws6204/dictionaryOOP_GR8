package JDBC;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertDataFromFile {

    public static void main(String[] args) {
        Connection connection = null;
        String jdbcURL = "jdbc:mysql://localhost:3306/dictionarydb";
        String username = "root";
        String password = "PHW#84#jeor";

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);

            // Đường dẫn đến tệp văn bản
            String filePath = "C:\\Users\\Admin\\Downloads\\dictionaryOOP_GR8-main\\dictionaryOOP_GR8-main\\Dictionary\\src\\main\\java\\txt\\anhviet109K.txt";

            // Đọc tệp văn bản
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;

                String wordTarget = "";
                StringBuilder wordExplain = new StringBuilder();

                PreparedStatement preparedStatement = null;
                String insertSQL = "INSERT INTO words (word_target, word_explain) VALUES (?, ?)";

                int batchSize = 1000; // Số lượng chèn một lần để tối ưu hiệu suất
                int currentBatchSize = 0;

                while ((line = br.readLine()) != null) {
                    if (line.startsWith("@")) {
                        // Lấy từ đầu đến ký tự cuối cùng trước dấu /
                        int indexOfSlash = line.indexOf('/');
                        if (indexOfSlash != -1) {
                            wordTarget = line.substring(1, indexOfSlash).trim();
                            wordExplain.append(line.substring(indexOfSlash).trim()).append("\n");
                        } else {
                            // Nếu không có dấu /, lấy toàn bộ phần từ sau @ làm word_target
                            wordTarget = line.substring(1).trim();
                        }
                    } else if (line.startsWith("*") || line.startsWith("-") || line.startsWith("=")) {
                        // Bắt đầu từ *
                        wordExplain.append(line.trim()).append("\n");
                    } else if (line.startsWith("/")) {
                        // Bắt đầu từ /
                        wordExplain.append(line.trim()).append("\n");
                        while ((line = br.readLine()) != null && !line.startsWith("/")) {
                            wordExplain.append(line.trim()).append("\n");
                        }
                        // Khi gặp dòng kết thúc /, không thêm dòng mới vào wordExplain
                    } else if (line.trim().isEmpty()) {
                        // Khi gặp dòng trắng, chèn từ vào cơ sở dữ liệu
                        if (!wordTarget.isEmpty() && wordExplain.length() > 0) {
                            if (currentBatchSize == 0) {
                                preparedStatement = connection.prepareStatement(insertSQL);
                            }

                            preparedStatement.setString(1, wordTarget);
                            preparedStatement.setString(2, wordExplain.toString());
                            preparedStatement.addBatch();
                            currentBatchSize++;

                            if (currentBatchSize >= batchSize) {
                                preparedStatement.executeBatch();
                                currentBatchSize = 0;
                            }
                        }
                        wordTarget = "";
                        wordExplain.setLength(0);
                    }
                }

                // Thực hiện chèn dữ liệu cuối cùng nếu còn
                if (currentBatchSize > 0) {
                    preparedStatement.executeBatch();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                System.out.println("Dữ liệu đã được thêm vào cơ sở dữ liệu.");
            } catch (Exception e) {
                System.err.println("Lỗi khi đọc tệp văn bản: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi kết nối cơ sở dữ liệu: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
    }
}