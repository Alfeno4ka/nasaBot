import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.time.format.DateTimeParseException;

public class MyTelegramBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final String URL = "https://api.nasa.gov/planetary/apod?api_key=xnQphsLb9G7BYXSSjTDPvoSSApFqxdE3b2BTLONf";

    private final DateConvertor dateConvertor;

    public MyTelegramBot(String BOT_NAME, String BOT_TOKEN) throws TelegramApiException {
        this.BOT_NAME = BOT_NAME;
        this.BOT_TOKEN = BOT_TOKEN;
        dateConvertor = new DateConvertor();


        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // /help
        // /start
        // /image
        // /date
        // /earth
        if (update.hasMessage() && update.getMessage().hasText()) {
            String[] separatedAction = update.getMessage().getText().split(" ");
            String action = separatedAction[0];
            long chatId = update.getMessage().getChatId();

            switch (action) {
                case "/help":
                    sendMessage("Этот бот присылает картинку дня по запросу /image", chatId);

                    break;
                case "/start":
                    String image = Utils.getUrl(URL);
                    sendMessage(image, chatId);
                    break;
                case "/image":
                    image = Utils.getUrl(URL);
                    sendMessage(image, chatId);
                    break;
                case "/date":
                    String dateStr = separatedAction[1];
                    try {
                        dateStr = dateConvertor.convertDate(dateStr);
                    } catch (DateTimeParseException e) {
                        sendMessage("Не удалось определить дату", chatId);
                        break;
                    }
                    image = Utils.getUrl(URL + "&date=" + dateStr);
                    sendMessage(image, chatId);
                    break;
                case "/earth":
                    Utils.getEarths("xnQphsLb9G7BYXSSjTDPvoSSApFqxdE3b2BTLONf");

                default:
                    sendMessage("Неизвестная команда", chatId);


            }
        }
    }

    void sendMessage(String msg, long chatId) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        message.setText(msg);

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        // TODO
        return BOT_TOKEN;
    }
}
