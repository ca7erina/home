package designPattern.observer;

import java.util.ArrayList;


public class WeatherData implements Subject {
    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){
        observers = new ArrayList();
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if(i>=0){
            observers.remove(i);
        }

    }

    @Override
    public void notifyObservers() {
        for(int i=0;i<observers.size();i++){
            Observer ob = (Observer)observers.get(i);
            ob.update(temperature,humidity,pressure); // java Observable: update(Observable obs)
        }

    }


    public void measurementsChanged(){
        notifyObservers();
    }

    public void setMeasurents (float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
