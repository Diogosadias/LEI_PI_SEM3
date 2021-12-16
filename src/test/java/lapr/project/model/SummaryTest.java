package lapr.project.model;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static lapr.project.model.TemporalMessages.getDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummaryTest {

    @Test
    void testMMSI() throws IOException {
        //Arrange
        Ship s = new Ship("211331640", "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294, 32, 13.6, "NA");
        //Act
        String expectedResult = "211331640";
        //Assert
        assertEquals(expectedResult, s.getMMSI());

        //Act
        expectedResult = "211331640";
        s.setMMSI(expectedResult);
        //Assert
        assertEquals(expectedResult, s.getMMSI());
    }

    @Test
    void testVesselName() throws IOException {
        //Arrange
        Ship s = new Ship("211331640", "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294, 32, 13.6, "NA");
        //Act
        String expectedResult = "SEOUL EXPRESS";
        //Assert
        assertEquals(expectedResult, s.getVesselName());

        //Act
        expectedResult = "SEOUL EXPRESS";
        s.setVesselName(expectedResult);
        //Assert
        assertEquals(expectedResult, s.getVesselName());
    }

    @Test
    void testIMO() throws IOException {
        //Arrange
        Ship s = new Ship("211331640", "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294, 32, 13.6, "NA");
        //Act
        String expectedResult = "IMO9193305";
        //Assert
        assertEquals(expectedResult, s.getIMO());

        //Act
        expectedResult = "IMO9193305";
        s.setIMO(expectedResult);
        //Assert
        assertEquals(expectedResult, s.getIMO());
    }

    @Test
    void testCallSign() throws IOException {
        //Arrange
        Ship s = new Ship("211331640", "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294, 32, 13.6, "NA");
        //Act
        String expectedResult = "DHBN";
        //Assert
        assertEquals(expectedResult, s.getCallSign());

        //Act
        expectedResult = "DHBN";
        s.setCallSign(expectedResult);
        //Assert
        assertEquals(expectedResult, s.getCallSign());
    }

    @Test
    void testVesselType() throws IOException {
        //Arrange
        Ship s = new Ship("211331640", "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294, 32, 13.6, "NA");
        //Act
        int expectedResult = 70;
        //Assert
        assertEquals(expectedResult, s.getVesselType());

        //Act
        expectedResult = 70;
        s.setVesselType(expectedResult);
        //Assert
        assertEquals(expectedResult, s.getVesselType());
    }

    @Test
    void tesLength() throws IOException {
        //Arrange
        Ship s = new Ship("211331640", "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294, 32, 13.6, "NA");
        //Act
        int expectedResult = 294;
        //Assert
        assertEquals(expectedResult, s.getLength());

        //Act
        expectedResult = 294;
        s.setLength(expectedResult);
        //Assert
        assertEquals(expectedResult, s.getLength());
    }

    @Test
    void testWidth() throws IOException {
        //Arrange
        Ship s = new Ship("211331640", "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294, 32, 13.6, "NA");
        //Act
        int expectedResult = 32;
        //Assert
        assertEquals(expectedResult, s.getWidth());

        //Act
        expectedResult = 32;
        s.setWidth(expectedResult);
        //Assert
        assertEquals(expectedResult, s.getWidth());
    }

    @Test
    void testDraft() throws IOException {
        //Arrange
        Ship s = new Ship("211331640", "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294, 32, 13.6, "NA");
        //Act
        double expectedResult = 13.6;
        //Assert
        assertEquals(expectedResult, s.getDraft());

        //Act
        expectedResult = 13.6;
        s.setDraft(expectedResult);
        //Assert
        assertEquals(expectedResult, s.getDraft());
    }

    @Test
    void testCargo() throws IOException {
        //Arrange
        Ship s = new Ship("211331640", "SEOUL EXPRESS", "IMO9193305", "DHBN", 70, 294, 32, 13.6, "NA");
        //Act
        String expectedResult = "NA";
        //Assert
        assertEquals(expectedResult, s.getCargo());

        //Act
        expectedResult = "NA";
        s.setCargo(expectedResult);
        //Assert
        assertEquals(expectedResult, s.getCargo());
    }

    @Test
    void ensureMeanSOG() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        double expectedResult = 13.780000000000001;
        double result = ship.getMeanSOG();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureMeanCOG() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        double expectedResult = 16;
        double result = ship.getMeanCOG();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureMaxSOG() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        double expectedResult = 17.2;
        double result = ship.getMaxSOG();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureMaxCOG() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        double expectedResult = 17.4;
        double result = ship.getMaxCOG();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureDepartureLatitude() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        double expectedResult = 42.97875;
        double result = ship.getDepartureLatitude();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureDepartureLongitude() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        double expectedResult = -66.97001;
        double result = ship.getDepartureLongitude();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureArrivalLatitude() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        double expectedResult = 62.81971;
        double result = ship.getArrivalLatitude();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureArrivalLongitude() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        double expectedResult = -68.33132;
        double result = ship.getArrivalLongitude();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureStartBaseDateTime() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        LocalDateTime expectedResult = getDate("31/12/2020 01:09");
        LocalDateTime result = ship.getStartBaseDateTime();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureEndBaseDateTime() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        LocalDateTime expectedResult = getDate("31/12/2020 23:32");
        LocalDateTime result = ship.getEndBaseDateTime();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureTotalNumberOfMovements() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        int expectedResult = 5;
        int result = ship.getTotalNumberOfMovements();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureTotalMovementTime() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        Duration expectedResult = Duration.ofHours(22);
         expectedResult = expectedResult.plusMinutes(23);
        Duration result = ship.getTotalMovementTime();
        //Assert
        assertEquals(expectedResult, result);
    }
        @Test
    void ensureCompareToIMO() throws IOException {
        //Arrange
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        Ship ship1 = new Ship("224871721", "VARAMO", "IMO7982712", "K6U1N", 79, 126, 23, 2.5, "NA");
        
        //Act
        int expectedResult = ship.getIMO().compareTo(ship1.getIMO());
        
        //Assert
         assertEquals(expectedResult,ship.compareToIMO(ship1));
        
    }
    
            @Test
    void ensureCompareToCallSign() throws IOException {
        //Arrange
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        Ship ship1 = new Ship("224871721", "VARAMO", "IMO7982712", "K6U1N", 79, 126, 23, 2.5, "NA");
        
        //Act
        int expectedResult = ship.getCallSign().compareTo(ship1.getCallSign());
        
        //Assert
         assertEquals(expectedResult,ship.compareToCallSign(ship1));
        
    }
        @Test
    void ensureDeltaDistance() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        double expectedResult = 2207.983914073569;
        double result = ship.getDeltaDistance();
        //Assert
        assertEquals(expectedResult, result);
    }
    
            @Test
    void ensureTravelledDistance() throws IOException {
        //Arrange

        //Act
        Ship ship = new Ship("211331640", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 01:09", 42.97875, -66.97001, 12.9, 17.1, 147, "NA", "B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 05:19", 49.37628, -68.98271, 13.2, 12.3, 119, "NA", "B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 19:25", 58.89612, -52.18733, 17.2, 16.9, 121, "NA", "B");
        TemporalMessages shipmov4 = new TemporalMessages("31/12/2020 20:49", 53.98716, -53.99174, 10.1, 17.4, 142, "NA", "B");
        TemporalMessages shipmov5 = new TemporalMessages("31/12/2020 23:32", 62.81971, -68.33132, 15.5, 16.3, 147, "NA", "B");
        movs.insert(shipmov1);
        movs.insert(shipmov2);
        movs.insert(shipmov3);
        movs.insert(shipmov4);
        movs.insert(shipmov5);
        ship.setMovements(movs);
        DecimalFormat df = new DecimalFormat("00.##########");
        double expectedResult = 4082.9150550812;
        double result = ship.getTravelledDistance();
        //Assert
        assertEquals(df.format(expectedResult), df.format(result));
    }
}
