package lapr.project.ui;

import lapr.project.controller.*;

import java.io.IOException;
import java.util.Scanner;

import lapr.project.data.DatabaseConnection;
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
                trafficManagerController.shipAvailableMonday();
                break;
            case "E":
                flag = false;
            break;
            default:
        }
        }

    }
}
