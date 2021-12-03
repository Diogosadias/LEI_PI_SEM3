package lapr.project.data;

import lapr.project.model.Port;
import lapr.project.model.PortTree;
import lapr.project.model.Ship;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDatabase {
    public boolean searchPosition(DatabaseConnection databaseConnection, String code, Object object) {
        boolean returnValue = false;

        try {
            object = getPosition(databaseConnection, code);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
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

        String sqlCommand = "Select * From container where container_id =  ?";

        PreparedStatement getClientsPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getClientsPreparedStatement.setInt(1, Integer.parseInt(containercode));

        ResultSet rs = getClientsPreparedStatement.executeQuery();

        while (rs.next()) {
            Integer testShip = rs.getInt(5);
            if(testShip!=null){
                Ship s = getShipFromDatabase(databaseConnection,testShip);
                object = s;
            } else{
                Port p = getPortFromDatabase(databaseConnection,rs.getInt(6));
                object = p;
            }
        }
        rs.close();                       // Close the ResultSet                 4

        return object;
    }

    private Port getPortFromDatabase(DatabaseConnection databaseConnection, Integer port_id) throws SQLException {
        String cont;
        String country;
        String location;
        Double lat;
        Double lon;
        Port port =null;

        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "Select * From Port where port_id =  ?";

        PreparedStatement getClientsPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getClientsPreparedStatement.setInt(1, port_id);

        ResultSet rs = getClientsPreparedStatement.executeQuery();
        while (rs.next()) {
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
        rs.close();                       // Close the ResultSet                 4

        return port;
    }

    private Ship getShipFromDatabase(DatabaseConnection databaseConnection, Integer shipscode) throws SQLException {
        Integer mmsi;
        String vesselName;
        String callSign;
        int vesselType ;
        int length;
        int width;
        double draft;
        String cargo;
        Ship ship =null;

        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "Select * From Ship where IMO_NUMBER =  ?";

        PreparedStatement getClientsPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getClientsPreparedStatement.setString(1, shipscode.toString());

        ResultSet rs = getClientsPreparedStatement.executeQuery();
        while (rs.next()) {
            mmsi = rs.getInt(1);
            vesselName = rs.getString(2);
            callSign = rs.getString(6);
            vesselType = rs.getInt(10) ;
            length = rs.getInt(7);
            width = rs.getInt(8);
            draft = Double.parseDouble(null); //replace by access
            cargo = null; //replace by access
            ship = new Ship(mmsi.toString(),vesselName,shipscode.toString(),callSign,vesselType,length,width,draft,cargo);

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
