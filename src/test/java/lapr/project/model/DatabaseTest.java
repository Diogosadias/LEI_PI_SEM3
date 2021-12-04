package lapr.project.model;

import lapr.project.data.ClientDatabase;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DatabaseTest {


    /***
     * Client wants to Know Situation of Container
     */
    @Test
    public void testContainer(){

    }

    /***
     * Test to Import
     */
    @Test
    public void testImportMockito()  {

        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);

        Connection connection = mock(Connection.class);

        try {
            connection.setAutoCommit(false);

            ImportPortDatabase importPortDatabase = new ImportPortDatabase();
            List<Port> list= new ArrayList<>();


            for (Port port : list) {
                when(importPortDatabase.save(databaseConnection, port)).thenReturn(
                        true);
                boolean result = importPortDatabase.save(databaseConnection, port);
                assertTrue(result);

                Logger.getLogger(DatabaseTest.class.getName())
                        .log(Level.INFO, "Port Added!");

            }
        }catch(SQLException ex){
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);

        }
    }

    /***
     * Test Closest Port
     */
    @Test
    public void testClosestPort(){

        PortTree portTree =mock(PortTree.class);
        ImportPortDatabase importPortDatabase = mock(ImportPortDatabase.class);

        Connection connection = mock(Connection.class);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);


        try {
            connection.setAutoCommit(false);

            when(importPortDatabase.getPortData(databaseConnection, portTree)).thenReturn(
                    true);
            boolean result =importPortDatabase.getPortData(databaseConnection, portTree);
            assertTrue(result);

            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.INFO, "Ports Retrieved From Database!");


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

    }

    /**
     * Client Mockito
     */
    @Test
    public void clientMockito() {
        String code = null;
        ClientDatabase clientDatabase = mock(ClientDatabase.class);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);

        Connection connection = mock(Connection.class);
        Object object = null;
        try {
            connection.setAutoCommit(false);


            when(clientDatabase.searchPosition(databaseConnection, code, object)).thenReturn(
                    true);
            boolean result = clientDatabase.searchPosition(databaseConnection, code, object);
            assertTrue(result);

            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.INFO, "Container Found!");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);

        }
    }


}
