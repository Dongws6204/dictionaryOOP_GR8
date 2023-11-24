
package api;

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;

public class Translator {
    private static final String API_KEY = "AIzaSyCgWfL_ke9if8Cm7qaK-Ft_lXKAF-G5A_U";

    public static void main(String[] args) {
        String textToTranslate = "Hello"; // Từ cần dịch

        try {
            // Encode văn bản cần dịch
            String encodedText = URLEncoder.encode(textToTranslate, "UTF-8");

            // Tạo URL cho API request
            String urlStr = "https://translation.googleapis.com/language/translate/v2?key=" + API_KEY +
                    "&source=en&target=vi&q=" + encodedText;

            // Tạo kết nối HTTP
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Đọc phản hồi từ API
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // In kết quả JSON từ API
            System.out.println(response.toString());

            // Xử lý JSON để lấy kết quả dịch
            // Ví dụ: Lấy dịch tiếng Việt từ JSON
            // (Cần thư viện JSON parsing như org.json hoặc Gson)

            conn.disconnect(); // Đóng kết nối
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
