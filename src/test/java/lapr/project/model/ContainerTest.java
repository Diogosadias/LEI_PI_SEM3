package lapr.project.model;

import lapr.project.controller.ClientController;
import lapr.project.data.ClientDatabase;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipDatabase;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContainerTest {

    /**
     * Test Constructor
     */
    @Test
    public void contructorTest(){
        Container c = new Container(1,1.1,1,1,1,1.1);
        String t = "Container - "+ 1 +
                "\n\t" + 1.1 + "kg - of PayLoad" +
                "\n\t(" + 1 + "," + 1 + "," +1 +") - Position" +
                "\n\t" + 1.1 + " ºC - of Temperature"  ;

        assertEquals(t,c.toString());

        c = new Container(1,1.1,1.1);
        t = "Container - "+ 1 +
                "\n\t" + 1.1 + "kg - of PayLoad" +
                "\n\t" + 1.1 + " ºC - of Temperature"  ;

        assertEquals(t,c.toStringwPosition());
    }

    /**
     * Test Offload
     */
    @Test
    public void testOffload(){
        ShipCaptain shipCaptain = new ShipCaptain(1);
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        assertEquals("Ship Captain Id was not Found!",shipCaptain.offload(databaseConnection,null));


        Integer t = shipCaptain.getId();
        assertTrue(t==1);

        databaseConnection = mock(DatabaseConnection.class);
        assertEquals("Cargo Manifest was not Found!",shipCaptain.occupancyRateManifest(databaseConnection,null,null));


        databaseConnection = mock(DatabaseConnection.class);
        assertEquals("Cargo Manifest was not Found!",shipCaptain.occupancyRateTime(databaseConnection,null,null));

    }

    /**
     * Tests to US312
     */
    @Test
    public void testClientSprint3() throws IOException {
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        ClientController clientController=new ClientController();
        Connection connection = mock(Connection.class);


        String in ="10";
        Scanner ou = new Scanner(clientController.searchPosition2(databaseConnection,null));
        assertEquals(in,ou.nextLine());

    }
}
