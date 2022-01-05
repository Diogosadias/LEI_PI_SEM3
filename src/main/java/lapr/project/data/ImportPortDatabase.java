package lapr.project.data;


import lapr.project.model.City;
import lapr.project.model.Port;
import lapr.project.model.PortTree;
import oracle.ucp.util.Pair;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
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
        String sqlCommand =
                "insert into Port(port_id, continent, country, location, latitude, longitude) values (?, ?, ?, ?, ?, ?)";

        executePortStatementOnDatabase(databaseConnection, port,
                sqlCommand);
    }

    private void updatePortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {

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
        // Get the result table from the query
        try (ResultSet rs = getPorts.executeQuery()) {
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
        }

        return portTree;
    }

    public boolean getCities(DatabaseConnection databaseConnection, List<City> listCity) {
        boolean returnValue = false;

        try {
            listCity = getCitiesFromDatabase(databaseConnection,listCity);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(ImportPortDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private List<City> getCitiesFromDatabase(DatabaseConnection databaseConnection, List<City> listCity) throws SQLException {
        String sqlCommand = "select * from Country";
        return executeCitiesStatement(databaseConnection,listCity,sqlCommand);
    }

    private List<City> executeCitiesStatement(DatabaseConnection databaseConnection, List<City> listCity, String sqlCommand) throws SQLException {
        String cont;
        String country;
        String name;
        Double lat;
        Double lon;
        Connection connection = databaseConnection.getConnection();

        PreparedStatement getCities = connection.prepareStatement(sqlCommand);
        // Get the result table from the query
        try (ResultSet rs = getCities.executeQuery()) {
            // Get the result table from the query
            while (rs.next()) {
                name = rs.getString(6);
                cont = rs.getString(4);
                country = rs.getString(1);
                lat = rs.getDouble(7);
                lon = rs.getDouble(8);
                City city = new City(name,country,cont, new Point2D.Double(lat,lon));
                listCity.add(city);
            }
        }

        return listCity;
    }

    public boolean getBorders(DatabaseConnection databaseConnection, TreeMap<String, List<String>> borders) {
        boolean returnValue = false;

        try {
            borders = getBordersfromDatabase(databaseConnection,borders);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(ImportPortDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private TreeMap<String, List<String>> getBordersfromDatabase(DatabaseConnection databaseConnection, TreeMap<String, List<String>> borders) throws SQLException {
        String sqlCommand = "select * from Border"; //Remake statement
        return executeBordersStatement(databaseConnection,borders,sqlCommand);
    }

    private TreeMap<String, List<String>> executeBordersStatement(DatabaseConnection databaseConnection, TreeMap<String, List<String>> borders, String sqlCommand) throws SQLException {
        String country1;
        String country2;
        Connection connection = databaseConnection.getConnection();

        PreparedStatement getBorders = connection.prepareStatement(sqlCommand);
        // Get the result table from the query
        try (ResultSet rs = getBorders.executeQuery()) {
            // Get the result table from the query
            while (rs.next()) {
                country1 = rs.getString(1);
                country2 = rs.getString(2);
                List<String> list = new ArrayList<>();
                if(borders.containsKey(country1)) {
                    list = borders.get(country1);
                }
                list.add(country2);
                borders.put(country1, list);
            }
        }

        return borders;
    }

    public boolean getSeaDist(DatabaseConnection databaseConnection, TreeMap<String, List<Pair<String, Double>>> seadist) {
        boolean returnValue = false;

        try {
            seadist = getSeaDistFromDatabase(databaseConnection,seadist);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(ImportPortDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private TreeMap<String, List<Pair<String, Double>>> getSeaDistFromDatabase(DatabaseConnection databaseConnection, TreeMap<String, List<Pair<String, Double>>> seadist) throws SQLException {
        String sqlCommand = "select from_Port_name,to_Port_name,sea_distance from Sea_distance"; //?
        return executeSeaDistStatement(databaseConnection,seadist,sqlCommand);
    }

    private TreeMap<String, List<Pair<String, Double>>> executeSeaDistStatement(DatabaseConnection databaseConnection, TreeMap<String, List<Pair<String, Double>>> seadist, String sqlCommand) throws SQLException {
        String port1;
        String port2;
        Double dist;
        Connection connection = databaseConnection.getConnection();

        PreparedStatement getDist = connection.prepareStatement(sqlCommand);
        // Get the result table from the query
        try (ResultSet rs = getDist.executeQuery()) {
            // Get the result table from the query
            while (rs.next()) {
                port1 = rs.getString(1);
                port2 = rs.getString(2);
                dist = rs.getDouble(3);
                List<Pair<String, Double>> list = new ArrayList<>();
                if(seadist.containsKey(port1)) {
                    list = seadist.get(port1);
                }
                list.add(new Pair<>(port2,dist));
                seadist.put(port1, list);
            }
        }

        return seadist;
    }
}
