package lapr.project.model;

import lapr.project.controller.PortManagerController;
import lapr.project.controller.TrafficManagerController;
import lapr.project.data.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataBaseImportTest {

    /**
     * Tests File Creation
     */
    @Test
    public void testFileCreation() throws IOException {
        TrafficManagerController trafficManagerController = new TrafficManagerController();
        Scanner in = new Scanner(new File("src/test/resources/FreigthNotSuccess"));
        Scanner ou = new Scanner(trafficManagerController.buildFreight(null));
        assertEquals(in.nextLine(),ou.nextLine());

        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);

        in = new Scanner(new File("src/test/resources/FreigthSuccess"));
        ou = new Scanner(trafficManagerController.buildFreight(databaseConnection));
        assertEquals(in.nextLine(),ou.nextLine());
    }
}
