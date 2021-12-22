package lapr.project.model;

import lapr.project.data.ClientDatabase;
import lapr.project.data.DatabaseConnection;
import oracle.ons.Cli;

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

            object =clientDatabase.searchPosition(databaseConnection, code, object);
            if (object==null) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Container Found!");

        } catch (
                SQLException | NullPointerException ex) {
            Logger.getLogger(Client.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Client.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        if(object==null) return "Container is not logged on the Database";
        return object.toString() ;
    }

    public String search2(DatabaseConnection databaseConnection, String code) {
        if (code == null) return  "10";

        Connection connection = databaseConnection.getConnection();
        Object object = null;
        try {
            connection.setAutoCommit(false);

            object =clientDatabase.searchPosition(databaseConnection, code, object);
            if (object==null) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Container Found!");

        } catch (
                SQLException | NullPointerException ex) {
            Logger.getLogger(Client.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Client.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        if(object instanceof Client) return "11";
        return object.toString() ;
    }
}
