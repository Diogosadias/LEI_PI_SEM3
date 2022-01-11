package lapr.project.model;

import lapr.project.controller.TrafficManagerController;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import lapr.project.data.ShipDatabase;

import oracle.ucp.util.Pair;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Double.NaN;
import static lapr.project.model.TemporalMessages.getDate;

import lapr.project.utils.PL.Edge;
import lapr.project.utils.PL.MatrixGraph;

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

    public String getClosestPort(DatabaseConnection databaseConnection, Object code3, Object date1, TrafficManagerController main) throws IOException {

        PortTree portTree = new PortTree();
        ImportPortDatabase importPortDatabase = new ImportPortDatabase();

        Connection connection = databaseConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            if (!importPortDatabase.getPortData(databaseConnection, portTree)) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Ports Retrieved From Database!");


        }catch(SQLException ex){
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PortTree.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        if (code3 == null) {
            throw new IOException(s);
        }

        double a[] = new double[2];


        if (main.csTree.isCS(code3)) {
            if (main.csTree.find(code3)) {
                a = main.csTree.getShip(code3).getCoordsbyBaseDateTime(date1);
                return portTree.findNearesNeighbour(a[0], a[1]).toString() + "\n" + s2;
            }
        } else {
            return "Ship Code was not according regulations!" + "\n" + s2;

        }

        return s2;
    }

    public String nextMonday(DatabaseConnection databaseConnection, String date1) throws IOException {
        List<Ship> ship = null;
        if(date1==null) return "Date is not Valid!";
        ShipDatabase shipDatabase = new ShipDatabase();

        ship = shipDatabase.getNextMonday(databaseConnection,date1);


        if(ship==null) return "Ship was not Found!";

        String print = "The ships available Next Monday: \n";

        for(Ship c : ship){
            print = print +"\n" + c.toStringwPosition();
        }

        return print + "|\n" +s2;
    }


//retornar os n locais mais próximos por continente, criando uma sub-matriz para cada continente

    public String selectNPlaces(int n, MatrixGraph matrixGraph) {
        if(n<1 ) return "Invalid N Value!";

        LinkedList<Pair<Object,Double>> list = null;

        String print = "";
        List <String> continentList = matrixGraph.getContinents();
        for(String s : continentList){
            MatrixGraph matrixGraphCont = matrixGraph.clone();
            matrixGraphCont.operatechanges(s);

            //Floyd-Warshall Matrix - Create

            Double[][] floydMatrix = matrixGraphCont.floydMatrix();

            //get the n locals
            list = nClosestPlaces(matrixGraphCont,floydMatrix,n);


            if(list.isEmpty() || list.get(0).get2nd()==0.0){
                print = print + "\n" +
                        "For the Continent : " + s + "\n" +
                        "There are no Connections to Calculate" + "\n" +
                        "-----------------";
            } else {
                //print List
                print = print + "\n" +
                        "For the Continent : " + s + "\n" +
                        printL(list) + "\n" +
                        "-----------------";
            }

        }

        return print;
    }

    public static LinkedList<Pair<Object,Double>> nClosestPlaces(MatrixGraph matrixGraph,Double[][] floydMatrix, int n) {
        LinkedList<Pair<Object,Double>> list = new LinkedList<>();

        int counter = 0;
        for(Object v : matrixGraph.vertices()){
            list.add(new Pair<Object,Double>(v,getShortAverage(floydMatrix,matrixGraph.key(v))));
            counter++;
            if(counter>=n) break;
        }


        for(int i = 0; i< floydMatrix.length;i++){
                Double value = getShortAverage(floydMatrix,i);
                for(Pair<Object, Double> p : list){
                    int index = list.indexOf(p);
                    if(value!=0.0 && value<p.get2nd() && !p.get1st().equals(matrixGraph.vertex(i))) {
                        list.remove(n-1);
                        list.add(index,new Pair<>(matrixGraph.vertex(i),value));
                        break;
                    }
                }
        }

        if(list.get(0).get2nd().isNaN()) list = new LinkedList<>();

        return list;
    }

    public static Double getShortAverage(Double[][] floydMatrix, int vertex) {
        Double value = 0.0;
        int notNull = 0;
        for(int i = 0;i<floydMatrix.length;i++){
            if(floydMatrix[vertex][i]!=null) {
                value = value + floydMatrix[vertex][i];
                notNull++;
            }
        }
        if(notNull==0) return 0.0;
        return value/ notNull;
    }

    public static String printL(LinkedList<Pair<Object, Double>> list) {
        String print ="";
        for(Pair v : list){
            if( v.get1st() instanceof City){
                print = print + "\n" +
                        ((City) v.get1st()).getName() + ", " + ((City) v.get1st()).getCountry() + " - average distance  - " + v.get2nd().toString() ;
            }
            if( v.get1st() instanceof Port){
                print = print + "\n" +
                        ((Port) v.get1st()).getLocation() + ", " + ((Port) v.get1st()).getCountry() + " - average distance  - " + v.get2nd().toString() ;
            }

        }
        return print;
    }


}
