package my.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.json.MailRequest;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static final String REMOTE_SERVICE_URL =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static final ObjectMapper mapper = new ObjectMapper();  //Для преобразования
    // полученного json в обьект java

    public static void main(String[] args) throws IOException {

        var requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(30000)
                .setRedirectsEnabled(false)
                .build();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My Test Service")
                .setDefaultRequestConfig(requestConfig)
                .build();

        // ***** Выполнили техническую часть (настроили клиент)

        HttpGet request = new HttpGet(REMOTE_SERVICE_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);

        //  Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
        List<MailRequest> mailRequests = mapper.readValue(response.getEntity().getContent(),
                new TypeReference<>() {});
        mailRequests.stream()
                .filter(mailRequest -> mailRequest.getUpvotes() != null && mailRequest.getUpvotes() > 0)
                .forEach(System.out::println);
    }
}