package lapr.project.model;

import lapr.project.controller.TrafficManagerController;

import java.io.IOException;

public class Search {

    private String s = "Input is Invalid!";
    private String s2 = "-------------------------------------------------------------------------------------------------";

    public Search() {
        //only Initiated
    }

    public String searchDetails(Object code, TrafficManagerController main) throws IOException {
        if (code == null) {
            throw new IOException(s);
        }
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).toString() + "\n" + s2;
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).toString() + "\n" + s2;
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).toString() + "\n" + s2;
            } else {
                return "Ship Code was not according regulations!" + "\n" + s2;
            }
        }
        return s2;
    }

    public String searchDate(Object code, Object date, TrafficManagerController main) throws IOException {
        if (code == null || date == null) {
            throw new IOException(s);
        }
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).getMovements().getMoveByDate(date) + "\n" + s2;
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).getMovements().getMoveByDate(date) + "\n" + s2;
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).getMovements().getMoveByDate(date) + "\n" + s2;
            } else {
                return "Ship Code was not according regulations!" + "\n" + s2;
            }
        }
        return s2;
    }

    public String searchDate(Object code, Object date1, Object date2, TrafficManagerController main) throws IOException {
        if (code == null || date1 == null || date2 == null) {
            throw new IOException(s);
        }
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).getMovements().searchDateFrame(date1, date2) + "\n" + s2;
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).getMovements().searchDateFrame(date1, date2) + "\n" + s2;
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).getMovements().searchDateFrame(date1, date2) + "\n" + s2;
            } else {
                return "Ship Code was not according regulations!" + "\n" + s2;
            }
        }
        return s2;
    }

    public String summary(Object code, TrafficManagerController main) throws IOException {
        if (code == null) {
            throw new IOException(s);
        }
        if (main.mmsiTree.isMMSI(code)) {
            if (main.mmsiTree.find(code)) {
                return main.mmsiTree.getShip(code).getSummary(code) + "\n" + s2;
            }
        } else if (main.imoTree.isISO(code)) {
            if (main.imoTree.find(code)) {
                return main.imoTree.getShip(code).getSummary(code) + "\n" + s2;
            }
        } else if (main.csTree.isCS(code)) {
            if (main.csTree.find(code)) {
                return main.csTree.getShip(code).getSummary(code) + "\n" + s2;
            } else {
                return "Ship Code was not according regulations!" + "\n" + s2;
            }
        }
        return s2;
    }

    public String getClosestPort(Object code3, Object date1, TrafficManagerController main, PortTree portTree) throws IOException {
        if (code3 == null) {
            throw new IOException(s);
        }

        double a[] = new double[2];

        if (main.csTree.isCS(code3)) {
            if (main.csTree.find(code3)) {
                a = main.csTree.getShip(code3).getCoordsbyBaseDateTime(date1);
                return main.portTree.findNearesNeighbour(a[0], a[1]) + "\n" + s2;
            }
        } else {
            return "Ship Code was not according regulations!" + "\n" + s2;

        }

        return s2;
    }
}
