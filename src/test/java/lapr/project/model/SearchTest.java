package lapr.project.model;

import java.awt.geom.Point2D;
import lapr.project.controller.TrafficManagerController;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import lapr.project.utils.PL.MatrixGraph;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {

    Search search = new Search();
    TrafficManagerController main = new TrafficManagerController();
    private String code1 = "210950000";
    private String code2 = "IMO9395044";
    private String code3 = "C4SQ2";
    private String date = "31/12/2020";
    private String date1 = "30/12/2020 17:19";
    private String date2 = "30/01/2021 17:19";
    private int n = 5;

    private String s = "Input is Invalid!";
    private String s2 = "-------------------------------------------------------------------------------------------------";
    MatrixGraph instance;

    SearchTest() throws IOException {
    }

    @Test
    public void testsearchDetails() throws IOException {

        try {
            search.searchDetails(null, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        Ship s = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        main.mmsiTree.insert(s);
        String result = "Ship{MMSI=210950000, VesselName=VARAMO, IMO=IMO9395044, CallSign=C4SQ2, VesselType=70, Length=166, Width=25, Draft=9.5, Cargo=NA}\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails(Integer.parseInt(code1), main), result);
        main.imoTree.insert(s);
        result = "Ship{MMSI=210950000, VesselName=VARAMO, IMO=IMO9395044, CallSign=C4SQ2, VesselType=70, Length=166, Width=25, Draft=9.5, Cargo=NA}\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails(code2, main), result);
        main.csTree.insert(s);
        result = "Ship{MMSI=210950000, VesselName=VARAMO, IMO=IMO9395044, CallSign=C4SQ2, VesselType=70, Length=166, Width=25, Draft=9.5, Cargo=NA}\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails(code3, main), result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails(210950001, main), result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDetails("IMO9395042", main), result);

        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.searchDetails("C4SQ1", main), result);

        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.searchDetails("1234588", main), result);

    }

    @Test
    public void testsearchDate() throws IOException {

        try {
            search.searchDate(null, date, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        try {
            search.searchDate(code1, null, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        try {
            search.searchDate(null, null, main);
        } catch (IOException ex) {
            System.out.println(s);
        }

        Ship s = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "NA", "B");
        s.getMovements().insert(shipmov);
        main.mmsiTree.insert(s);
        String result = "Moves for : 31/12/2020\n"
                + "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(Integer.parseInt(code1), date, main), result);
        main.imoTree.insert(s);
        result = "Moves for : 31/12/2020\n"
                + "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(code2, date, main), result);
        main.csTree.insert(s);
        result = "Moves for : 31/12/2020\n"
                + "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(code3, date, main), result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(210950001, date, main), result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate("IMO9395042", date, main), result);

        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.searchDate("C4SQ1", date, main), result);

        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.searchDate("1234588", date, main), result);

    }

    @Test
    public void testSearchDate3parameters() throws IOException {
        try {
            search.searchDate(null, date1, date2, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        try {
            search.searchDate(code1, null, date2, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        try {
            search.searchDate(code1, date1, null, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        try {
            search.searchDate(null, null, date2, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        try {
            search.searchDate(null, date1, null, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        try {
            search.searchDate(code1, null, null, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        try {
            search.searchDate(null, null, null, main);
        } catch (IOException ex) {
            System.out.println(s);
        }

        Ship s = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "NA", "B");
        s.getMovements().insert(shipmov);
        main.mmsiTree.insert(s);
        String result = "Moves from - 30/12/2020 17:19 to -30/01/2021 17:19\n"
                + "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(Integer.parseInt(code1), date1, date2, main), result);
        main.imoTree.insert(s);
        result = "Moves from - 30/12/2020 17:19 to -30/01/2021 17:19\n"
                + "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(code2, date1, date2, main), result);
        main.csTree.insert(s);
        result = "Moves from - 30/12/2020 17:19 to -30/01/2021 17:19\n"
                + "BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass \n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "31/12/2020 17:19\t42.97875\t-66.97001\t12.9\t\t13.1\t\t355.0\t\t\tNA\t\t\tB\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(code3, date1, date2, main), result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate(210950001, date1, date2, main), result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.searchDate("IMO9395042", date1, date2, main), result);

        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.searchDate("C4SQ1", date1, date2, main), result);

        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.searchDate("1234588", date1, date2, main), result);

    }

    @Test
    public void testsummary() throws IOException {

        try {
            search.summary(null, main);
        } catch (IOException ex) {
            System.out.println(s);
        }
        Ship s = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70, 166, 25, 9.5, "NA");
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19", 42.97875, -66.97001, 12.9, 13.1, 355, "NA", "B");
        s.getMovements().insert(shipmov);
        main.mmsiTree.insert(s);
        String result = "The Ship with code 210950000 has:\n"
                + "\n"
                + "VesselName=VARAMO\n"
                + "Start BaseDateTime=2020-12-31T17:19\n"
                + "End BaseDateTime=2020-12-31T17:19\n"
                + "TotalMovementTimePT0S\n"
                + "TotalNumberOfMovements1\n"
                + "MaxSOG12.9\n"
                + "MeanSOG12.9\n"
                + "MaxCOG=13.1\n"
                + "MeanCOG=13.1\n"
                + "DepartureLatitude42.97875\n"
                + "DepartureLongitude-66.97001\n"
                + "ArrivalLatitude=42.97875\n"
                + "ArrivalLongitude=-66.97001\n"
                + "TravelledDistance=\n"
                + "0.0\n"
                + "DeltaDistance=0.0\n"
                + "\n"
                + "\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary(Integer.parseInt(code1), main), result);
        main.imoTree.insert(s);
        result = "The Ship with code IMO9395044 has:\n"
                + "\n"
                + "VesselName=VARAMO\n"
                + "Start BaseDateTime=2020-12-31T17:19\n"
                + "End BaseDateTime=2020-12-31T17:19\n"
                + "TotalMovementTimePT0S\n"
                + "TotalNumberOfMovements1\n"
                + "MaxSOG12.9\n"
                + "MeanSOG12.9\n"
                + "MaxCOG=13.1\n"
                + "MeanCOG=13.1\n"
                + "DepartureLatitude42.97875\n"
                + "DepartureLongitude-66.97001\n"
                + "ArrivalLatitude=42.97875\n"
                + "ArrivalLongitude=-66.97001\n"
                + "TravelledDistance=\n"
                + "0.0\n"
                + "DeltaDistance=0.0\n"
                + "\n"
                + "\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary(code2, main), result);
        main.csTree.insert(s);
        result = "The Ship with code C4SQ2 has:\n"
                + "\n"
                + "VesselName=VARAMO\n"
                + "Start BaseDateTime=2020-12-31T17:19\n"
                + "End BaseDateTime=2020-12-31T17:19\n"
                + "TotalMovementTimePT0S\n"
                + "TotalNumberOfMovements1\n"
                + "MaxSOG12.9\n"
                + "MeanSOG12.9\n"
                + "MaxCOG=13.1\n"
                + "MeanCOG=13.1\n"
                + "DepartureLatitude42.97875\n"
                + "DepartureLongitude-66.97001\n"
                + "ArrivalLatitude=42.97875\n"
                + "ArrivalLongitude=-66.97001\n"
                + "TravelledDistance=\n"
                + "0.0\n"
                + "DeltaDistance=0.0\n"
                + "\n"
                + "\n"
                + "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary(code3, main), result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary(210950001, main), result);

        result = "-------------------------------------------------------------------------------------------------";
        assertEquals(search.summary("IMO9395042", main), result);

        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.summary("C4SQ1", main), result);

        result = "Ship Code was not according regulations!" + "\n" + s2;
        assertEquals(search.summary("1234588", main), result);

    }
//     @Test
//    public void testSelectNPlaces() {
// String result = "Invalid N Value!";
// assertEquals(search.selectNPlaces(0,null,main),result);
//}

    @Test
    public void testGetMaritimePath() throws IOException {

        Port port1 = new Port("America", "United States", 14635, "Los Angeles", 33.716667, -118.26667);
        Port port2 = new Port("America", "Mexico", 14277, "Manzanillo", 19.05, -104.333336);
        Port port3 = new Port("America", "Chile", 14985, "Puerto Varas", 12.03, -90.891);
        Port port4 = new Port("America", "Brasil", 14217, "São Paulo", 19.05, -115.89821);
        City city1 = new City("Nicosia", "Cyprus", "Europe", new Point2D.Double(35.16666667, 33.366667));

        MatrixGraph matrixGraph = new MatrixGraph(false);
        matrixGraph.addVertex(port1);
        matrixGraph.addVertex(port2);
        matrixGraph.addVertex(port3);
        matrixGraph.addEdge(port1, port2, 1000.0);
        matrixGraph.addEdge(port1, port3, 300.0);
        matrixGraph.addEdge(port1, port4, 250.0);
        matrixGraph.addEdge(port2, port3, 50.0);
        matrixGraph.addEdge(port2, port4, 150.0);
        matrixGraph.addEdge(port3, port4, 110.0);
        matrixGraph.addEdge(port4, city1, 2000.0);

        String result = "The shortest path between the departure port " + port1 + " and the arrival port " + port2 + " is: " + 350.0 + ".\n"; //caminho mais curto do port 1 ao port 2 é : port1-port3-port2
        assertEquals(search.getMaritimePath(port1, port2, matrixGraph), result);

        result = "The departure port" + port4 + " or the arrival port " + city1 + " are not a port!\n";
        assertEquals(search.getMaritimePath(port4, city1, matrixGraph), result);

        Port portNotInGraph = new Port("Europe", "Portugal", 13908, "Porto", 41.15, -8.89821);
        result = "At least one of the ports " + port4 + " or " + portNotInGraph + " was not in the graph!\n";
        assertEquals(search.getMaritimePath(port4, portNotInGraph, matrixGraph), result);

        Port portInGraphButNoConnection = new Port("Europe", "Espanha", 13702, "Barcelona", 41.3879, 2.17);
        matrixGraph.addVertex(portInGraphButNoConnection);
        result = "Between the ports " + port4 + " and " + portInGraphButNoConnection + " there isn't a possible path!\n";
        assertEquals(search.getMaritimePath(port4, portInGraphButNoConnection, matrixGraph), result);

    }
        @Test
    public void testGetLandPath() throws IOException {

        Port port1 = new Port("America", "United States", 14635, "Los Angeles", 33.716667, -118.26667);
        Port port2 = new Port("America", "Mexico", 14277, "Manzanillo", 19.05, -104.333336);
        Port port3 = new Port("America", "Chile", 14985, "Puerto Varas", 12.03, -90.891);
        City city1 = new City("Nicosia", "Cyprus", "Europe", new Point2D.Double(35.16666667, 33.366667));
        City city2 = new City("Paris", "France", "Europe", new Point2D.Double(48.51, 21.1));
        City city3 = new City("Berlim", "Germany", "Europe", new Point2D.Double(52.31, 13.23));

        MatrixGraph matrixGraph = new MatrixGraph(false);
        matrixGraph.addVertex(port1);
        matrixGraph.addVertex(port2);
        matrixGraph.addVertex(port3);
        matrixGraph.addVertex(city1);
        matrixGraph.addVertex(city2);
        matrixGraph.addVertex(city3);
        matrixGraph.addEdge(port1, port2, 1000.0);
        matrixGraph.addEdge(port1, port3, 300.0);
        matrixGraph.addEdge(port2, port3, 50.0);
        matrixGraph.addEdge(port1, city1, 90.0);
        matrixGraph.addEdge(port1, city2, 120.0);
        matrixGraph.addEdge(port1, city3, 200.0);
        matrixGraph.addEdge(city1, city2, 800.0);
        matrixGraph.addEdge(city1, city3, 300.0);
        matrixGraph.addEdge(city2, city3, 400.0);

        String result = "The shortest path between the departure place " + city1 + " and the arrival place " + city2 + " is: " + 210.0 + ".\n";
        assertEquals(search.getLandPath(city1, city2, matrixGraph), result);
        
        result = "The shortest path between the departure place " + port1 + " and the arrival place " + city1 + " is: " + 90.0 + ".\n";
        assertEquals(search.getLandPath(port1, city1, matrixGraph), result);

        
        result = "The departure place" + port1 + " or the arrival place " + port2 + " didn't meet the requirements\n";
        assertEquals(search.getLandPath(port1, port2, matrixGraph), result);
        
        Port portNotInGraph = new Port("Europe", "Portugal", 13908, "Porto", 41.15, -8.89821);
        result = "At least one of the places " + city1 + " or " + portNotInGraph + " was not in the graph!\n";
        assertEquals(search.getLandPath(city1, portNotInGraph, matrixGraph), result);

        Port portInGraphButNoConnection = new Port("Europe", "Espanha", 13702, "Barcelona", 41.3879, 2.17);
        matrixGraph.addVertex(portInGraphButNoConnection);
        result = "Between the places " + city1 + " and " + portInGraphButNoConnection + " there isn't a possible path!\n";
        assertEquals(search.getLandPath(city1, portInGraphButNoConnection, matrixGraph), result);
    }
            @Test
    public void testGetLandOrSeaPath() throws IOException {

        Port port1 = new Port("America", "United States", 14635, "Los Angeles", 33.716667, -118.26667);
        Port port2 = new Port("America", "Mexico", 14277, "Manzanillo", 19.05, -104.333336);
        Port port3 = new Port("America", "Chile", 14985, "Puerto Varas", 12.03, -90.891);
        City city1 = new City("Nicosia", "Cyprus", "Europe", new Point2D.Double(35.16666667, 33.366667));
        City city2 = new City("Paris", "France", "Europe", new Point2D.Double(48.51, 21.1));
        City city3 = new City("Berlim", "Germany", "Europe", new Point2D.Double(52.31, 13.23));

        MatrixGraph matrixGraph = new MatrixGraph(false);
        matrixGraph.addVertex(port1);
        matrixGraph.addVertex(port2);
        matrixGraph.addVertex(port3);
        matrixGraph.addVertex(city1);
        matrixGraph.addVertex(city2);
        matrixGraph.addVertex(city3);
        matrixGraph.addEdge(port1, port2, 1000.0);
        matrixGraph.addEdge(port1, port3, 300.0);
        matrixGraph.addEdge(port2, port3, 50.0);
        matrixGraph.addEdge(port1, city1, 90.0);
        matrixGraph.addEdge(port1, city2, 120.0);
        matrixGraph.addEdge(port1, city3, 200.0);
        matrixGraph.addEdge(city1, city2, 800.0);
        matrixGraph.addEdge(city1, city3, 300.0);
        matrixGraph.addEdge(city2, city3, 400.0);

        String result = "The shortest path between the departure place " + city1 + " and the arrival place " + city2 + " is: " + 210.0 + ".\n";
        assertEquals(search.getLandOrSeaPath(city1, city2, matrixGraph), result);
        
        result = "The shortest path between the departure place " + port1 + " and the arrival place " + city1 + " is: " + 90.0 + ".\n";
        assertEquals(search.getLandOrSeaPath(port1, city1, matrixGraph), result);
        
        result = "The shortest path between the departure place " + port1 + " and the arrival place " + port2 + " is: " + 350.0 + ".\n";
        assertEquals(search.getLandOrSeaPath(port1, port2, matrixGraph), result);

        
        City cityNotInGraph = new City("Russia", "Moscovo", "Europe", new Point2D.Double(55.45, 37.37));
        result = "At least one of the places " + city1 + " or " + cityNotInGraph + " was not in the graph!\n";
        assertEquals(search.getLandOrSeaPath(city1, cityNotInGraph, matrixGraph), result);

        City cityInGraphButNoConnection = new City("Ucrânia", "Kiev", "Europe", new Point2D.Double(50.27, 30.31));
        matrixGraph.addVertex(cityInGraphButNoConnection);
        result = "Between the places " + port1 + " and " + cityInGraphButNoConnection + " there isn't a possible path!\n";
        assertEquals(search.getLandOrSeaPath(port1, cityInGraphButNoConnection, matrixGraph), result);
    }
}
