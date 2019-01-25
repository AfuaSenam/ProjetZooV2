package sample;

import java.rmi.Naming;

public class Serveur  {

    public static void main(String[] args) {
        try {
            ZooImpl monZoo = new ZooImpl();
            Naming.bind("rmi://localhost/ZooImpl", monZoo);
            System.out.println("Zoo enregistré " + monZoo);
        } catch (Exception e) {
            System.out.println("ERREUR");
            e.printStackTrace();
        }
    }
}
