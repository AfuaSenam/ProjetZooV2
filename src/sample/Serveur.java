package sample;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur  {

    public static void main(String[] args) {

        try {
            ZooImpl monZoo = new ZooImpl();
            Registry a= LocateRegistry.createRegistry(1089);
            //ZooImpl monZoo = (ZooImpl)Naming.lookup("//localhost/ZooImpl");
            a.bind("zoo",monZoo);
           // Naming.bind("rmi://localhost/ZooImpl", monZoo);
            System.out.println("Zoo enregistré " + monZoo);
        } catch (Exception e) {
            System.out.println("ERREUR");
            e.printStackTrace();
        }
    }
}
