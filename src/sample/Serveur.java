package sample;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur  {

    public static void main(String[] args) {

        try {
            ZooImpl monZoo = new ZooImpl();
            Registry reg= LocateRegistry.createRegistry(1099);
            reg.bind("zoo",monZoo);
            System.out.println("Zoo bon " + monZoo);
            while(true){

            }
        } catch (Exception e) {
            System.out.println("ERREUR");
            e.printStackTrace();
        }
    }
}
