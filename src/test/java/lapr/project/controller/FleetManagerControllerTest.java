package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.FleetManagerDatabase;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FleetManagerControllerTest {

    FleetMAnagerController fleetMAnagerController = new FleetMAnagerController();

    @Test
    public void testFleet() throws IOException {
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);



        Scanner scanner = new Scanner(fleetMAnagerController.ocupacionPeriod(null,"0","0",databaseConnection));
        String in = scanner.nextLine();
        String  result = "Ship Id is not Valid!";
        assertEquals(result,in);



    }
}
