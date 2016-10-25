package designPattern.proxy;

import java.io.Serializable;

/**
 * Created by chenxiaoxue on 8/1/16.
 */
public interface State extends Serializable{
    public void insertQuarter();
    public void ejectQuater();
    public void turnCrank();
    public void dispense();


}
