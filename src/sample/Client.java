package sample;

import javafx.application.Application;
import javafx.stage.Stage;

//import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String argv[]) throws Exception {
        Registry a= LocateRegistry.getRegistry("localhost",1099);
        Zoo zoo = (Zoo) a.lookup("zoo");
        zoo.setNomZoo("okk");
        System.out.println(zoo.getNomZoo());
        zoo.beginZoo();
    }
}
