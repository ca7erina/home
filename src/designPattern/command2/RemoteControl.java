package designPattern.command2;

/**
 * Remote Control has 7 slots, each slot has 2 buttons, on and off
 */
public class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;
    Command undoConmmand;

    public RemoteControl(){
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for(int i=0;i<7;i++){
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }

        undoConmmand = noCommand;
    }
    public void setCommand(int slot, Command onCommand, Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }
    public void onButtonWasPressed(int slot){
            onCommands[slot].execute();
            undoConmmand = onCommands[slot];

    }

    public void offButtonWasPressed(int slot){
        offCommands[slot].execute();
        undoConmmand = offCommands[slot];
    }

    public void undoButtonWasPushed(){
        undoConmmand.undo();
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("\n----- Remote Control -----\n");
        for(int i=0;i<onCommands.length;i++){
            sb.append("[slot "+i+"] "+onCommands[i].getClass().getName()+"    "+ offCommands[i].getClass().getName()+"\n");

        }
        return sb.toString();
    }

}
