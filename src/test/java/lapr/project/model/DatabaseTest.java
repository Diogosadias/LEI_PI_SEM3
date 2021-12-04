package lapr.project.model;

import lapr.project.controller.ClientController;
import lapr.project.controller.PortManagerController;
import lapr.project.data.ClientDatabase;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    public void testImportMockito() throws IOException {
        PortManager portManager = new PortManager();
        assertNotNull(portManager);

        PortManagerController portManagerController = new PortManagerController();
        assertNotNull(portManagerController);
        assertNotNull(portManagerController.getPortManager());
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);


        Scanner in = new Scanner(new File("src/test/resources/PortsNotSuccess"));
        Scanner ou = new Scanner(portManagerController.importPort(null));
        assertEquals(in.nextLine(), ou.nextLine());


        Connection connection = mock(Connection.class);



        ImportPortDatabase importPortDatabase = mock(ImportPortDatabase.class);

        Object object = null;
        try {
            connection.setAutoCommit(false);


            when(importPortDatabase.save(databaseConnection, object)).thenReturn(
                    true);
            assertTrue(importPortDatabase.save(databaseConnection, object));


        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        try {
            connection.setAutoCommit(false);
            Port port = new Port("Europe","United Kingdom",29002,"Liverpool",53.46666667,-3.033333333);


            when(importPortDatabase.save(databaseConnection, port)).thenReturn(
                    true);
            boolean result = importPortDatabase.save(databaseConnection, port);
            assertTrue(result);
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.INFO, "Port Added!");

        } catch (
                SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }


        try {
            PortTree portTree = new PortTree();

            portManagerController.importToDatabase(databaseConnection, portTree);
        }catch (NullPointerException | SQLException ex){
            Logger.getLogger(DatabaseTest.class.getName())
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
    public void clientMockito() throws IOException {
        Client client = new Client();
        assertNotNull(client);

        ClientController clientController = new ClientController();
        assertNotNull(clientController);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);


        Scanner in = new Scanner(new File("src/test/resources/Client1"));
        Scanner ou = new Scanner(clientController.searchPosition(databaseConnection, null));
        assertEquals(in.nextLine(), ou.nextLine());

        Connection connection = mock(Connection.class);


        String code = null;
        ClientDatabase clientDatabase = mock(ClientDatabase.class);

        Object object = null;
        try {
            connection.setAutoCommit(false);


            when(clientDatabase.searchPosition(databaseConnection, "12345", object)).thenReturn(
                    true);
            assertTrue(clientDatabase.searchPosition(databaseConnection, "12345", object));


        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);

        }
        try {
            connection.setAutoCommit(false);

            when(clientDatabase.searchPosition(databaseConnection, "12345", object)).thenReturn(
                    true);
            boolean result = clientDatabase.searchPosition(databaseConnection, "12345", object);
            assertTrue(result);
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.INFO, "Container Found!");

        } catch (
                SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        try {
            Ship ship = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
            when(client.search(databaseConnection, "12345")).thenReturn(ship.toString());
            assertEquals(client.search(databaseConnection, "12345"), ship.toString());
        }catch (NullPointerException ex){
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }


}
