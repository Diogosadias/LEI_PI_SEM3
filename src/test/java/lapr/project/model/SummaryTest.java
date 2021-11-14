package lapr.project.model;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummaryTest {
    /***
     * Ensure Travel distance is correct
     */
    @Test
    void testMMSI() throws IOException {
        //Arrange
        Ship s = new Ship(211331640, "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294,  32,  13.6, "NA");
        //Act
        int expectedResult = 211331640;
        //Assert
        assertEquals(expectedResult,s.getMMSI());

        //Act
        expectedResult = 211331640;
        s.setMMSI(expectedResult);
        //Assert
        assertEquals(expectedResult,s.getMMSI());
    }
        @Test
    void testVesselName() throws IOException {
        //Arrange
        Ship s = new Ship(211331640, "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294,  32,  13.6, "NA");
        //Act
        String expectedResult = "SEOUL EXPRESS";
        //Assert
        assertEquals(expectedResult,s.getVesselName());

        //Act
        expectedResult = "SEOUL EXPRESS";
        s.setVesselName(expectedResult);
        //Assert
        assertEquals(expectedResult,s.getVesselName());
    }
        @Test
    void testIMO() throws IOException {
        //Arrange
        Ship s = new Ship(211331640, "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294,  32,  13.6, "NA");
        //Act
        String expectedResult = "IMO9193305";
        //Assert
        assertEquals(expectedResult,s.getIMO());

        //Act
        expectedResult = "IMO9193305";
        s.setIMO(expectedResult);
        //Assert
        assertEquals(expectedResult,s.getIMO());
    }
        @Test
    void testCallSign() throws IOException {
        //Arrange
        Ship s = new Ship(211331640, "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294,  32,  13.6, "NA");
        //Act
        String expectedResult = "DHBN";
        //Assert
        assertEquals(expectedResult,s.getCallSign());

        //Act
        expectedResult = "DHBN";
        s.setCallSign(expectedResult);
        //Assert
        assertEquals(expectedResult,s.getCallSign());
    }
        @Test
    void testVesselType() throws IOException {
        //Arrange
        Ship s = new Ship(211331640, "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294,  32,  13.6, "NA");
        //Act
        int expectedResult = 70;
        //Assert
        assertEquals(expectedResult,s.getVesselType());

        //Act
        expectedResult = 70;
        s.setVesselType(expectedResult);
        //Assert
        assertEquals(expectedResult,s.getVesselType());
    }
        @Test
    void tesLength() throws IOException {
        //Arrange
        Ship s = new Ship(211331640, "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294,  32,  13.6, "NA");
        //Act
        int expectedResult = 294;
        //Assert
        assertEquals(expectedResult,s.getLength());

        //Act
        expectedResult = 294;
        s.setLength(expectedResult);
        //Assert
        assertEquals(expectedResult,s.getLength());
    }
        @Test
    void testWidth() throws IOException {
        //Arrange
        Ship s = new Ship(211331640, "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294,  32,  13.6, "NA");
        //Act
        int expectedResult = 32;
        //Assert
        assertEquals(expectedResult,s.getWidth());

        //Act
        expectedResult = 32;
        s.setWidth(expectedResult);
        //Assert
        assertEquals(expectedResult,s.getWidth());
    }
        @Test
        void testDraft() throws IOException {
        //Arrange
        Ship s = new Ship(211331640, "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294,  32,  13.6, "NA");
        //Act
        double expectedResult = 13.6;
        //Assert
        assertEquals(expectedResult,s.getDraft());

        //Act
        expectedResult = 13.6;
        s.setDraft(expectedResult);
        //Assert
        assertEquals(expectedResult,s.getDraft());
    }
        
         @Test   
         void testCargo() throws IOException {
        //Arrange
        Ship s = new Ship(211331640, "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294,  32,  13.6, "NA");
        //Act
        String expectedResult = "NA";
        //Assert
        assertEquals(expectedResult,s.getCargo());

        //Act
        expectedResult = "NA";
        s.setCargo(expectedResult);
        //Assert
        assertEquals(expectedResult,s.getCargo());
    }
}
