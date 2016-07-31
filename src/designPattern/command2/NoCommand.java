package designPattern.command2;

/**
 * Created by chenxiaoxue on 7/27/16.
 */
public class NoCommand implements Command {

    public void execute(){
      System.out.println("no command");
    }
    public void undo(){}
}
