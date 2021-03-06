package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.FleetManagerDatabase;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FleetMAnagerController {

    private FleetManagerDatabase fleetManagerDatabase = new FleetManagerDatabase();

    public File numberdays(DatabaseConnection databaseConnection) throws IOException {
        File myObj = new File("DaysIdle.txt");
        FileWriter myWriter = new FileWriter("DaysIdle.txt");
        myWriter.write(fleetManagerDatabase.daysIdle(databaseConnection));
        myWriter.close();


        return  myObj;
    }

    public File ocupacionPeriod(String shipId, String initialdate, String finaldate, DatabaseConnection databaseConnection) throws IOException {
        File myObj = new File("OcopancyRate-"+shipId+".txt");
        FileWriter myWriter = new FileWriter("OcopancyRate-"+shipId+".txt");
        myWriter.write(fleetManagerDatabase.ocupacionPeriod(shipId,initialdate,finaldate,databaseConnection));
        myWriter.close();


        return  myObj;
    }

    public File aboveThreshold(DatabaseConnection databaseConnection) throws IOException {
        File myObj = new File("AboveThreshold.txt");
        FileWriter myWriter = new FileWriter("AboveThreshold.txt");
        myWriter.write(fleetManagerDatabase.aboveThreshold(databaseConnection));
        myWriter.close();


        return  myObj;
    }

    public FleetManagerDatabase getFMD(){return fleetManagerDatabase;}
}
