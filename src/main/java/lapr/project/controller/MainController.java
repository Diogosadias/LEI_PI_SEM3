package lapr.project.controller;

import lapr.project.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
        List<Map> map = importer.readLine(filename,mmsiTree,imoTree,csTree);
        Map shipMap=map.get(0);
        Map shipMap1=map.get(1);
        Map shipMap2=map.get(2);

        int i =0;
        while (!(i == shipMap.size())) {

            mmsiTree.insert((Ship) shipMap.values().iterator().next());
            i++;
            imoTree.insert((Ship) shipMap1.values().iterator().next());
            i++;
            csTree.insert((Ship) shipMap2.values().iterator().next());
            i++;

        }
        this.mmsiTree=mmsiTree;
        this.imoTree=imoTree;
        this.csTree=csTree;

        System.out.println("Acabou");
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
                System.out.print(mmsiTree.getShip(code).getSummary(code));
            }
        } else if (imoTree.isISO(code)) {
            if (imoTree.find(code)) {
                System.out.print(imoTree.getShip(code).getSummary(code));
            }
        } else if (csTree.isCS(code)) {
            if (csTree.find(code)) {
                System.out.print(csTree.getShip(code).getSummary(code));
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
