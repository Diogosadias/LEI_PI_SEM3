package lapr.project.ui;

import lapr.project.controller.*;

public class MainUI {


    private Object code;
    private Object date;
    private Object date1;
    private Object date2;
    private Object n;

    public void run() {

        MainController mainController = new MainController();
        mainController.importFile(); //US101 - Import file & US103 - Positional Messages

        mainController.searchDetails(code); //US102 - Search Ship by code MMSI
        mainController.searchDetails(code); //US102 - Search Ship by code CallSign
        mainController.searchDetails(code); //US102 - Search Ship by code ISO

        mainController.searchDate(code,date); //US103 - Search Ship by code MMSI on Date
        mainController.searchDate(code,date); //US103 - Search Ship by code CallSign on Date
        mainController.searchDate(code,date); //US103 - Search Ship by code ISO on Date

        mainController.searchDate(code,date1,date2); //US103 - Search Ship by code MMSI on Date Range
        mainController.searchDate(code,date1,date2); //US103 - Search Ship by code CallSign on Date Range
        mainController.searchDate(code,date1,date2); //US103 - Search Ship by code ISO on Date Range

        mainController.summary(code); //US104 - Summary Ship by code MMSI
        mainController.summary(code); //US104 - Summary Ship by code CallSign
        mainController.summary(code); //US104 - Summary Ship by code ISO

        mainController.getTopN(n,date1,date2); //US106 - TopN for Date Range and Group By Vessel Type

        mainController.pairsofShips(); //US107 - Pair Ships with close location and Diferent Travel Distance




    }
}
