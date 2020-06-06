import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class Weather {
    //dc7ce8bcb3744d53986ebfa38ab3998c-My API token from site:"OpenWeather"
    public static String getWeather(String message, Type model) throws IOException {//Method, whicg retunr to us String in which we will be send our message and type
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=dc7ce8bcb3744d53986ebfa38ab3998c");//извлекаем содержимое при помощи класса URL
        Scanner in = new Scanner((InputStream) url.getContent());//TExt scanner
        String result = "";
        while (in.hasNext()) {//From loop we are read flow(поток)
            result += in.nextLine();//REsult will add one string. Считываем всю строку и сохраняем в переменную
        }
        JSONObject object = new JSONObject(result);//Кастим JSON объект в нашу строку
        model.setName(object.getString("name"));//Take our model, object from object return string. Получаем наш город
        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        JSONArray getArray = object.getJSONArray("weather");//For whatch array, where all data
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));//After read the array
        }
        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemp() + "C" + "\n" +
                "Humidity: " + model.getHumidity() + "%" + "\n" +
                "Fells like: " + model.getFeels_like() + "C" + "\n" +
                "Main: " + model.getMain() + "\n" +
                "http://openweathermap.org/img/w/" + model.getIcon() + ".png";
    }
}