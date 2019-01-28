package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client extends Application {

    public static void main(String argv[]) throws RemoteException, MalformedURLException, NotBoundException {
        launch(argv);

    }

    @Override
    public void start(Stage primaryStage) throws RemoteException, MalformedURLException,Exception {

        ZooImpl monZoo = (ZooImpl)Naming.lookup("//localhost/ZooImpl");
        monZoo.beginZoo(primaryStage);
    }
}
