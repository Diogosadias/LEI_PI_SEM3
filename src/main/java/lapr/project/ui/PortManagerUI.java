package lapr.project.ui;

import lapr.project.model.PortManagerController;

import java.io.IOException;

public class PortManagerUI {
    PortManagerController portManagerController = new PortManagerController();
    String filename = "Data/data-ships&ports/bports.csv";

    public PortManagerUI(){

    }

    public void runUI() throws IOException {
        portManagerController.importPort(filename);
    }
}
