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
        portManagerController.importPort(filename);
    }
}
