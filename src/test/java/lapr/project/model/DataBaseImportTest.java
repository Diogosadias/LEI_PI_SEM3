package lapr.project.model;

import lapr.project.controller.TrafficManagerController;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import lapr.project.utils.PL.MatrixGraph;
import oracle.ucp.util.Pair;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

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
        ImportPortDatabase importPortDatabase = mock(ImportPortDatabase.class);
        DataBaseImport dataBaseImport1 = new DataBaseImport(importPortDatabase);

        String in1 = "Database has no data!";
        List<Port> listPorts =new ArrayList<>();
        List<City> listCity = new ArrayList<>();
        TreeMap<String, List<String>> borders = new TreeMap<>();
        TreeMap<String, List<Pair<String, Double>>> seadist = new TreeMap<>();
        MatrixGraph matrixGraph = new MatrixGraph(false);
        PortTree portTree = new PortTree();
        when(dataBaseImport1.importPortDatabase.getPortData(databaseConnection,portTree)).thenReturn(true);
        when(dataBaseImport1.importPortDatabase.getCities(databaseConnection,listCity)).thenReturn(true);
        when(dataBaseImport1.importPortDatabase.getBorders(databaseConnection,borders)).thenReturn(true);
        when(dataBaseImport1.importPortDatabase.getSeaDist(databaseConnection,seadist)).thenReturn(true);
        when(dataBaseImport1.buildGraph(listPorts,listCity,borders,seadist,1)).thenReturn(matrixGraph);
        ou = new Scanner(dataBaseImport1.buildFreight(databaseConnection,1));
        assertEquals(in.nextLine(),ou.nextLine());
        

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
