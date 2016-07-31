package designPattern.command1;

/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class RemoteControlTest {

    public static void main(String[] args){
        SimpleRemoteControl remote = new SimpleRemoteControl();

        Light light = new Light();
        LightOnCommand lightOncommand= new LightOnCommand(light);

        remote.setCommand(lightOncommand); // remote doesn't know light object,only know concreteommand
        remote.buttonWasPressed(); // remote's execute()-> concreteCommand's execute(): light.on();

    }
}
