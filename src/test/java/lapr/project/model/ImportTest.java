package lapr.project.model;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import java.util.List;
import static lapr.project.model.Import.readLine;
import static org.junit.Assert.assertEquals;


public class ImportTest {
        @Test
    public void ensureImportParametresisNotNull() throws IOException {
            //Arrange
            //Act
            //Assert
            try {
                List<ShipTree> result = readLine(null, null, null, null);
            } catch (IOException ex) {
                System.out.println("Input is Invalid!");
            }
        }

        @Test
        public void testGeneral(){
            Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
            String t = ship.toString();

            assertEquals(t,ship.toString());
            t= ship.print(22.0);

            assertEquals(ship.print(22.0),t);


        }
}
