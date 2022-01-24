package lapr.project.ui;

import lapr.project.controller.FleetMAnagerController;
import lapr.project.controller.TrafficManagerController;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.util.Scanner;

public class FleetManagerUI {

    private String shipId = "999999999";
    private Integer year = 2020;
    private String initialdate = "01/01/2022 08:00";
    private String finaldate = "10/01/2022 08:00";

    public FleetManagerUI(){
        //Creation Only
    }
    public void runUI(DatabaseConnection databaseConnection) throws IOException {
        FleetMAnagerController fleetManagerController = new FleetMAnagerController();


        Scanner scanner = new Scanner(System.in);


        boolean flag = true;
        while (flag) {
            System.out.println("Fleet Manager!" +
                    "\nPlease Select the task from the following:" +
                    "\n1 - Get Number of Days Idle" +
                    "\n2 - Get OR for a Given Period" +
                    "\n3 - Get Ships above Threshold" +
                    "\nE - Exit");

            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1":
                    fleetManagerController.numberdays(databaseConnection);
                    break;
                case "2":
                    fleetManagerController.ocupacionPeriod(shipId,initialdate,finaldate,databaseConnection);
                    break;
                case "3":
                    fleetManagerController.aboveThreshold(databaseConnection);
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }
}
