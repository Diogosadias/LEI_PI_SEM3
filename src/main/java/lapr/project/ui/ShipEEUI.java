package lapr.project.ui;

import java.util.Scanner;

public class ShipEEUI {

    public ShipEEUI(){
        //only-creation
    }

    public void runUI(){
        Scanner scanner = new Scanner(System.in);
        boolean flag =true;
        while(flag){
            System.out.println("Dear Ship Chief Electrical Engineer!" +
                    "\nPlease Select the task from the following:" +
                    "\n1 - Receive Alert!" +
                    "\nE - Exit");

            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1":
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
