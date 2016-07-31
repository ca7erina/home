package designPattern.command2;

/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class RemoteControlTest {

    public static void main(String[] args){
        RemoteControl remote = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        Stereo stereo = new Stereo("Living Room");

        LightOnCommand  livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        LightOnCommand  kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);


        remote.setCommand(0,livingRoomLightOn,livingRoomLightOff);
        remote.setCommand(1,kitchenLightOn,kitchenLightOff);
        remote.setCommand(2,stereoOnWithCD,stereoOff);

        System.out.println(remote);

        remote.onButtonWasPressed(0);
        remote.offButtonWasPressed(0);
        System.out.println("undo");
        remote.undoButtonWasPushed();

        remote.onButtonWasPressed(1);
        remote.offButtonWasPressed(1);

        remote.onButtonWasPressed(2);
        remote.offButtonWasPressed(2);


    }
}
