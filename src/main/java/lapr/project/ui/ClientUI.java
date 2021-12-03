package lapr.project.ui;

import lapr.project.controller.ClientController;

import java.util.Scanner;

public class ClientUI {
    ClientController clientController = new ClientController();
    String code = "211331640";

    public void runUI() {
        Scanner scanner = new Scanner(System.in);
        boolean flag =true;
        while(flag){
            System.out.println("SDear Client!" +
                    "\nPlease Select the task from the following:" +
                    "\n1 - Get info about container Location" +
                    "\nE - Exit");

            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1":
                    clientController.searchPosition(code);
                    break;
                case "E":
                    flag = false;
                    break;
                default:
            }
        }
    }
}
