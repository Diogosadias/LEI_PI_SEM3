package lapr.project.ui;

import lapr.project.controller.*;

import java.io.IOException;

public class MainUI {


    private String code1="211331640";
    private String code2= "DHBN";
    private String code3="IMO9193305";
    private String date="31/12/2020 17:19";
    private String date2="30/01/2021 17:19";
    private int n=5;

    public void run() throws IOException {

        MainController mainController = new MainController();
        //Pedro
        mainController.importFile(); //US101 - Import file & US103 - Positional Messages

        mainController.searchDetails(code1); //US102 - Search Ship by code MMSI - Implemented
        mainController.searchDetails(code2); //US102 - Search Ship by code CallSign - Implemented
        mainController.searchDetails(code3); //US102 - Search Ship by code ISO - Implemented

        //Diogo
        mainController.searchDate(code1,date); //US103 - Search Ship by code MMSI on Date - Implemented
        mainController.searchDate(code2,date); //US103 - Search Ship by code CallSign on Date - Implemented
        mainController.searchDate(code3,date); //US103 - Search Ship by code ISO on Date - Implemented

        mainController.searchDate(code1,date,date2); //US103 - Search Ship by code MMSI on Date Range - Implemented
        mainController.searchDate(code2,date,date2); //US103 - Search Ship by code CallSign on Date Range - Implemented
        mainController.searchDate(code3,date,date2); //US103 - Search Ship by code ISO on Date Range - Implemented

        //Duarte
        mainController.summary(code1); //US104 - Summary Ship by code MMSI
        mainController.summary(code2); //US104 - Summary Ship by code CallSign
        mainController.summary(code3); //US104 - Summary Ship by code ISO

        //Diogo
        mainController.getTopN(n,date,date2); //US106 - TopN for Date Range and Group By Vessel Type - Implemented

        mainController.pairsofShips(); //US107 - Pair Ships with close location and Diferent Travel Distance - Implemented




    }
}
