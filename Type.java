public class Type {
//When I'm type the message to the bot, he will send a request to API with this type, API will return JSON, we will fill our type with data. We will parse our JSON, and send back our message to users
    private String name;
    private Double feels_like;
    private Double temp;
    private Double humidity;


    //Create own properties, which describes our types(GETTERS and SETTERS)заполнить и вытащить наши значения
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    private String icon;
    private String main;

    public Double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }

}
