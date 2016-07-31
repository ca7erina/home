package designPattern.command2;

/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class StereoOffCommand implements Command{
    Stereo stereo;

    public StereoOffCommand(Stereo stereo){
        this.stereo = stereo;
    }

    public void execute(){
        stereo.off();
    }

    public void undo(){
        stereo.on();

    }
}
