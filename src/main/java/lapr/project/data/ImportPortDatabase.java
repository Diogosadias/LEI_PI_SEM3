package lapr.project.data;

import lapr.project.model.Port;
import lapr.project.model.PortTree;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportPortDatabase {

    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Port port = (Port) object;
        boolean returnValue = false;

        try {
            savePortToDatabase(databaseConnection, port);

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

    private void savePortToDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        if (isPortOnDatabase(databaseConnection, port)) {
            updatePortOnDatabase(databaseConnection, port);
        } else {
            insertPortOnDatabase(databaseConnection, port);
        }
    }

    private void insertPortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "insert into Port(port_id, continent, country, location, latitude, longitude) values (?, ?, ?, ?, ?, ?)";

        executePortStatementOnDatabase(databaseConnection, port,
                sqlCommand);
    }

    private void updatePortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {

        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "update Port set port_id=?, continent=?, country=?, location=?, latitude=?, longitude=?";

        executePortStatementOnDatabase(databaseConnection, port,
                sqlCommand);
    }

    private boolean isPortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        boolean isPortOnDatabase = false;

        String sqlCommand = "select * from Port where port_id = ?";

        PreparedStatement getClientsPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getClientsPreparedStatement.setInt(1, port.getCode());

        try (ResultSet clientsResultSet = getClientsPreparedStatement.executeQuery()) {

            if (clientsResultSet.next()) {
                // if client already exists in the database
                isPortOnDatabase = true;
            } else {

                // if client does not exist in the database
                isPortOnDatabase = false;
            }
        } catch (SQLException ex){
            isPortOnDatabase = false;
        }
        return isPortOnDatabase;
    }

    private void executePortStatementOnDatabase(
            DatabaseConnection databaseConnection,
            Port port, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        PreparedStatement savePortPreparedStatement =
                connection.prepareStatement(
                        sqlCommand);
        savePortPreparedStatement.setInt(1,port.getCode());
        savePortPreparedStatement.setString(2,port.getCont());
        savePortPreparedStatement.setString(3,port.getCountry());
        savePortPreparedStatement.setString(4,port.getLocation());
        savePortPreparedStatement.setFloat(5, (float) port.getCoords().x);
        savePortPreparedStatement.setFloat(6, (float) port.getCoords().y);
        savePortPreparedStatement.executeUpdate();
    }


    public boolean getPortData(DatabaseConnection databaseConnection, PortTree portTree) {
        boolean returnValue = false;

        try {
            portTree = getPortsFromDataBase(databaseConnection,portTree);

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

    private PortTree getPortsFromDataBase(DatabaseConnection databaseConnection,PortTree portTree) throws SQLException {
        String sqlCommand = "select * from Port";
        return executePortTreeStatement(databaseConnection,portTree,sqlCommand);
    }

    private PortTree executePortTreeStatement(DatabaseConnection databaseConnection, PortTree portTree, String sqlCommand) throws SQLException {
        Integer code;
        String cont;
        String country;
        String location;
        Double lat;
        Double lon;
        Connection connection = databaseConnection.getConnection();

        PreparedStatement getPorts = connection.prepareStatement(sqlCommand);
        ResultSet rs = getPorts.executeQuery();

        // Get the result table from the query
        while (rs.next()) {
            code = rs.getInt(1);
            cont = rs.getString(3);
            country = rs.getString(4);
            location = rs.getString(5);
            lat = rs.getDouble(6);
            lon = rs.getDouble(7);
            Port port =null;
            try {
                port = new Port(cont,country,code,location,lat,lon);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                portTree.insert(port, new Point2D.Double(lat,lon));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        rs.close();                       // Close the ResultSet                 4

        return portTree;
    }
}
