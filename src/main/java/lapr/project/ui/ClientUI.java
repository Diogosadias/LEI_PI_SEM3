package lapr.project.ui;

import lapr.project.controller.ClientController;

public class ClientUI {
    ClientController clientController = new ClientController();
    String code = "211331640";

    public void runUI() {
        clientController.searchPosition(code);
    }
}
