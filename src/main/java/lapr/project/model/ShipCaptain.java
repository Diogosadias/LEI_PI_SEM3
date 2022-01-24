package lapr.project.model;

import oracle.ucp.util.Pair;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShipCaptain {

    private Integer shipCaptain_id;

    private String s2 = "-------------------------------------------------------------------------------------------------";

    public ShipCaptain(Integer i) {
        shipCaptain_id = i;
    }

    public String occupancyRateManifest(DatabaseConnection databaseConnection, Integer cargoID, String ship_id) {
        Double rate = null;
        if (cargoID == null) {
            return "Cargo Manifest was not Found!";
        }

        ShipDatabase shipDatabase = new ShipDatabase();

        rate = shipDatabase.getORM(databaseConnection, cargoID, ship_id, rate);

        if (rate == null) {
            return "Rate was not Calculated!";
        }

        String rateS = String.format("%.2f", rate);

        return "| The Rate for the Cargo Manifest was = " + rateS + "% |\n" + s2;

    }

    public String occupancyRateTime(DatabaseConnection databaseConnection, String ship_id, String date) {
        Double rate = null;
        if (date == null) {
            return "Cargo Manifest was not Found!";
        }

        ShipDatabase shipDatabase = new ShipDatabase();

        rate = shipDatabase.getOCT(databaseConnection, ship_id, date);

        if (rate == null) {
            return "Rate was not Calculated!";
        }

        String rateS = String.format("%.2f", rate);

        return "| The Rate for the Cargo Manifest at the Time was = " + rateS + "% |\n" + s2;

    }

    public Integer getId() {
        return shipCaptain_id;
    }

    public String offload(DatabaseConnection databaseConnection, Integer id) {
        List<Container> containers = new ArrayList<>();

        if (id == null) {
            return "Ship Captain Id was not Found!";
        }

        ShipDatabase shipDatabase = new ShipDatabase();

        containers = shipDatabase.getOffload(databaseConnection, id);

        if (containers == null || containers.size() == 0) {
            return "There are no Containers to Offload!";
        }

        String print = "The Containers to OffLoad next Port: \n";

        for (Container c : containers) {
            print = print + "\n" + c.toString();
        }

        return print + "|\n" + s2;

    }

    public String load(DatabaseConnection databaseConnection, Integer id) {
        List<Container> containers = new ArrayList<>();

        if (id == null) {
            return "Ship Captain Id was not Found!";
        }

        ShipDatabase shipDatabase = new ShipDatabase();

        containers = shipDatabase.getLoad(databaseConnection, id);

        if (containers == null || containers.size() == 0) {
            return "There are no Containers to Load!";
        }

        String print = "The Containers to be Loaded next Port: \n";

        for (Container c : containers) {
            print = print + "\n" + c.toStringwPosition();
        }

        return print + "|\n" + s2;
    }

    public String yearly(DatabaseConnection databaseConnection, String year, String ship_id) {
        Pair<Integer, Double> c;

        if (year == null) {
            return "Year is not Valid!";
        }

        ShipDatabase shipDatabase = new ShipDatabase();

        c = shipDatabase.year(databaseConnection, year, ship_id);

        if (c.get1st() == null) {
            return "There are no Container Information for this Year!";
        }

        return "The total number of Manifest is " + c.get1st()
                + "\n And the average number of container per Manifest is : " + c.get2nd() + "\n" + s2;
    }

    public String calculate(String IMO) {

        //constants
        int containers = 50;
        int density = 997;
        double Earthgravity = 9.807;
        double containerMass = 1000 / 2;

        //variables
        double containersMass;
        double height;
        double width;
        double length;
        double mass;
        double weight;
        double submerseHeight;
        double submerseWidth;
        double submerseArea;
        double pressure;

        if (IMO.equals("IMO9103685")) {

            length = 294.00;
            width = 32.00;
            height = 30.00;
            weight = 60200000;
            containersMass = containers * containerMass;
            mass = weight + containersMass;
            submerseHeight = Math.sqrt(((mass / density) * height * 2) / (width * length));
            submerseWidth = ((submerseHeight / height) * width);
            submerseArea = 2 * ((submerseHeight * submerseWidth) / 2) + (2 * (width * length));
            pressure = (weight * Earthgravity) / submerseArea;
            return "The total mass placed on the vessel with IMO " + IMO + " is: " + containersMass + " kg, the pressure exerted by that mass is: " + pressure + " P and the difference in height is: " + submerseHeight + " m";
        } else if (IMO.equals("IMO9655169")) {
            length = 168.00;
            width = 26.00;
            height = 30.00;
            weight = 25240000;
            containersMass = containers * containerMass;
            mass = weight + containersMass;
            submerseHeight = Math.sqrt(((mass / density) * height * 2) / (width * length));
            submerseWidth = ((submerseHeight / height) * width);
            submerseArea = 2 * ((submerseHeight * submerseWidth) / 2) + (2 * (width * length));
            pressure = (weight * Earthgravity) / submerseArea;
            return "The total mass placed on the vessel with IMO " + IMO + " is: " + containersMass + " kg, the pressure exerted by that mass is: " + pressure + " P and the difference in height is: " + submerseHeight + " m";
        } else if (IMO.equals("IMO9776171")) {
            length = 400.00;
            width = 59.00;
            height = 30.00;
            weight = 191422000;
            containersMass = containers * containerMass;
            mass = weight + containersMass;
            submerseHeight = Math.sqrt(((mass / density) * height * 2) / (width * length));
            submerseWidth = ((submerseHeight / height) * width);
            submerseArea = 2 * ((submerseHeight * submerseWidth) / 2) + (2 * (width * length));
            pressure = (weight * Earthgravity) / submerseArea;
            return "The total mass placed on the vessel with IMO " + IMO + " is: " + containersMass + " kg, the pressure exerted by that mass is: " + pressure + " P and the difference in height is: " + submerseHeight + " m";
        } else {
            return "The IMO was invalid!";
        }
    }
}
