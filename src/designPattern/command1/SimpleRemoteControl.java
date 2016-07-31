package designPattern.command1;

/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class SimpleRemoteControl {
    Command slot;
    public SimpleRemoteControl(){}
    public void setCommand(Command command){
        slot = command;
    }
    public void buttonWasPressed(){
        slot.execute();
    }

}
