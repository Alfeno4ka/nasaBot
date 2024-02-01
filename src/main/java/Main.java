import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static final String FILE_PREFIX = "image";
    private static final String FILE_EXTENSION = ".png";

    public static void main(String[] args) throws IOException, TelegramApiException {
        MyTelegramBot bot1 = new MyTelegramBot("apod2_bot","6722775235:AAEApxjaf73dBKuhaw-641EySgnEz5f221E");
    }


}
