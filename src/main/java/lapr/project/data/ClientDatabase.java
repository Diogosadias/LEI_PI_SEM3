package lapr.project.data;

import lapr.project.model.Port;
import lapr.project.model.PortTree;
import lapr.project.model.Ship;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDatabase {
    public Object searchPosition(DatabaseConnection databaseConnection, String code, Object object) {

        try {
            object = getPosition(databaseConnection, code);


        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            object = null;
        }
        return object;
    }

    private Object getPosition(DatabaseConnection databaseConnection, String code) throws SQLException {
        if (isContainerOnDatabase(databaseConnection, code)) {
            return getContainerPos(databaseConnection, code);
        } else {
            return null;
        }
    }

    private Object getContainerPos(DatabaseConnection databaseConnection, String code) throws SQLException {
        return executeClientStatment(databaseConnection,code);
    }

    private Object executeClientStatment(DatabaseConnection databaseConnection, String containercode) throws SQLException {
        Object object =null;
        Connection connection = databaseConnection.getConnection();

        CallableStatement cstmt = connection.prepareCall("{? = call currentSituationOfAContainer(?)}"); //Redo this call
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.setInt(2, Integer.parseInt(containercode));
        cstmt.executeUpdate();
        ResultSet rs = (ResultSet) cstmt.getRef(1);
        if(rs==null) return object;

        while (rs.next()) {
            String  testShip = rs.getString(1).substring(0,4);
            if(testShip=="SHIP"){
                Ship s = getShipFromDatabase(databaseConnection,rs.getString(1).substring(4));
                object = s;
            } else{
                Port p = getPortFromDatabase(databaseConnection,rs.getString(1).substring(4));
                object = p;
            }
        }
        rs.close();                       // Close the ResultSet                 4

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

        ResultSet rs = getClientsPreparedStatement.executeQuery();
        while (rs.next()) {
            Integer port_id = rs.getInt(1);
            cont = rs.getString(3);
            country = rs.getString(4);
            location = rs.getString(5);
            lat = rs.getDouble(6);
            lon = rs.getDouble(7);
            try {
                port = new Port(cont,country,port_id,location,lat,lon);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        rs.close();

        return port;
    }

    private Ship getShipFromDatabase(DatabaseConnection databaseConnection, String shipName) throws SQLException {
        Integer mmsi;
        String IMO;
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

        ResultSet rs = getClientsPreparedStatement.executeQuery();
        while (rs.next()) {
            mmsi = rs.getInt(1);
            IMO = rs.getString(3);
            String vesselName = shipName;
            callSign = rs.getString(6);
            vesselType = rs.getInt(10) ;
            length = rs.getInt(7);
            width = rs.getInt(8);
            draft = Double.parseDouble(null); //replace by access
            cargo = null; //replace by access
            ship = new Ship(mmsi.toString(),vesselName,IMO,callSign,vesselType,length,width,draft,cargo);

        }
        rs.close();                       // Close the ResultSet                 4

        return ship;
    }

    private boolean isContainerOnDatabase(DatabaseConnection databaseConnection, String code) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isContainer = false;

        String sqlCommand = "Select * From container where container_id =  ?";

        PreparedStatement getClientsPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getClientsPreparedStatement.setInt(1, Integer.parseInt(code));

        try (ResultSet clientsResultSet = getClientsPreparedStatement.executeQuery()) {

            if (clientsResultSet.next()) {
                // if client already exists in the database
                isContainer = true;
            } else {

                // if client does not exist in the database
                isContainer = false;
            }
        } catch (SQLException ex){
            isContainer = false;
        }
        return isContainer;
    }
}
