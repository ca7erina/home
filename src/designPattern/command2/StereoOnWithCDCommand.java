package designPattern.command2;

/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class StereoOnWithCDCommand implements Command{
    Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo){
        this.stereo = stereo;
    }

    public void execute(){
        stereo.on();
        stereo.setCD();
        stereo.setVolume();
    }

    public void undo(){
        stereo.off();
    }
}
