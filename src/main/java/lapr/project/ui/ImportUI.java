package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.ShipTree;

public class ImportUI {


    public void run() {

        ImportController importController = new ImportController();
        SearchController searchController = new SearchController();
        SummaryController summaryController = new SummaryController();
        TopNController topNController = new TopNController();
        PairShipsController pairShipsController = new PairShipsController();
        importController.importFile();
        //importController.search();
        //importController.summary();


    }
}
