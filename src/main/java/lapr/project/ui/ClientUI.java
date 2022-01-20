package lapr.project.ui;

import lapr.project.controller.ClientController;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.util.Scanner;

public class ClientUI {
    ClientController clientController = new ClientController();
    String code = "15";
    String code3 = "99";
    String clientId = "1";
    String clientId2 = "2";
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
                    clientController.searchPosition(databaseConnection,clientId,code);
                    clientController.searchPosition(databaseConnection,clientId2,code);
                    clientController.searchPosition(databaseConnection,clientId,code3);
                    break;
                case "E":
                    flag = false;
                    break;
                default:
            }
        }
    }
}
