package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static lapr.project.model.ShipMovements.getDate;
import static org.junit.jupiter.api.Assertions.*;

class ShipMovementsTest {


    /***
     * Ensure Time is correctly inputted
     */
    @Test
    void testGetDate() {
        //Arrange
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime expectedResult=LocalDateTime.parse("01/02/2021 10:10", format);
        //Act
        String a ="01/02/2021 10:10";
        LocalDateTime result =getDate(a);
        //Assert
        assertEquals(expectedResult,result);
    }

    /**
     * Ensure get is working
     */
    @Test
    void testGetBaseDateTime(){
        //Arrange
        ShipMovements shipmov = new ShipMovements("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime expectedResult = LocalDateTime.parse("31/12/2020 17:19",format);
        LocalDateTime result =shipmov.getBaseDateTime();
        //Assert
        assertEquals(expectedResult,result);
    }
}