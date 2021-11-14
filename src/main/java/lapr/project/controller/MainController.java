package lapr.project.controller;

import lapr.project.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static lapr.project.model.TemporalMessages.getDate;

public class MainController {

    public void setMmsiTree(MMSTree mmsiTree) {
        this.mmsiTree = mmsiTree;
    }



    private MMSTree mmsiTree = new MMSTree();
    private IMOTree imoTree = new IMOTree();
    private CallSignTree csTree = new CallSignTree();

    /*
    Mais tarde criar classe Software/APP para armazenar tudo o que é importante
     */
    public MainController() {
    }

    public void importFile(String filename) throws IOException {

        Import importer = new Import();
        List<ShipTree> map = importer.readLine(filename,mmsiTree,imoTree,csTree);
        MMSTree mmsiTree= (MMSTree) map.get(0);
        IMOTree imoTree= (IMOTree) map.get(1);
        CallSignTree csTree= (CallSignTree) map.get(2);

        this.mmsiTree=mmsiTree;
        this.imoTree=imoTree;
        this.csTree=csTree;

    }

    public void searchDetails(Object code) {
        if (mmsiTree.isMMSI(code)) {
            if (mmsiTree.find(code)) {
                System.out.println(mmsiTree.getShip(code).toString());
            }
        } else if (imoTree.isISO(code)) {
            if (imoTree.find(code)) {
                System.out.println(imoTree.getShip(code).toString());
            }
        } else if (csTree.isCS(code)) {
            if (csTree.find(code)) {
                System.out.println(csTree.getShip(code).toString());
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void searchDate(Object code, Object date) {
        if (mmsiTree.isMMSI(code)) {
            if (mmsiTree.find(code)) {
                System.out.println(mmsiTree.getShip(code).getMovements().getMoveByDate(date));
            }
        } else if (imoTree.isISO(code)) {
            if (imoTree.find(code)) {
                System.out.println(imoTree.getShip(code).getMovements().getMoveByDate(date));
            }
        } else if (csTree.isCS(code)) {
            if (csTree.find(code)) {
                System.out.println(csTree.getShip(code).getMovements().getMoveByDate(date));
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void searchDate(Object code, Object date1, Object date2) throws IOException {
        if (mmsiTree.isMMSI(code)) {
            if (mmsiTree.find(code)) {
                System.out.println(mmsiTree.getShip(code).getMovements().searchDateFrame(date1, date2));
            }
        } else if (imoTree.isISO(code)) {
            if (imoTree.find(code)) {
                System.out.println(imoTree.getShip(code).getMovements().searchDateFrame(date1, date2));
            }
        } else if (csTree.isCS(code)) {
            if (csTree.find(code)) {
                System.out.println(csTree.getShip(code).getMovements().searchDateFrame(date1, date2));
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void summary(Object code) {
        if (mmsiTree.isMMSI(code)) {
            if (mmsiTree.find(code)) {
                System.out.println(mmsiTree.getShip(code).getSummary(code));
            }
        } else if (imoTree.isISO(code)) {
            if (imoTree.find(code)) {
                System.out.println(imoTree.getShip(code).getSummary(code));
            }
        } else if (csTree.isCS(code)) {
            if (csTree.find(code)) {
                System.out.println(csTree.getShip(code).getSummary(code));
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
    }

    public void getTopN(Object n, String date1, String date2) throws IOException {
        if ((int) n >= mmsiTree.size()) {
            throw new UnsupportedOperationException("Ships are not enough to fulfill requirement!");
        }
        TopN topsum = new TopN(mmsiTree);
        System.out.println(topsum.getTop(n,  getDate(date1),  getDate(date2)));
    }

    public void pairsofShips() {
        PairsCalculator pc = new PairsCalculator(mmsiTree);
        System.out.println(pc.pairs());
    }
}
