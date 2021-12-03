package lapr.project.ui;

import lapr.project.controller.ShipCaptainController;
import lapr.project.controller.TrafficManagerController;
import lapr.project.model.CargoManifest;

import java.util.Scanner;

public class ShipCaptainUI {
    ShipCaptainController shipCaptainController = new ShipCaptainController();
    private CargoManifest cargoManifest = new CargoManifest();
    private String date= "31/12/2020 00:01";


    public ShipCaptainUI(){
        //Creation Only
    }

    public void runUI() {
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
                    "\nE - Exit");

            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1":
                    shipCaptainController.offloaded();
                    break;
                case "2":
                    shipCaptainController.loaded();
                    break;
                case "3":
                    shipCaptainController.yearlymainfest();
                    break;
                case "4":
                    shipCaptainController.occupancyratemanifest(cargoManifest);
                    break;
                case "5":
                    shipCaptainController.occupancyrateTime(date);
                    break;
                case "E":
                    flag = false;
                    break;
                default:
            }
        }



    }
}
