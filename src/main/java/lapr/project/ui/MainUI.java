package lapr.project.ui;

import lapr.project.controller.*;

public class MainUI {


    private String code1="211331640";
    private String code2= "DHBN";
    private String code3="IMO9193305";
    private String date="31/12/2020 17:19";
    private String date2="30/01/2021 17:19";
    private int n=5;

    public void run() {

        MainController mainController = new MainController();
        //Pedro
        mainController.importFile(); //US101 - Import file & US103 - Positional Messages

        mainController.searchDetails(code1); //US102 - Search Ship by code MMSI
        mainController.searchDetails(code2); //US102 - Search Ship by code CallSign
        mainController.searchDetails(code3); //US102 - Search Ship by code ISO

        //Diogo
        mainController.searchDate(code1,date); //US103 - Search Ship by code MMSI on Date
        mainController.searchDate(code2,date); //US103 - Search Ship by code CallSign on Date
        mainController.searchDate(code3,date); //US103 - Search Ship by code ISO on Date

        mainController.searchDate(code1,date,date2); //US103 - Search Ship by code MMSI on Date Range
        mainController.searchDate(code2,date,date2); //US103 - Search Ship by code CallSign on Date Range
        mainController.searchDate(code3,date,date2); //US103 - Search Ship by code ISO on Date Range

        //Duarte
        mainController.summary(code1); //US104 - Summary Ship by code MMSI
        mainController.summary(code2); //US104 - Summary Ship by code CallSign
        mainController.summary(code3); //US104 - Summary Ship by code ISO

        //Diogo
        mainController.getTopN(n,date,date2); //US106 - TopN for Date Range and Group By Vessel Type

        mainController.pairsofShips(); //US107 - Pair Ships with close location and Diferent Travel Distance




    }
}
