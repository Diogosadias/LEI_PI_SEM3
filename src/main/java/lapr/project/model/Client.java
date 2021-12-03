package lapr.project.model;

import lapr.project.data.ClientDatabase;
import lapr.project.data.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private ClientDatabase clientDatabase = new ClientDatabase();

    public String search(DatabaseConnection databaseConnection,String code) {
        if (code == null) return "Code is not Valid!";

        Connection connection = databaseConnection.getConnection();
        Object object = null;
        try {
            connection.setAutoCommit(false);
            if (!clientDatabase.searchPosition(databaseConnection, code, object)) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Container Found!");

        } catch (
                SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PortTree.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        if(object==null) return "Container is not logged on the Database";
        return object.toString() ;
    }

}
