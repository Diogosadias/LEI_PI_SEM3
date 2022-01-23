package lapr.project.ui;

import lapr.project.controller.ShipEEController;

import java.io.IOException;
import java.util.Scanner;

public class ShipEEUI {
    private ShipEEController shipEEController = new ShipEEController();
    private String filename="docs/Sprint4/US411/Energy_Results.txt";

    public ShipEEUI(){
        //only-creation
    }

    public void runUI() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean flag =true;
        while(flag){
            System.out.println("Dear Ship Chief Electrical Engineer!" +
                    "\nPlease Select the task from the following:" +
                    "\n1 - Energy Container" +
                    "\n2 - Report Energy Needed" +
                    "\nE - Exit");

            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1":
                    shipEEController.file(filename);
                    //ARQCP function
                    break;
                case "2":
                    shipEEController.file2(filename);
                    //ARQCP function
                    break;
                case "E":
                    flag = false;
                    break;
                default:
            }
        }

    }
}
