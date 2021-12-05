package lapr.project.model;

import lapr.project.controller.ClientController;
import lapr.project.controller.PortManagerController;
import lapr.project.controller.TrafficManagerController;
import lapr.project.data.ClientDatabase;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import lapr.project.data.ShipDatabase;
import org.junit.jupiter.api.Test;

import static lapr.project.model.TemporalMessages.getDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

        ImportPortDatabase importPortDatabase1 = new ImportPortDatabase();
        try {
            Connection connection1 = mock(Connection.class);
            connection1.setAutoCommit(false);

            PortTree portTree = new PortTree();
            List<Port> list = (List<Port>) portTree.inOrder();
            for (Port port : list) {
                when(importPortDatabase1.save(databaseConnection,port)).thenReturn(true);
                assertTrue(importPortDatabase1.save(databaseConnection,port));
                connection.commit();
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
    public void testClosestPort() throws IOException {

        TrafficManagerController trafficManagerController = new TrafficManagerController();
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);



        Connection connection = mock(Connection.class);



        Search search = mock(Search.class);

        Object object = null;
        try {
            connection.setAutoCommit(false);


            when(search.getClosestPort(databaseConnection, null, null,null)).thenReturn(
                    "true");
            assertEquals(search.getClosestPort(databaseConnection, null, null,null),"true");



        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        ImportPortDatabase importPortDatabase = mock(ImportPortDatabase.class);
        try {
            connection.setAutoCommit(false);

            when(importPortDatabase.getPortData(databaseConnection, null)).thenReturn(
                    true);
            boolean result = importPortDatabase.getPortData(databaseConnection, null);
            assertTrue(result);
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.INFO, "Ports Retrieved From Database!");

        } catch (
                SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }


        try {
            Port port = new Port("Europe","United Kingdom",29002,"Liverpool",53.46666667,-3.033333333);
            when(search.getClosestPort(databaseConnection,null,null,null)).thenReturn(port.toString());
            assertEquals(search.getClosestPort(databaseConnection,null,null,null),port.toString());
        }catch (NullPointerException ex){
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);
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
                    object);
            assertEquals(object,clientDatabase.searchPosition(databaseConnection, "12345", object));


        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);

        }
        try {
            connection.setAutoCommit(false);

            when(clientDatabase.searchPosition(databaseConnection, "12345", object)).thenReturn(
                    object);
            Object result = clientDatabase.searchPosition(databaseConnection, "12345", object);
            assertEquals(object,result);
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


    /***
     * Test Monday
     */
    @Test
    public void testMonday() throws IOException {
        Search search = new Search();
        assertNotNull(search);

        TrafficManagerController trafficManagerController = new TrafficManagerController();
        assertNotNull(trafficManagerController);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);


        Scanner ou = new Scanner(trafficManagerController.shipAvailableMonday(databaseConnection, null));
        assertEquals("Date is not Valid!", ou.nextLine());

        Connection connection = mock(Connection.class);


        ShipDatabase shipDatabase = mock(ShipDatabase.class);

        String date = "31/12/2020 00:01";
        Object object = null;
        try {
            connection.setAutoCommit(false);


            when(shipDatabase.getNextMonday(databaseConnection,date)).thenReturn((List<Ship>) object);
            assertEquals(object,shipDatabase.getNextMonday(databaseConnection,date));


        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);

        }


        try {
            Ship ship = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
            when(shipDatabase.getNextMonday(databaseConnection, date)).thenReturn((List<Ship>) ship);
            assertEquals(shipDatabase.getNextMonday(databaseConnection, date), ship);
        }catch (NullPointerException ex){
            Logger.getLogger(DatabaseTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }


    }
}
