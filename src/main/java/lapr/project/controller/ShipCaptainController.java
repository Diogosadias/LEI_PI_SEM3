package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.ShipCaptain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static lapr.project.model.TemporalMessages.getDate;

public class ShipCaptainController {
    private ShipCaptain shipCaptain = new ShipCaptain(12345);


    public File offloaded(DatabaseConnection databaseConnection) throws IOException {
        File myObj = new File("Offload.txt");
        FileWriter myWriter = new FileWriter("Offload.txt");
        myWriter.write(shipCaptain.offload(databaseConnection, shipCaptain.getId()));
        myWriter.close();

        return  myObj;
    }

    public File loaded(DatabaseConnection databaseConnection) throws IOException {
        File myObj = new File("Load.txt");
        FileWriter myWriter = new FileWriter("Load.txt");
        myWriter.write(shipCaptain.load(databaseConnection, shipCaptain.getId()));
        myWriter.close();

        return  myObj;
    }

    public File yearlymainfest(DatabaseConnection databaseConnection, String year) throws IOException {
        File myObj = new File("YearInformation.txt");
        FileWriter myWriter = new FileWriter("YearInformation.txt");
        myWriter.write(shipCaptain.yearly(databaseConnection, year));
        myWriter.close();


        return  myObj;
    }



    public File occupancyrateTime(DatabaseConnection databaseConnection,String ship_id, String date) throws IOException {
        File myObj = new File("OccupancyRateTime.txt");
        FileWriter myWriter = new FileWriter("OccupancyRateTime.txt");
        myWriter.write(shipCaptain.occupancyRateTime(databaseConnection, ship_id,getDate(date)));
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
