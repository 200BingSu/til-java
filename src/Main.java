import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        String dataName = "data";
        String serviceKey = "XYD6S7KPAR854867";
        String pageNo = "1";
        String numOfRows = "10";

        StringBuilder urlBuilder = new StringBuilder("https://www.safetydata.go.kr/V2/api/DSSP-IF-10850");
        urlBuilder.append("?" + "serviceKey=" + serviceKey);
        urlBuilder.append("&" + "pageNo=" + pageNo);
        urlBuilder.append("&" + "numOfRows=" + numOfRows);
        System.out.println(urlBuilder.toString());

        URI uri = new URI(urlBuilder.toString());
        URL url = uri.toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader;
        connection.connect();

        if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            System.err.println("Connection failed.");
            return;
        }

        /* API 응답에서 데이터 추출 */
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        reader.close();
        connection.disconnect();

        System.out.println("응답 결과:");
        System.out.println(sb.toString());
    }

}
