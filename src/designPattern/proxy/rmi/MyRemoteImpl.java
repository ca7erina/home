package designPattern.proxy.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by chenxiaoxue on 8/2/16.
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    @Override
    public String sayHello() throws RemoteException {
        return "Server says: Hello";
    }

    public MyRemoteImpl() throws RemoteException{

    }

    public static void main(String[] args){
        try{
            MyRemote service = new MyRemoteImpl();
            Naming.rebind("RemoteHello",service);


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
