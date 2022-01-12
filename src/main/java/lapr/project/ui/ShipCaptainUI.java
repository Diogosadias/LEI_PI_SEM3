package lapr.project.ui;

import lapr.project.controller.ShipCaptainController;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.util.Scanner;

public class ShipCaptainUI {
    ShipCaptainController shipCaptainController = new ShipCaptainController();
    private Integer cargoID = 55;
    private String ship_id="999999999";
    private String date= "31/01/2019 17:19";
    private String year = "2019";
    private String captain_id = "90";



    public ShipCaptainUI(){
        //Creation Only
    }

    public void runUI(DatabaseConnection databaseConnection) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean flag =true;
        while(flag){
            System.out.println("Ship Captain!" +
                    "\nPlease Select the task from the following:" +
                    "\n1 - Offloaded" +
                    "\n2 - Loaded" +
                    "\n3 - Yearly Manifest Information" +
                    "\n4 - Occupancy Rate" +
                    "\n5 - Occupancy Rate at Given Time" +
                    "\n6 - Search for Vessel Types" +
                    "\n7 - Determine Center of Mass" +
                    "\n8 - Get Sink Measure" +
                    "\nE - Exit");

            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1":
                    shipCaptainController.offloaded(databaseConnection);
                    break;
                case "2":
                    shipCaptainController.loaded(databaseConnection);
                    break;
                case "3":
                    shipCaptainController.yearlymainfest(databaseConnection,year,captain_id);
                    break;
                case "4":
                    shipCaptainController.occupancyratemanifest(databaseConnection,cargoID,ship_id);
                    break;
                case "5":
                    shipCaptainController.occupancyrateTime(databaseConnection,ship_id,date);
                    break;
                case "6":
                    //shipCaptainController.searchVesselTypes(databaseConnection,ship_id,date);
                    break;
                case "7":
                    //shipCaptainController.determineMassCenter(databaseConnection,ship_id,date);
                    break;
                case "8":
                    //shipCaptainController.sinkMeasure(databaseConnection,ship_id,date);
                    break;
                case "E":
                    flag = false;
                    break;
                default:
            }
        }



    }
}
