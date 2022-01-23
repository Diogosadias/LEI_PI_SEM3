package lapr.project.data;

import lapr.project.model.Client;
import lapr.project.model.Port;
import lapr.project.model.PortTree;
import lapr.project.model.Ship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDatabase {
    public Object searchPosition(DatabaseConnection databaseConnection,String clientId, String code, Object object) {

        try {
            object = executeClientStatment(databaseConnection,clientId, code);


        } catch (SQLException ex) {
            if(ex.getMessage().startsWith("ORA-20001: ERROR CODE 10")) object = "10";
            if(ex.getMessage().startsWith("ORA-20001: ERROR CODE 11")) object = "11";
            /*
            Logger.getLogger(ClientDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);

             */
        }
        return object;
    }



    private Object executeClientStatment(DatabaseConnection databaseConnection,String clientId, String containercode) throws SQLException {
        Object object =null;
        Connection connection = databaseConnection.getConnection();

        CallableStatement cstmt = connection.prepareCall("{? = call getCurrentSituationOfContainer(?,?)}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.setInt(2, Integer.parseInt(clientId));
        cstmt.setInt(3, Integer.parseInt(containercode));
        cstmt.executeUpdate();
        String rs = cstmt.getString(1);
        if(rs==null) return object;

        while (rs.length()!=0) {
            String testShip = rs.substring(0, 4);
            if (testShip.equals("SHIP")) {
                Ship s = getShipFromDatabase(databaseConnection, rs.substring(5));
                object = s;
                break;
            } else if (testShip.equals("PORT")) {
                Port p = getPortFromDatabase(databaseConnection, rs.substring(5));
                object = p;
                break;
            } else {
                Client c = null;
                object = c;
                break;
            }
        }

        return object;
    }

    private Port getPortFromDatabase(DatabaseConnection databaseConnection, String location) throws SQLException {
        String cont;
        String country;
        Double lat;
        Double lon;
        Port port =null;

        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "Select * From Port where Location =  ?";

        PreparedStatement getClientsPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getClientsPreparedStatement.setString(1, location);

        try (ResultSet rs = getClientsPreparedStatement.executeQuery()) {
            while (rs.next()) {
                Integer portId = rs.getInt(1);
                cont = rs.getString(3);
                country = rs.getString(4);
                location = rs.getString(5);
                lat = rs.getDouble(6);
                lon = rs.getDouble(7);
                try {
                    port = new Port(cont,country,portId,location,lat,lon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        }

        return port;
    }

    private Ship getShipFromDatabase(DatabaseConnection databaseConnection, String shipName) throws SQLException {
        Integer mmsi;
        String imo;
        String callSign;
        int vesselType ;
        int length;
        int width;
        double draft;
        String cargo;
        Ship ship =null;

        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "Select * From Ship where Name =  ?";

        PreparedStatement getClientsPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getClientsPreparedStatement.setString(1, shipName);

        try (ResultSet rs = getClientsPreparedStatement.executeQuery()) {
            while (rs.next()) {
                mmsi = rs.getInt(1);
                imo = rs.getString(3);
                String vesselName = shipName;
                callSign = rs.getString(6);
                vesselType = rs.getInt(10) ;
                length = rs.getInt(7);
                width = rs.getInt(8);
                draft = 0.0; //replace by access
                cargo = null; //replace by access
                ship = new Ship(mmsi.toString(),vesselName,imo,callSign,vesselType,length,width,draft,cargo);
            }
            // Close the ResultSet                 4
        }

        return ship;
    }


    public String read(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        String print = "";

        while (in.hasNext()){ //Redo
            print=print+in.nextLine();
        }

        return print;

    }

    public String readReport(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        String print = "";

        while (in.hasNext()){ //Redo
            print=print+in.nextLine();
        }

        return print;
    }
}
