package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipCaptain {

    private String s2 = "-------------------------------------------------------------------------------------------------";

    public String occupancyRateManifest(DatabaseConnection databaseConnection, Integer cargoID, String ship_id) {
        Double rate = null;
        if(cargoID==null) return "Cargo Manifest was not Found!";

        ShipDatabase shipDatabase = new ShipDatabase();

        rate = shipDatabase.getORM(databaseConnection,cargoID,ship_id);


        if(rate==null) return "Rate was not Calculated!";

        String rateS = String.valueOf(System.out.format("double : %.2f", rate));

        return "| The Rate for the Cargo Manifest was = " + rateS + "|\n" +s2;


    }

    public String occupancyRateTime(DatabaseConnection databaseConnection,String ship_id, LocalDateTime date) {
        Double rate = null;
        if(date==null) return "Cargo Manifest was not Found!";

        ShipDatabase shipDatabase = new ShipDatabase();

        rate = shipDatabase.getOCT(databaseConnection,ship_id,date);


        if(rate==null) return "Rate was not Calculated!";

        String rateS = String.valueOf(System.out.format("double : %.2f", rate));

        return "| The Rate for the Cargo Manifest at the Time was = " + rateS + "|\n" +s2;


    }
}
