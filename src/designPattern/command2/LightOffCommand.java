package designPattern.command2;

/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class LightOffCommand implements Command{
    Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    public void execute(){
        light.off();
    }

    public void undo(){
        light.on();
    }
}
