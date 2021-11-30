package lapr.project.ui;

import lapr.project.controller.*;

import java.io.IOException;

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

    public void run() throws IOException {

        TrafficManagerController trafficManagerController = new TrafficManagerController();

        trafficManagerController.importFile(filename); //US101 - Import file & US103 - Positional Messages

        trafficManagerController.searchDetails(code1); //US102 - Search Ship by code MMSI - Implemented
        trafficManagerController.searchDetails(code2); //US102 - Search Ship by code CallSign - Implemented
        trafficManagerController.searchDetails(code3); //US102 - Search Ship by code ISO - Implemented

        trafficManagerController.searchDate(code1,date1); //US103 - Search Ship by code MMSI on Date - Implemented
        trafficManagerController.searchDate(code2,date1); //US103 - Search Ship by code CallSign on Date - Implemented
        trafficManagerController.searchDate(code3,date1); //US103 - Search Ship by code ISO on Date - Implemented

        trafficManagerController.searchDate(code1,date,date2); //US103 - Search Ship by code MMSI on Date Range - Implemented
        trafficManagerController.searchDate(code2,date,date2); //US103 - Search Ship by code CallSign on Date Range - Implemented
        trafficManagerController.searchDate(code3,date,date2); //US103 - Search Ship by code ISO on Date Range - Implemented

        trafficManagerController.summary(code1); //US104 - Summary Ship by code MMSI
        trafficManagerController.summary(code2); //US104 - Summary Ship by code CallSign
        trafficManagerController.summary(code3); //US104 - Summary Ship by code ISO

        trafficManagerController.getTopN(n,date,date2); //US106 - TopN for Date Range and Group By Vessel Type - Implemented

        trafficManagerController.pairsofShips(); //US107 - Pair Ships with close location and Diferent Travel Distance - Implemented




    }
}
