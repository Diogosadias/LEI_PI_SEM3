package lapr.project.controller;

import lapr.project.model.*;

import java.io.IOException;

public class MainController {
    private ShipTree mmsiTree;
    private ShipTree isoTree;
    private ShipTree csTree;
    /*
    Mais tarde criar classe Software/APP para armazenar tudo o que é importante
    */

    public void importFile() {
        Import importer = new Import();
        mmsiTree = importer.importMMSI();
        isoTree = importer.importISO();
        csTree = importer.importCS();
    }

    public void searchDetails(Object code) {
        if(mmsiTree.isMMSI(code)) {
            if(mmsiTree.find(code)){
                System.out.print(mmsiTree.getShip(code).toString());
            }
        } else if(isoTree.isISO(code)){
            if(isoTree.find(code)){
                System.out.print(isoTree.getShip(code).toString());
            }
        } else if(csTree.isCS(code)){
            if(csTree.find(code)){
                System.out.print(csTree.getShip(code).toString());
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void searchDate(Object code, Object date) {
        if(mmsiTree.isMMSI(code)) {
            if(mmsiTree.find(code)){
                System.out.print(mmsiTree.getShip(code).getMovements().getMoveByDate(date));
            }
        } else if(isoTree.isISO(code)){
            if(isoTree.find(code)){
                System.out.print(isoTree.getShip(code).getMovements().getMoveByDate(date));
            }
        } else if(csTree.isCS(code)){
            if(csTree.find(code)){
                System.out.print(csTree.getShip(code).getMovements().getMoveByDate(date));
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void searchDate(Object code, Object date1, Object date2) throws IOException {
        if(mmsiTree.isMMSI(code)) {
            if(mmsiTree.find(code)){
                System.out.print(mmsiTree.getShip(code).getMovements().searchDateFrame(date1,date2));
            }
        } else if(isoTree.isISO(code)){
            if(isoTree.find(code)){
                System.out.print(isoTree.getShip(code).getMovements().searchDateFrame(date1,date2));
            }
        } else if(csTree.isCS(code)){
            if(csTree.find(code)){
                System.out.print(csTree.getShip(code).getMovements().searchDateFrame(date1,date2));
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void summary(Object code) {
        if(mmsiTree.isMMSI(code)) {
            if(mmsiTree.find(code)){
                System.out.print(mmsiTree.getShip(code).getMovements().getSummary(code));
            }
        } else if(isoTree.isISO(code)){
            if(isoTree.find(code)){
                System.out.print(isoTree.getShip(code).getMovements().getSummary(code));
            }
        } else if(csTree.isCS(code)){
            if(csTree.find(code)){
                System.out.print(csTree.getShip(code).getMovements().getSummary(code));
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void getTopN(Object n, Object date1, Object date2) {
        if((int)n>mmsiTree.size()) throw new UnsupportedOperationException("Ships are not enough to fulfill requirement!");
        TopN topsum = new TopN();
        System.out.println(topsum.getTop(n,date1,date2));
    }

    public void pairsofShips() {
        System.out.println(mmsiTree.pairs());
    }
}
