package lapr.project.ui;

import lapr.project.controller.FleetMAnagerController;
import lapr.project.controller.TrafficManagerController;

import java.util.Scanner;

public class FleetManagerUI {

    public FleetManagerUI(){
        //Creation Only
    }
    public void runUI() {
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
                    //fleetManagerController.numberdays();
                    break;
                case "2":
                    //fleetManagerController.ocupacionPeriod();
                    break;
                case "3":
                    //fleetManagerController.aboveThreshold();
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }
}
