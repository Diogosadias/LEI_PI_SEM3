package lapr.project.model;

import lapr.project.controller.TrafficManagerController;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DataBaseImportTest {

    /**
     * Tests File Creation
     */
    @Test
    public void testFileCreation() throws IOException {
        TrafficManagerController trafficManagerController = new TrafficManagerController();
        DataBaseImport dataBaseImport = mock(DataBaseImport.class);
        Scanner in = new Scanner(new File("src/test/resources/FreigthNotSuccess"));
        Scanner ou = new Scanner(trafficManagerController.buildFreight(null, 1));
        assertEquals(in.nextLine(),ou.nextLine());

        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);

        in = new Scanner(new File("src/test/resources/FreigthSuccess"));
        when(dataBaseImport.buildFreight(databaseConnection,1)).thenReturn("Graph was built!");
        ou = new Scanner(dataBaseImport.buildFreight(databaseConnection,1));
        assertEquals(in.nextLine(),ou.nextLine());
        /*

        dataBaseImport.importPortDatabase = mock(ImportPortDatabase.class);
        PortTree portTree = mock(PortTree.class);

        String ine = "Ports Couldn't be Retrieved";
        when(dataBaseImport.importPortDatabase.getPortData(databaseConnection,portTree)).thenReturn(false);
        ou = new Scanner(dataBaseImport.buildFreight(databaseConnection,1));
        assertEquals(ine,ou.nextLine());

         */

        //Tests for multiple acess to database
    }

    /**
     * Test Errors in the database
     */
    @Test
    public void testErrorsDatabase() throws IOException {
        DataBaseImport dataBaseImport = mock(DataBaseImport.class);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);


        /*
        ImportPortDatabase importPortDatabase = dataBaseImport.importPortDatabase;
        PortTree portTree = mock(PortTree.class);
        when(importPortDatabase.getPortData(databaseConnection,portTree)).thenReturn(false);
        String  result = dataBaseImport.buildFreight(databaseConnection,0);
        assertEquals(result,"Ports Couldn't be Retrieved");
        */


    }
}
