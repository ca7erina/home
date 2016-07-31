package designPattern.observer;

/**
 * Created by chenxiaoxue on 7/10/16.
 */
public interface Subject {

    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();


}
