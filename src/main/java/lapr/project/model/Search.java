package lapr.project.model;

import lapr.project.controller.TrafficManagerController;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import lapr.project.data.ShipDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lapr.project.model.TemporalMessages.getDate;

public class Search {

    private String s = "Input is Invalid!";
    private String s2 = "-------------------------------------------------------------------------------------------------";

    public Search() {
        //only Initiated
    }

    public String searchDetails(Object code, TrafficManagerController main) throws IOException {
        if (code == null) {
            throw new IOException(s);
        }
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).toString() + "\n" + s2;
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).toString() + "\n" + s2;
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).toString() + "\n" + s2;
            } else {
                return "Ship Code was not according regulations!" + "\n" + s2;
            }
        }
        return s2;
    }

    public String searchDate(Object code, Object date, TrafficManagerController main) throws IOException {
        if (code == null || date == null) {
            throw new IOException(s);
        }
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).getMovements().getMoveByDate(date) + "\n" + s2;
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).getMovements().getMoveByDate(date) + "\n" + s2;
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).getMovements().getMoveByDate(date) + "\n" + s2;
            } else {
                return "Ship Code was not according regulations!" + "\n" + s2;
            }
        }
        return s2;
    }

    public String searchDate(Object code, Object date1, Object date2, TrafficManagerController main) throws IOException {
        if (code == null || date1 == null || date2 == null) {
            throw new IOException(s);
        }
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).getMovements().searchDateFrame(date1, date2) + "\n" + s2;
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).getMovements().searchDateFrame(date1, date2) + "\n" + s2;
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).getMovements().searchDateFrame(date1, date2) + "\n" + s2;
            } else {
                return "Ship Code was not according regulations!" + "\n" + s2;
            }
        }
        return s2;
    }

    public String summary(Object code, TrafficManagerController main) throws IOException {
        if (code == null) {
            throw new IOException(s);
        }
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).getSummary(code) + "\n" + s2;
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).getSummary(code) + "\n" + s2;
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).getSummary(code) + "\n" + s2;
            } else {
                return "Ship Code was not according regulations!" + "\n" + s2;
            }
        }
        return s2;
    }

    public String getClosestPort(DatabaseConnection databaseConnection, Object code3, Object date1, TrafficManagerController main) throws IOException {

        PortTree portTree = new PortTree();
        ImportPortDatabase importPortDatabase = new ImportPortDatabase();

        Connection connection = databaseConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            if (!importPortDatabase.getPortData(databaseConnection, portTree)) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Ports Retrieved From Database!");


        }catch(SQLException ex){
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PortTree.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        if (code3 == null) {
            throw new IOException(s);
        }

        double a[] = new double[2];

        if (main.csTree.isCS(code3)) {
            if (main.csTree.find(code3)) {
                a = main.csTree.getShip(code3).getCoordsbyBaseDateTime(date1);
                return portTree.findNearesNeighbour(a[0], a[1]).toString() + "\n" + s2;
            }
        } else {
            return "Ship Code was not according regulations!" + "\n" + s2;

        }

        return s2;
    }

    public String nextMonday(DatabaseConnection databaseConnection, String date1) throws IOException {
        Ship ship = null;
        if(date1==null) return "Date is not Valid!";
        ShipDatabase shipDatabase = new ShipDatabase();

        ship = shipDatabase.getNextMonday(databaseConnection,date1);


        if(ship==null) return "Ship was not Found!";


        return "The Ship Avilable next Monday is : \n" + ship.toString() + "|\n" +s2;
    }
}
