package designPattern.command2;

/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class Stereo {
    String name;
    public Stereo(String name){
        this.name = name;
    }


    public void on(){
        System.out.println(name+ " Stereo is on");
    }

    public void off(){
        System.out.println(name+ " Stereo is off");
    }

    public void setCD(){
        System.out.println(name+ " Stereo is set for CD input");
    }
    public void setDVD(){
        System.out.println(name+ " Stereo is set for DVD input");
    }
    public void setVolume(){
        System.out.println(name+" Stereo volume set to 11");
    }

}
