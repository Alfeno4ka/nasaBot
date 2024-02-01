package com.alfeno4ka.nasabot.util;

import com.alfeno4ka.nasabot.model.EarthAnswer;
import com.alfeno4ka.nasabot.model.NasaAnswer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class IntegrationUtils {
    private static final String EARTH_URL = "https://api.nasa.gov/EPIC/api/natural/images?";
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String getUrl(String nasaUrl) {
        CloseableHttpClient client = HttpClients.createDefault();

        try {
            CloseableHttpResponse response = client.execute(new HttpGet(nasaUrl));

            NasaAnswer answer = MAPPER.readValue(response.getEntity().getContent(), NasaAnswer.class);

            return answer.getUrl();
        } catch (IOException e) {
            System.out.println("Была ошибка с ботом");
        }
        return "";
    }

    public static EarthAnswer getLatestEarthAnswer(String apiKey) {
        StringBuilder earthUrlBuilder = new StringBuilder();
        earthUrlBuilder.append(EARTH_URL);
        earthUrlBuilder.append("api_key=");
        earthUrlBuilder.append(apiKey);

        CloseableHttpClient client = HttpClients.createDefault();

        try {
            CloseableHttpResponse response = client.execute(new HttpGet(earthUrlBuilder.toString()));
            CollectionType javaType = MAPPER.getTypeFactory().constructCollectionType(List.class, EarthAnswer.class);
            List<EarthAnswer> earthsAnswer = MAPPER.readValue(response.getEntity().getContent(), javaType);

            return earthsAnswer.stream()
                    .filter(earthAnswer -> earthAnswer.getDate().equals(
                            earthsAnswer.stream()
                                    .map(EarthAnswer::getDate)
                                    .max(Date::compareTo).get()
                    )).findFirst().orElse(null);
        } catch (IOException e) {
            System.out.println("Была ошибка с ботом");
        }
        return null;
    }
}
