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

    }

    public void searchDetails(Object code) {
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
    }

    public void getTopN(Object n, Object date1, Object date2) {
    }

    public void pairsofShips() {
    }
}
