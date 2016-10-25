package designPattern.proxy.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by chenxiaoxue on 8/2/16.
 */
public interface MyRemote extends Remote{
    public String sayHello() throws RemoteException;
}
