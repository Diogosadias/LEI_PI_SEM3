package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.ShipCaptain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ShipCaptainController {
    public ShipCaptain shipCaptain = new ShipCaptain(12345);
    private Integer tripCode =50;


    public File offloaded(DatabaseConnection databaseConnection) throws IOException {
        File myObj = new File("Offload.txt");
        try (FileWriter myWriter = new FileWriter("Offload.txt")) {
            myWriter.write(shipCaptain.offload(databaseConnection, tripCode));
        }

        return  myObj;
    }

    public File loaded(DatabaseConnection databaseConnection) throws IOException {
        File myObj = new File("Load.txt");
        try (FileWriter myWriter = new FileWriter("Load.txt")) {
            myWriter.write(shipCaptain.load(databaseConnection, tripCode));
        }

        return  myObj;
    }

    public File yearlymainfest(DatabaseConnection databaseConnection, String year, String shipId) throws IOException {
        File myObj = new File("YearInformation.txt");
        try (FileWriter myWriter = new FileWriter("YearInformation.txt")) {
            myWriter.write(shipCaptain.yearly(databaseConnection, year,shipId));
        }


        return  myObj;
    }



    public File occupancyrateTime(DatabaseConnection databaseConnection,String ShipId, String date) throws IOException {
        File myObj = new File("OccupancyRateTime.txt");
        try (FileWriter myWriter = new FileWriter("OccupancyRateTime.txt")) {
            myWriter.write(shipCaptain.occupancyRateTime(databaseConnection, ShipId,date));
        }


        return  myObj;
    }

    public File occupancyratemanifest(DatabaseConnection databaseConnection, Integer cargoID, String ShipId) throws IOException {
        File myObj = new File("OccupancyRate - " + cargoID + ".txt");
        try (FileWriter myWriter = new FileWriter("OccupancyRate - " + cargoID + ".txt")) {
            myWriter.write(shipCaptain.occupancyRateManifest(databaseConnection, cargoID,ShipId));
        }


        return  myObj;
    }

    public File calculate(String vessel) throws IOException {
            File myObj = new File("Calculate.txt");
        try (FileWriter myWriter = new FileWriter("Calculate.txt")) {
            myWriter.write(shipCaptain.calculate(vessel));
        }


        return  myObj;
    }
}
