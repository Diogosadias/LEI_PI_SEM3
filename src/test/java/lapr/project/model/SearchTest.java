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
    private String date= "31/12/2020 17:19";
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
    public void testsearchDate() {
    }

    @Test
    public void testSearchDate3parameters() {
    }

    @Test
    public void testsummary() {
    }
}