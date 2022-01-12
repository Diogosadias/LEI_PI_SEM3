package lapr.project.ui;

import lapr.project.controller.PortManagerController;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class PortManagerUI {
    PortManagerController portManagerController = new PortManagerController();
    String filename = "Data/data-ships&ports/bports.csv";

    public PortManagerUI(){
        //Creation Only
    }

    public void runUI(DatabaseConnection databaseConnection) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean flag =true;
        while(flag){
            System.out.println("Dear Port Manager!" +
                    "\nPlease Select the task from the following:" +
                    "\n1 - Import Ports" +
                    "\n2 - Map Occupation Resources" +
                    "\n3 - Generate Report to Check Resources" +
                    "\n4 - Develop Data Model" +
                    "\nE - Exit");

            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1":
                    if(portManagerController.importPort(filename)!= null){
                        System.out.println("Import Success, check File!");
                        portManagerController.importToDatabase(databaseConnection,portManagerController.getPortManager().getPortTree());
                    }
                    else System.out.println("Import Not Success, please check File!");
                    break;
                case "2":
                    portManagerController.mapResources(databaseConnection);
                    break;
                case "3":
                    //portManagerController.generateReport(databaseConnection);
                    break;
                case "4":
                    //portManagerController.developModel(databaseConnection);
                    break;
                case "E":
                    flag = false;
                    break;
                default:
            }
        }
    }
}
