package lapr.project.ui;

import lapr.project.controller.PortStaffController;

import java.io.IOException;
import java.util.Scanner;

public class PortStaffUI {
    private String filename="docs/Sprint4/US411/Energy_Results.txt";




    public PortStaffUI() throws IOException {
        //Creation Only
    }

    public void runUI() throws IOException {

        Scanner scanner = new Scanner(System.in);
        boolean flag =true;
        while(flag){
            System.out.println("Dear Port Staff!" +
                    "\nPlease Select the task from the following:" +
                    "\n1 - Fill Dynamically" +
                    "\nE - Exit");

            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1":
                    PortStaffController portStaffController = new PortStaffController();
                    portStaffController.fillDina(filename);
                    break;
                case "E":
                    flag = false;
                    break;
                default:
            }
        }



    }
}
