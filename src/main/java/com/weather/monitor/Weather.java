package com.weather.monitor;

public class Weather {

    private String Temperature;
    private String Raindrop, Humidity;



    public Weather() {
    }

    public Weather(String Temperature, String Raindrop, String Humidity) {
        this.Temperature = Temperature;
        this.Raindrop = Raindrop;
        this.Humidity = Humidity;
    }

    public String getTemperature() {
        return this.Temperature;
    }

    public void setTemperature(String Temperature) {
        this.Temperature = Temperature;
    }

    public String getRaindrop() {
        return this.Raindrop;
    }

    public void setRaindrop(String Raindrop) {
        this.Raindrop = Raindrop;
    }

    public String getHumidity() {
        return this.Humidity;
    }

    public void setHumidity(String Humidity) {
        this.Humidity = Humidity;
    }

    public Weather Temperature(String Temperature) {
        this.Temperature = Temperature;
        return this;
    }

    public Weather Raindrop(String Raindrop) {
        this.Raindrop = Raindrop;
        return this;
    }

    public Weather Humidity(String Humidity) {
        this.Humidity = Humidity;
        return this;
    }

    
}
