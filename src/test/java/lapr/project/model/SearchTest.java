package lapr.project.model;

import lapr.project.controller.TrafficManagerController;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {
    Search search = new Search();
    TrafficManagerController main = new TrafficManagerController();
    private String code1="210950000";
    private String code2= "IMO9395044";
    private String code3="C4SQ2";
    private String date= "31/12/2020";
    private String date1= "30/12/2020 17:19";
    private String date2= "30/01/2021 17:19";
    private int n=5;

    private String s = "Input is Invalid!";
    private String s2 ="-------------------------------------------------------------------------------------------------";

    SearchTest() throws IOException {
    }


    @Test
    public void testsearchDetails() throws IOException {

        try{
            search.searchDetails(null,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        Ship s = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        main.mmsiTree.insert(s);
        String result = "Ship{MMSI=210950000, VesselName=VARAMO, IMO=IMO9395044, CallSign=C4SQ2, VesselType=70, Length=166, Width=25, Draft=9.5, Cargo=NA}\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails(Integer.parseInt(code1),main),result);
        main.imoTree.insert(s);
        result = "Ship{MMSI=210950000, VesselName=VARAMO, IMO=IMO9395044, CallSign=C4SQ2, VesselType=70, Length=166, Width=25, Draft=9.5, Cargo=NA}\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails(code2,main),result);
        main.csTree.insert(s);
        result = "Ship{MMSI=210950000, VesselName=VARAMO, IMO=IMO9395044, CallSign=C4SQ2, VesselType=70, Length=166, Width=25, Draft=9.5, Cargo=NA}\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails(code3,main),result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails(210950001,main),result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails("IMO9395042",main),result);


        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.searchDetails("C4SQ1",main),result);


        result = "Ship Code was not according regulations!" +"\n"+ s2;
        assertEquals(search.searchDetails("1234588",main),result);




    }

    @Test
    public void testsearchDate() throws IOException {

        try{
            search.searchDate(null,date,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        try{
            search.searchDate(code1,null,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        try{
            search.searchDate(null,null,main);
        } catch (IOException ex){
            System.out.println(s);
        }

        Ship s = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        s.getMovements().insert(shipmov);
        main.mmsiTree.insert(s);
        String result = "Moves for : 31/12/2020\n" +
                "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(Integer.parseInt(code1),date,main),result);
        main.imoTree.insert(s);
        result = "Moves for : 31/12/2020\n" +
                "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(code2,date,main),result);
        main.csTree.insert(s);
        result = "Moves for : 31/12/2020\n" +
                "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(code3,date,main),result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(210950001,date,main),result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate("IMO9395042",date,main),result);


        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.searchDate("C4SQ1",date,main),result);


        result = "Ship Code was not according regulations!" +"\n"+ s2;
        assertEquals(search.searchDate("1234588",date,main),result);





    }

    @Test
    public void testSearchDate3parameters() throws IOException {
        try{
            search.searchDate(null,date1,date2,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        try{
            search.searchDate(code1,null,date2,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        try{
            search.searchDate(code1,date1,null,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        try{
            search.searchDate(null,null,date2,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        try{
            search.searchDate(null,date1,null,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        try{
            search.searchDate(code1,null,null,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        try{
            search.searchDate(null,null,null,main);
        } catch (IOException ex){
            System.out.println(s);
        }

        Ship s = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        s.getMovements().insert(shipmov);
        main.mmsiTree.insert(s);
        String result = "Moves from - 30/12/2020 17:19 to -30/01/2021 17:19\n" +
                "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(Integer.parseInt(code1),date1,date2,main),result);
        main.imoTree.insert(s);
        result = "Moves from - 30/12/2020 17:19 to -30/01/2021 17:19\n" +
                "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(code2,date1,date2,main),result);
        main.csTree.insert(s);
        result = "Moves from - 30/12/2020 17:19 to -30/01/2021 17:19\n" +
                "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(code3,date1,date2,main),result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(210950001,date1,date2,main),result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate("IMO9395042",date1,date2,main),result);


        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.searchDate("C4SQ1",date1,date2,main),result);


        result = "Ship Code was not according regulations!" +"\n"+ s2;
        assertEquals(search.searchDate("1234588",date1,date2,main),result);



    }

    @Test
    public void testsummary() throws IOException {

        try{
            search.summary(null,main);
        } catch (IOException ex){
            System.out.println(s);
        }
        Ship s = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        s.getMovements().insert(shipmov);
        main.mmsiTree.insert(s);
        String result = "The Ship with code 210950000 has:\n" +
                "\n" +
                "VesselName=VARAMO\n" +
                "Start BaseDateTime=2020-12-31T17:19\n" +
                "End BaseDateTime=2020-12-31T17:19\n" +
                "TotalMovementTimePT0S\n" +
                "TotalNumberOfMovements1\n" +
                "MaxSOG12.9\n" +
                "MeanSOG12.9\n" +
                "MaxCOG=13.1\n" +
                "MeanCOG=13.1\n" +
                "DepartureLatitude42.97875\n" +
                "DepartureLongitude-66.97001\n" +
                "ArrivalLatitude=42.97875\n" +
                "ArrivalLongitude=-66.97001\n" +
                "TravelledDistance=\n" +
                "0.0\n" +
                "DeltaDistance=0.0\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary(Integer.parseInt(code1),main),result);
        main.imoTree.insert(s);
        result = "The Ship with code IMO9395044 has:\n" +
                "\n" +
                "VesselName=VARAMO\n" +
                "Start BaseDateTime=2020-12-31T17:19\n" +
                "End BaseDateTime=2020-12-31T17:19\n" +
                "TotalMovementTimePT0S\n" +
                "TotalNumberOfMovements1\n" +
                "MaxSOG12.9\n" +
                "MeanSOG12.9\n" +
                "MaxCOG=13.1\n" +
                "MeanCOG=13.1\n" +
                "DepartureLatitude42.97875\n" +
                "DepartureLongitude-66.97001\n" +
                "ArrivalLatitude=42.97875\n" +
                "ArrivalLongitude=-66.97001\n" +
                "TravelledDistance=\n" +
                "0.0\n" +
                "DeltaDistance=0.0\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary(code2,main),result);
        main.csTree.insert(s);
        result = "The Ship with code C4SQ2 has:\n" +
                "\n" +
                "VesselName=VARAMO\n" +
                "Start BaseDateTime=2020-12-31T17:19\n" +
                "End BaseDateTime=2020-12-31T17:19\n" +
                "TotalMovementTimePT0S\n" +
                "TotalNumberOfMovements1\n" +
                "MaxSOG12.9\n" +
                "MeanSOG12.9\n" +
                "MaxCOG=13.1\n" +
                "MeanCOG=13.1\n" +
                "DepartureLatitude42.97875\n" +
                "DepartureLongitude-66.97001\n" +
                "ArrivalLatitude=42.97875\n" +
                "ArrivalLongitude=-66.97001\n" +
                "TravelledDistance=\n" +
                "0.0\n" +
                "DeltaDistance=0.0\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary(code3,main),result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary(210950001,main),result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary("IMO9395042",main),result);


        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.summary("C4SQ1",main),result);


        result = "Ship Code was not according regulations!" +"\n"+ s2;
        assertEquals(search.summary("1234588",main),result);

    }
     @Test
    public void testSelectNPlaces() {
 String result = "Invalid N Value!";
 assertEquals(search.selectNPlaces(0,null,main),result);
}
}