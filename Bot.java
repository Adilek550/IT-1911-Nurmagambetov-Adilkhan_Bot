import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();//initialise our API
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();//create the object TelgramApi
        try {//We are need to registrate our bot
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiException e){//In the case if my bot have exclusion  will return this exclusion
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){// Is it method, which bot answer will be to us
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);//Opportunity of markup
        sendMessage.setChatId(message.getChatId().toString());//We need to create a id, for understand to whom to answer
        sendMessage.setReplyToMessageId(message.getMessageId());//To which specific message should my bot give the answer
        sendMessage.setText(text);//Text, which I'm send this method
        LogRecord update;
            try {
                setButton(sendMessage);
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }//Sending messages

    }

@Override
    public void onUpdateReceived(Update update) {//Method for receiving messages,receive updates through Long pool. Is it pending request queue
        Type model = new Type();
        Message message = update.getMessage();//Object  of class Message include here text, taking from our update or message
            if (message != null && message.hasText()) {//if our message not null and has the text
                switch (message.getText()) {
                    case "/help":
                        sendMsg(message, "How I can help you?/ Как я могу Вам помочь? " + "\n"+

                                "Write to me: @Adilkhann/ Напишите мне: @Adilkhann");
                        break;
                    case "/settings":
                        sendMsg(message, "What we will need to setting?/ Что мы будем настраивать?");
                        break;
                    case "/start":
                        sendMsg(message, " Hello, friend, please, write the city, whose weather you want to see./ Приветсвую, друг, пожалуйста введи город, погоду которого хочешь узнать.");
                     break;
                        default:
                        try {
                            sendMsg(message, Weather.getWeather(message.getText(), model));//Отпраляем сообщение которое отправил юзер и переотправляем туда результат обработки, передавая туда текст сообщения и нашу модель
                        } catch (IOException e) {
                            sendMsg(message, "City is not found...");
                        }

                }
            }

        }


    public void setButton(SendMessage sendMessage){//Buttons, which send the messages
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();//Initialise the keyboard
        sendMessage.setReplyMarkup(replyKeyboardMarkup);//associate a message with our keyboard
        replyKeyboardMarkup.setSelective(true);//Parameter, which show keyboard to all users
        replyKeyboardMarkup.setResizeKeyboard(true);//automatically select the size of the keyboard
        replyKeyboardMarkup.setOneTimeKeyboard(false);//Parameter, hide buttons for the user or not

        List<KeyboardRow> keyboardRowList = new ArrayList<>();//List of buttons
        KeyboardRow keyboardFRow = new KeyboardRow();//Firs line of our keyboard
        keyboardFRow.add(new KeyboardButton("/start"));
        keyboardFRow.add(new KeyboardButton("/help"));
        keyboardFRow.add(new KeyboardButton("/settings"));


        keyboardRowList.add(keyboardFRow);//Add all rows to the list
        replyKeyboardMarkup.setKeyboard(keyboardRowList);//Install  list in the keyboard

    }
    @Override
    public String getBotUsername() {
        return "Adilkhan123_bot";
    }//For return our name of bot, when I'm write to BotFather
@Override
    public String getBotToken() {
        return "1235410761:AAH4abaR4QW2O3WzCl4IYRrhK-5Vv5fuLlg";
    }//Is it our token, when I'm take from BotFather
}

