package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static lapr.project.model.TemporalMessages.getDate;
import static org.junit.jupiter.api.Assertions.*;

class TemporalMessagesTest {


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
     * Ensure BaseDateTime is Working
     */
    @Test
    void testBaseDateTime() throws IOException {
        //Arrange
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime expectedResult = LocalDateTime.parse("31/12/2020 17:19",format);
        LocalDateTime result =shipmov.getBaseDateTime();
        //Assert
        assertEquals(expectedResult,result);
        assertEquals(expectedResult,shipmov.getBaseDateTime());

        //Act
        expectedResult = LocalDateTime.parse("01/01/2021 17:19",format);
        shipmov.setBaseDateTime(expectedResult);
        //Assert
        assertEquals(expectedResult,shipmov.getBaseDateTime());
    }

    /**
     * Ensure latitude is Working
     */
    @Test
    void testLatitude() throws IOException {
        //Arrange
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        double expectedResult = 42.97875;
        //Assert
        assertEquals(expectedResult,shipmov.getLatitude());

        //Act
        expectedResult = 42.77875;
        shipmov.setLatitude(expectedResult);
        //Assert
        assertEquals(expectedResult,shipmov.getLatitude());
    }

    /**
     * Ensure longitude is Working
     */
    @Test
    void testLongitude() throws IOException {
        //Arrange
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        double expectedResult = -66.97001;
        //Assert
        assertEquals(expectedResult,shipmov.getLongitude());

        //Act
        expectedResult = -66.97003;
        shipmov.setLongitude(expectedResult);
        //Assert
        assertEquals(expectedResult,shipmov.getLongitude());
    }

    /**
     * Ensure sog is Working
     */
    @Test
    void testsog() throws IOException {
        //Arrange
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        double expectedResult = 12.9;
        //Assert
        assertEquals(expectedResult,shipmov.getSog());

        //Act
        expectedResult = -12.8;
        shipmov.setSog(expectedResult);
        //Assert
        assertEquals(expectedResult,shipmov.getSog());
    }

    /**
     * Ensure cog is Working
     */
    @Test
    void testcog() throws IOException {
        //Arrange
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        double expectedResult = 13.1;
        //Assert
        assertEquals(expectedResult,shipmov.getCog());

        //Act
        expectedResult = 13.7;
        shipmov.setCog(expectedResult);
        //Assert
        assertEquals(expectedResult,shipmov.getCog());
    }

    /**
     * Ensure Heading is Working
     */
    @Test
    void testheading() throws IOException {
        //Arrange
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        double expectedResult = 355;
        //Assert
        assertEquals(expectedResult,shipmov.getHeading());

        //Act
        expectedResult = 354;
        shipmov.setHeading(expectedResult);
        //Assert
        assertEquals(expectedResult,shipmov.getHeading());
    }

    /**
     * Ensure Position is Working
     */
    @Test
    void testPosition() throws IOException {
        //Arrange
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        String expectedResult = "NA";
        //Assert
        assertEquals(expectedResult,shipmov.getPosition());

        //Act
        expectedResult = "354";
        shipmov.setPosition(expectedResult);
        //Assert
        assertEquals(expectedResult,shipmov.getPosition());
    }

    /**
     * Ensure TransceiverClass is Working
     */
    @Test
    void testTrasnceiverClass() throws IOException {
        //Arrange
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        String expectedResult = "B";
        //Assert
        assertEquals(expectedResult,shipmov.getTransceiverClass());

        //Act
        expectedResult = "354";
        shipmov.setTransceiverClass(expectedResult);
        //Assert
        assertEquals(expectedResult,shipmov.getTransceiverClass());
    }

    /**
     * Ensure Compare to is Working
     */
    @Test
    void testCompareto() throws IOException {
        //Arrange
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov1 = new TemporalMessages("01/01/2021 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        //Act
        int expectedResult = shipmov.getBaseDateTime().compareTo(shipmov1.getBaseDateTime());
        //Assert
        assertEquals(expectedResult,shipmov.compareTo(shipmov1));
    }
}