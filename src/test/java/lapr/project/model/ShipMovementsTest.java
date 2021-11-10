package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static lapr.project.model.ShipMovements.getDate;
import static org.junit.jupiter.api.Assertions.*;

class ShipMovementsTest {

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
}