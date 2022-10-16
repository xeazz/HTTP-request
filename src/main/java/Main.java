import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

public class Main {
    public static final String URL =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpUriRequest httpGet = new HttpGet(URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            List<Facts> facts = mapper.readValue(
                    response.getEntity().getContent().readAllBytes(),
                    new TypeReference<>() {
                    });
            facts.stream()
                    .filter(x -> x.getUpvotes() != 0 && x.getUpvotes() > 0)
                    .forEach(System.out::println);
        }
    }
}
