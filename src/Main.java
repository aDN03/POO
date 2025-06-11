package src;

import src.Controller.Controller;

public class Main {
    public static void main(String[] args) {
        try {
            Controller controller = new Controller();
            controller.runUI();
        }
        catch (Exception e) {
            System.out.println("Error: "+e.getMessage()+" ["+ e +"]");
        }
    }
}
