package designPattern.command2;


/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class LightOnCommand implements Command{
   Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    public void execute(){
        light.on();
    }

    public void undo(){
        light.off();
    }
}
