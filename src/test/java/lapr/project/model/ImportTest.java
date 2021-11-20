package lapr.project.model;

import lapr.project.controller.MainController;
import org.junit.Test;

import java.io.IOException;

import java.util.List;
import static lapr.project.model.Import.readLine;
import lapr.project.controller.MainController.*;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImportTest {

    @Test
    public void testReadLine() {
        assertThrows(IOException.class, () -> readLine("", null, new IMOTree(), new CallSignTree()), "Expected readLine to throw IOException but it didn't");
        assertThrows(IOException.class, () -> readLine("", new MMSTree(), null, new CallSignTree()), "Expected readLine to throw IOException but it didn't");
        assertThrows(IOException.class, () -> readLine("", new MMSTree(), new IMOTree(), null), "Expected readLine to throw IOException but it didn't");
    }

    @Test
    public void ensureImportParametresisNotNull() {
        //Arrange
        //Act
        //Assert

        Exception exception = assertThrows(IOException.class, () -> {
            List<ShipTree> result = readLine(null, null, null, null);
        });

        String expectedMessage = "Input";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGeneral() throws IOException {
        Ship ship = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        String t = ship.toString();
        assertEquals(t, ship.toString());
        t = ship.print(22.0);

        assertEquals(ship.print(22.0), t);

        assertFalse(ship.checkproximity(null));
        assertFalse(ship.checkproximity(ship));

        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "NA", "B");
        ship.getMovements().insert(shipmov);
        assertEquals(ship.getArrival(), ship.getdeparture());
        assertTrue(ship.distPorts(ship.getArrival(), ship.getdeparture()));
    }
//gmaer
}
