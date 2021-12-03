package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.ShipCaptain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ShipCaptainController {
    private ShipCaptain shipCaptain = new ShipCaptain();


    public void offloaded() {
    }

    public void loaded() {
    }

    public void yearlymainfest() {
    }



    public File occupancyrateTime(DatabaseConnection databaseConnection,String ship_id, String date) throws IOException {
        File myObj = new File("OccupancyRateTime - " + date + ".txt");
        FileWriter myWriter = new FileWriter("OccupancyRateTime - " + date + ".txt");
        myWriter.write(shipCaptain.occupancyRateTime(databaseConnection, ship_id,date));
        myWriter.close();


        return  myObj;
    }

    public File occupancyratemanifest(DatabaseConnection databaseConnection, Integer cargoID, String ship_id) throws IOException {
        File myObj = new File("OccupancyRate - " + cargoID + ".txt");
        FileWriter myWriter = new FileWriter("OccupancyRate - " + cargoID + ".txt");
        myWriter.write(shipCaptain.occupancyRateManifest(databaseConnection, cargoID,ship_id));
        myWriter.close();


        return  myObj;
    }
}
