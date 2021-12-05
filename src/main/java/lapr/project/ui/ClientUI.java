package lapr.project.ui;

import lapr.project.controller.ClientController;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.util.Scanner;

public class ClientUI {
    ClientController clientController = new ClientController();
    String code = "11";
    String code1 = "12345";

    public void runUI(DatabaseConnection databaseConnection) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean flag =true;
        while(flag){
            System.out.println("Dear Client!" +
                    "\nPlease Select the task from the following:" +
                    "\n1 - Get info about container Location" +
                    "\nE - Exit");

            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1":
                    clientController.searchPosition(databaseConnection,code);
                    break;
                case "2":
                    clientController.searchPosition(databaseConnection,code1);
                    break;
                case "E":
                    flag = false;
                    break;
                default:
            }
        }
    }
}
