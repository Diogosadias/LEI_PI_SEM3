package lapr.project.ui;

import lapr.project.controller.*;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Scanner;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.City;
import lapr.project.model.Port;
import lapr.project.model.PortTree;

public class TrafficManagerUI {

    private String filename = "data/data-ships&ports/bships.csv";
    private String code1="211331640";
    private String code3= "DHBN";
    private String code2="IMO9193305";
    private String date= "31/12/2020 00:01";
    private String date1="31/12/2020";
    private String date2= "31/12/2020 17:00";
    private int n=5;
    private String date3= "2021.12.05";
    private int nConnections = 5;
    private Port port1 = new Port("America", "United States", 14635, "Los Angeles", 33.716667, -118.26667);
    private Port port2 = new Port("America", "Mexico", 14277, "Manzanillo", 19.05, -104.333336);
    private City city1 = new City("Lisbon", "Portugal", "Europe", new Point2D.Double(38.71666667, -9.133333));
    private City city2 = new City("Rome", "Italy", "Europe", new Point2D.Double(41.9, 12.483333));


    public TrafficManagerUI() throws IOException {
        //Creation Only
    }



    public void run(DatabaseConnection databaseConnection) throws IOException {
        TrafficManagerController trafficManagerController = new TrafficManagerController();


        Scanner scanner = new Scanner(System.in);


        boolean flag =true;
        while(flag){
        System.out.println("Traffic Manager!" +
                "\nPlease Select the task from the following:" +
                "\n1 - Import Ships" +
                "\n2 - Search Details" +
                "\n3 - Search Move By Date" +
                "\n4 - Search Move By Date Range" +
                "\n5 - Summary of Ship" +
                "\n6 - Top-N" +
                "\n7 - Pairs of Ships" +
                "\n8 - Get Closest Port" +
                "\n9 - Ship Availability" +
                "\n10 - Build Freight" +
                "\n11 - Color Map" +
                "\n12 - Select N Closest Places" +
                "\n13 - Get N Critical Ports" +
                "\n14 - Get Shortest Path Between 2 Places" +
                "\n15 - Get Most Efficient Circuit" +
                "\nE - Exit");

        String inputString = scanner.nextLine();
        switch (inputString) {
            case "1":
                trafficManagerController.importFile(filename); //US101 - Import file & US103 - Positional Messages
                break;
            case "2":
                trafficManagerController.searchDetails(code1); //US102 - Search Ship by code MMSI - Implemented
                trafficManagerController.searchDetails(code2); //US102 - Search Ship by code CallSign - Implemented
                trafficManagerController.searchDetails(code3); //US102 - Search Ship by code ISO - Implemented                break;
                break;
            case "3":
                trafficManagerController.searchDate(code1, date1); //US103 - Search Ship by code MMSI on Date - Implemented
                trafficManagerController.searchDate(code2, date1); //US103 - Search Ship by code CallSign on Date - Implemented
                trafficManagerController.searchDate(code3, date1); //US103 - Search Ship by code ISO on Date - Implemented
                break;
            case "4":
                trafficManagerController.searchDate(code1, date, date2); //US103 - Search Ship by code MMSI on Date Range - Implemented
                trafficManagerController.searchDate(code2, date, date2); //US103 - Search Ship by code CallSign on Date Range - Implemented
                trafficManagerController.searchDate(code3, date, date2); //US103 - Search Ship by code ISO on Date Range - Implemented
                break;
            case "5":
                trafficManagerController.summary(code1); //US104 - Summary Ship by code MMSI
                trafficManagerController.summary(code2); //US104 - Summary Ship by code CallSign
                trafficManagerController.summary(code3); //US104 - Summary Ship by code ISO
                break;
            case "6":
                trafficManagerController.getTopN(n, date, date2); //US106 - TopN for Date Range and Group By Vessel Type - Implemented
                break;
            case "7":
                trafficManagerController.pairsofShips(); //US107 - Pair Ships with close location and Diferent Travel Distance - Implemented
                break;
            case "8":
                trafficManagerController.closestPort(databaseConnection, code3, date);
                break;
            case "9":
                trafficManagerController.shipAvailableMonday(databaseConnection,date3);
                break;
            case "10":
                trafficManagerController.buildFreight(databaseConnection, nConnections);
                break;
            case "11":
                trafficManagerController.colorTheMap();
                break;
            case "12":
                trafficManagerController.selectNPlaces(n);
                break;
            case "13":
                trafficManagerController.buildFreight(databaseConnection, nConnections);
                trafficManagerController.criticalPorts(n);
                break;
            case "14":
                trafficManagerController.buildFreight(databaseConnection, nConnections);
                trafficManagerController.getLandPath(city1, city2); //only land
               trafficManagerController.getMaritimePath(port1, port2);  //only sea
               trafficManagerController.getLandOrSeaPath(city1, port1); //both
                break;
            case "15":
                trafficManagerController.buildFreight(databaseConnection, nConnections);
                trafficManagerController.findCircuit();
                break;
            case "E":
                flag = false;
            break;
            default:
        }
        }

    }
}
