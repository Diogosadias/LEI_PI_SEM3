package lapr.project.model;

import lapr.project.controller.MainController;

import java.io.IOException;

public class    Search {


    private String s = "Input is Invalid!";
    private String s2 ="-------------------------------------------------------------------------------------------------";

    public Search(){
        //only Initiated
    }

    public String searchDetails(Object code, MainController main) throws IOException {
        if (code == null) { throw new IOException(s);}
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).toString();
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).toString();
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).toString();
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
        return s2;
    }

    public String searchDate(Object code, Object date, MainController main) throws IOException {
        if (code == null||date==null) { throw new IOException(s);}
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).getMovements().getMoveByDate(date);
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).getMovements().getMoveByDate(date);
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).getMovements().getMoveByDate(date);
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
        return s2;
    }

    public String searchDate(Object code, Object date1, Object date2, MainController main) throws IOException {
        if (code == null||date1==null||date2==null) { throw new IOException(s);}
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).getMovements().searchDateFrame(date1, date2);
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).getMovements().searchDateFrame(date1, date2);
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).getMovements().searchDateFrame(date1, date2);
            }
        } else {
            System.out.println("Ship Code was not according regulations!");
        }
        return s2;
    }
}
