package lapr.project.model;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import java.util.List;
import static lapr.project.model.Import.readLine;


public class ImportTest {
        @Test
    public void ensureImportParametresisNotNull() throws IOException {
        //Arrange
        //Act
        //Assert
        try{
            List<ShipTree> result = readLine(null,null,null,null);
        }catch (IOException ex){
            System.out.println("Input is Invalid!");
        }
    }
}
