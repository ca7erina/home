package designPattern.command2;

/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class Light {

    String name;

    public Light(String name){
        this.name = name;
    }

    public void on(){
        System.out.println(name+ " Light is on");
    }

    public void off(){
        System.out.println(name +" Light is off");
    }
}
