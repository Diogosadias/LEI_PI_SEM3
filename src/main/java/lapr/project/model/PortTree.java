package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.utils.PL.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class

PortTree <E extends Comparable<E>> extends KDTree<Port> {

    public void insert(Port port) throws IOException {
        insert(port,port.getCoords());
    }

    protected DoubleNode<? extends Object> find(DoubleNode<Port> node, E element) {
        if (node ==null) return null;
        if (node.getInfo().compareTo((Port) element)>0) return find(node.getLeft(),element) ;
        if (node.getInfo().compareTo((Port) element)<0) return find(node.getRight(),element) ;
        return node;
    }

    public boolean find(E property) {
        return find(root,property)!=null;
    }

    private void executePortStatementOnDatabase(
            DatabaseConnection databaseConnection,
            Port port, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        PreparedStatement savePortPreparedStatement =
                connection.prepareStatement(
                        sqlCommand);
        savePortPreparedStatement.setInt(1,port.getCode());
        savePortPreparedStatement.setString(3,port.getCont());
        savePortPreparedStatement.setString(4,port.getCountry());
        savePortPreparedStatement.setString(5,port.getLocation());
        savePortPreparedStatement.setFloat(6, (float) port.getCoords().x);
        savePortPreparedStatement.setFloat(7, (float) port.getCoords().y);
        savePortPreparedStatement.executeUpdate();
    }

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


}
