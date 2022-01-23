package lapr.project.controller;

import lapr.project.data.ClientDatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ShipEEController {
    private ClientDatabase clientDatabase = new ClientDatabase();

    public ShipEEController(){
        //creation only
    }


    public File file(String filename) throws IOException {
        File myObj = new File("ReportEnergy.txt");
        FileWriter myWriter = new FileWriter("ReportEnergy.txt");
        myWriter.write(clientDatabase.readReport(filename));
        myWriter.close();


        return  myObj;
    }
}
