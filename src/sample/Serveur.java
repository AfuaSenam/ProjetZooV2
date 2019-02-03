package sample;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur  extends ZooImpl{

    public Serveur() throws RemoteException {
    }

    public static void main(String[] args) {

        try {
            ZooImpl monZoo = new ZooImpl();
            Zoo stub = (Zoo) UnicastRemoteObject.exportObject(monZoo, 1099);
            Registry reg= LocateRegistry.createRegistry(1099);
            reg.bind("zoo",stub);
            System.out.println("Zoo bon " + monZoo);
            while(true){

            }
        } catch (Exception e) {
            System.out.println("ERREUR");
            e.printStackTrace();
        }
    }
}
