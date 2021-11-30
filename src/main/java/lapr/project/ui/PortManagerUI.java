package lapr.project.ui;

import lapr.project.controller.PortManagerController;

import java.io.IOException;

public class PortManagerUI {
    PortManagerController portManagerController = new PortManagerController();
    String filename = "Data/data-ships&ports/bports.csv";

    public PortManagerUI(){
        //Creation Only
    }

    public void runUI() throws IOException {
        if(portManagerController.importPort(filename)!= null) System.out.println("Import Success, check File!");
        else System.out.println("Import Not Success, please check File!");
    }
}
