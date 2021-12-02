package lapr.project.ui;

import lapr.project.controller.PortManagerController;
import lapr.project.data.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class PortManagerUI {
    PortManagerController portManagerController = new PortManagerController();
    String filename = "Data/data-ships&ports/bports.csv";

    public PortManagerUI(){
        //Creation Only
    }

    public void runUI(DatabaseConnection databaseConnection) throws IOException, SQLException {
        if(portManagerController.importPort(filename)!= null){
            System.out.println("Import Success, check File!");
            //portManagerController.importToDatabase(databaseConnection,portManagerController.getPortManager().getPortTree());
        }
        else System.out.println("Import Not Success, please check File!");
    }
}
