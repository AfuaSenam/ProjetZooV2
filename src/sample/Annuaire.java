package sample;

import java.rmi.registry.*;

public class Annuaire {

    public static void main(String[] args) {
        try {
            Registry a = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            ZooImpl Z = new ZooImpl();
            a.rebind("ZooImpl", Z);
            System.out.println("C'est bon" + Z);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
