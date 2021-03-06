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
    void testGetDate() throws IOException {
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
        TemporalMessages shipmov1 = new TemporalMessages(getDate("31/12/2020 17:19"),42.97875,-66.97001,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov2 = new TemporalMessages(getDate("31/12/2020 17:19"));
        //Act
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime expectedResult = LocalDateTime.parse("31/12/2020 17:19",format);
        LocalDateTime result =shipmov.getBaseDateTime();
        //Assert
        assertEquals(expectedResult,shipmov1.getBaseDateTime());
        assertEquals(expectedResult,result);
        assertEquals(expectedResult,shipmov.getBaseDateTime());
        assertEquals(expectedResult,shipmov2.getBaseDateTime());


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
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 17:19",-90,-66.97001,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 17:19",90,-66.97001,12.9,13.1,355, "NA","B");

        //Act
        double expectedResult = 42.97875;
        //Assert
        assertEquals(expectedResult,shipmov.getLatitude());
        assertEquals(-90,shipmov1.getLatitude());
        assertEquals(90,shipmov2.getLatitude());


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
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 17:19",42.97875,-180,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 17:19",42.97875,180,12.9,13.1,355, "NA","B");

        //Act
        double expectedResult = -66.97001;
        //Assert
        assertEquals(expectedResult,shipmov.getLongitude());
        assertEquals(-180,shipmov1.getLongitude());
        assertEquals(180,shipmov2.getLongitude());


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
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,0,355, "NA","B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,359,355, "NA","B");

        //Act
        double expectedResult = 13.1;
        //Assert
        assertEquals(expectedResult,shipmov.getCog());
        assertEquals(0,shipmov1.getCog());
        assertEquals(359,shipmov2.getCog());

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
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,0, "NA","B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,359, "NA","B");

        //Act
        double expectedResult = 355;
        //Assert
        assertEquals(expectedResult,shipmov.getHeading());
        assertEquals(0,shipmov1.getHeading());
        assertEquals(359,shipmov2.getHeading());


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

    /***
     * Ensure data is valid by Business Standard
     */
    @Test
    void ensureValidation() throws IOException {
        try{
            TemporalMessages shipmov = new TemporalMessages((String) null,42.97875,-66.97001,12.9,13.1,355, "NA","B");
        }catch (IOException ex){
            System.out.println("Input is Invalid!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",-100,-66.97001,12.9,13.1,355, "NA","B");
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-200,12.9,13.1,355, "NA","B");
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,-3,355, "NA","B");
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,511, "NA","B");
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",91,-66.97001,12.9,13.1,355, "NA","B");
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,181,12.9,13.1,355, "NA","B");
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,360,355, "NA","B");
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,360, "NA","B");
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,-1, "NA","B");
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            TemporalMessages shipmov = new TemporalMessages(null);
        }catch (IOException ex){
            System.out.println("Input is Invalid");
        }
    }
}