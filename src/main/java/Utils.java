import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Utils {
    private static final String EARTH_URL = "https://api.nasa.gov/EPIC/api/natural/images?";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static String getUrl(String nasaUrl) {
        CloseableHttpClient client = HttpClients.createDefault();

        try {
            CloseableHttpResponse response = client.execute(new HttpGet(nasaUrl));

            NasaAnswer answer = MAPPER.readValue(response.getEntity().getContent(), NasaAnswer.class);

            return answer.url;
        } catch(IOException e){
            System.out.println("Была ошибка с ботом");
        }
        return "";
    }

    public static List<EarthsAnswer> getEarths (String apiKey) {
        StringBuilder earthUrlBuilder = new StringBuilder();
        earthUrlBuilder.append(EARTH_URL);
        earthUrlBuilder.append("api_key=");
        earthUrlBuilder.append(apiKey);
        CloseableHttpClient client = HttpClients.createDefault();

        try {
            CloseableHttpResponse response = client.execute(new HttpGet(earthUrlBuilder.toString()));
            //List<EarthsAnswer> earthsAnswers = MAPPER.readValue(response.getEntity().getContent(), List.class);
            CollectionType javaType = MAPPER.getTypeFactory().constructCollectionType(List.class, EarthsAnswer.class);
            List<EarthsAnswer> earthsAnswer = MAPPER.readValue(response.getEntity().getContent(), javaType);

            return earthsAnswer;
        } catch(IOException e){
            System.out.println("Была ошибка с ботом");
        }
        return Collections.emptyList();

    }
}
