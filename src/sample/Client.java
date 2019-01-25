package sample;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String argv[]) throws RemoteException, MalformedURLException, NotBoundException {
        ZooImpl monZoo = (ZooImpl)Naming.lookup("//localhost/ZooImpl");
    }
}
