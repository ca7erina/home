package designPattern.observer2;

import designPattern.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;


public class CurrentConditionsDisplay implements Observer,DisplayElement {
    Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable obs, Object arg) {
        if(obs instanceof WeatherData){
            WeatherData weatherData = (WeatherData)obs;
            this.humidity = weatherData.getHumididty();
            this.temperature = weatherData.getTemperature();
            display();
        }
    }

    public void display() {
        System.out.println("Current Conditions: "+ temperature +" F degrees and "+ humidity +" % humidity");

    }



}
