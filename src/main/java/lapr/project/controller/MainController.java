package lapr.project.controller;

import lapr.project.model.*;

import java.io.IOException;
import java.util.Map;

public class MainController {

    public void setMmsiTree(ShipTree mmsiTree) {
        this.mmsiTree = mmsiTree;
    }

    private ShipTree mmsiTree;
    private ShipTree imoTree;
    private ShipTree csTree;

    /*
    Mais tarde criar classe Software/APP para armazenar tudo o que é importante
     */
    public MainController() {
    }

    public void importFile() {
        Import importer = new Import();

    }

    public void searchDetails(Object code) {
        if (mmsiTree.isMMSI(code)) {
            if (mmsiTree.find(code)) {
                System.out.print(mmsiTree.getShip(code).toString());
            }
        } else if (imoTree.isISO(code)) {
            if (imoTree.find(code)) {
                System.out.print(imoTree.getShip(code).toString());
            }
        } else if (csTree.isCS(code)) {
            if (csTree.find(code)) {
                System.out.print(csTree.getShip(code).toString());
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void searchDate(Object code, Object date) {
        if (mmsiTree.isMMSI(code)) {
            if (mmsiTree.find(code)) {
                System.out.print(mmsiTree.getShip(code).getMovements().getMoveByDate(date));
            }
        } else if (imoTree.isISO(code)) {
            if (imoTree.find(code)) {
                System.out.print(imoTree.getShip(code).getMovements().getMoveByDate(date));
            }
        } else if (csTree.isCS(code)) {
            if (csTree.find(code)) {
                System.out.print(csTree.getShip(code).getMovements().getMoveByDate(date));
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void searchDate(Object code, Object date1, Object date2) throws IOException {
        if (mmsiTree.isMMSI(code)) {
            if (mmsiTree.find(code)) {
                System.out.print(mmsiTree.getShip(code).getMovements().searchDateFrame(date1, date2));
            }
        } else if (imoTree.isISO(code)) {
            if (imoTree.find(code)) {
                System.out.print(imoTree.getShip(code).getMovements().searchDateFrame(date1, date2));
            }
        } else if (csTree.isCS(code)) {
            if (csTree.find(code)) {
                System.out.print(csTree.getShip(code).getMovements().searchDateFrame(date1, date2));
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void summary(Object code) {
        if (mmsiTree.isMMSI(code)) {
            if (mmsiTree.find(code)) {
                System.out.print(mmsiTree.getShip(code).getMovements().getSummary(code));
            }
        } else if (imoTree.isISO(code)) {
            if (imoTree.find(code)) {
                System.out.print(imoTree.getShip(code).getMovements().getSummary(code));
            }
        } else if (csTree.isCS(code)) {
            if (csTree.find(code)) {
                System.out.print(csTree.getShip(code).getMovements().getSummary(code));
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void getTopN(Object n, Object date1, Object date2) throws IOException {
        if ((int) n >= mmsiTree.size()) {
            throw new UnsupportedOperationException("Ships are not enough to fulfill requirement!");
        }
        TopN topsum = new TopN(mmsiTree);
        System.out.println(topsum.getTop(n, date1, date2));
    }

    public void pairsofShips() {
        PairsCalculator pc = new PairsCalculator(mmsiTree);
        System.out.println(pc.pairs());
    }
}
