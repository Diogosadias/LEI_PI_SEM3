package lapr.project.model;

import lapr.project.controller.TrafficManagerController;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import lapr.project.data.ShipDatabase;

import lapr.project.utils.PL.Algorithms;
import lapr.project.utils.PL.Graph;
import oracle.ucp.util.Pair;


import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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


    public String greaterCentrality(int n, MatrixGraph matrixGraph) {
        ArrayList<LinkedList<Object>> list = new ArrayList<>();
        ArrayList<Port> ports = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();

        applyDijktra(list,matrixGraph);


        countTimes(list,ports,numbers);

        return printCentral(ports,numbers,n);

    }

    private String printCentral(ArrayList<Port> ports, ArrayList<Integer> numbers, int n) {
        String print ="";
        for(int i = 0; i<n;i++){
            int index = 0;
            int max = 0;
            for(Object v : ports){
                if(numbers.get(ports.indexOf(v))>max){
                    max = numbers.get(ports.indexOf(v));
                    index = ports.indexOf(v);
                }
            }
            print = print + ports.get(index).toString() + "\n" + "This port is Critical and is traversed in " + max + " paths.\n-----\n";
            ports.remove(index);
            numbers.remove(index);
        }
        return print;
    }

    private void countTimes(ArrayList<LinkedList<Object>> list, ArrayList<Port> ports, ArrayList<Integer> numbers) {
        for(LinkedList l : list){
            if( l!=null) {
                for (Object v : l) {
                    if (v instanceof Port) {
                        if (ports.contains(v)) {
                            numbers.set(ports.indexOf(v), numbers.get(ports.indexOf(v)) + 1);
                        } else {
                            ports.add((Port) v);
                            numbers.add(1);
                        }
                    }
                }
            }

        }
    }


    public void applyDijktra(ArrayList<LinkedList<Object>> list, MatrixGraph matrixGraph) {
        for(Object v : matrixGraph.vertices()){
            //Dijktra
            ArrayList<LinkedList<Object>> paths = new ArrayList<>();
            ArrayList<Double> dist = new ArrayList<>();


            Algorithms.shortestPaths((Graph<Object, Double>) matrixGraph,  v,Edge::compare,Edge::apply,0.0,paths,dist);

            //add shortest path to list
            list.addAll(paths);

        }
    }

    public String findCiruit(MatrixGraph matrixGraph) {
        ArrayList<Object> circuit = new ArrayList<>();

        for(Object v : matrixGraph.vertices()) {
            ArrayList<Object> list = new ArrayList<>();
            int[] color = new int[matrixGraph.vertices().size()];
            color[matrixGraph.key(v)] = 1;
            list.add(v);


            Algorithms.findCircuit(v, matrixGraph, list, color);
            if(circuit.isEmpty()) circuit=list;
            else{
                if(circuit.size()<list.size()) circuit=list;
                else if(circuit.size()==list.size()) circuit = checkGreater(circuit,list,matrixGraph);
            }
        }


        return printCircuit(circuit,matrixGraph);
    }

    /**
     * Returns the circuit with min dist
     * @param circuit
     * @param list
     * @param matrixGraph
     * @return
     */
    private ArrayList<Object> checkGreater(ArrayList<Object> circuit, ArrayList<Object> list, MatrixGraph matrixGraph) {
        Double circuitD = 0.0;
        Double listD = 0.0;

        for(int i = 0;i<circuit.size()-1;i++){
            circuitD = circuitD + (Double) matrixGraph.edge(circuit.get(i),circuit.get(i+1)).getWeight();
            listD = listD + (Double) matrixGraph.edge(list.get(i),list.get(i+1)).getWeight();
        }
        if(circuitD>listD) return list;
        else return circuit;
    }

    private String printCircuit(ArrayList<Object> list,MatrixGraph matrixGraph) {
        String print = "The Most Efficient Circuit of the Graph is:\n";

        if(list.get(0) instanceof Port){
            print = print + ((Port) list.get(0)).getLocation();
        } else if(list.get(0) instanceof City){
            print = print + ((City) list.get(0)).getName();
        } else{
            return "There is No Circuit!";
        }
        double dist = 0.0;

        for(int i = 0; i<list.size()-1;i++){
            Double weigh = (Double) matrixGraph.edge(list.get(i),list.get(i+1)).getWeight();
            if(list.get(i+1) instanceof Port){
                print = print +" -> |" + weigh +"| -> " + ((Port) list.get(i+1)).getLocation() + "\n";

            } else if(list.get(i+1) instanceof City) {
                print = print +" -> |" + weigh +"| -> " + ((City) list.get(i+1)).getName() + "\n";
            }
            if(i!=list.size()-2 & list.get(i+1) instanceof Port) {
                print = print + ((Port) list.get(i+1)).getLocation();
            } else if(i!=list.size()-2 & list.get(i+1) instanceof City){
                print = print + ((City) list.get(i+1)).getName();

            }
            dist = dist + weigh;
        }
        return print+"\n" + "The total distance traveled is " + dist + ".\nThe Circuit goes through " + String.valueOf(list.size()-1) +" locations.";
    }
    
    //locais e distancias
    public String getMaritimePath(Object port1, Object port2, MatrixGraph matrixGraph) {

        if (port1 instanceof Port && port2 instanceof Port) {
            if (matrixGraph.validVertex(port1) && matrixGraph.validVertex(port2)) {
                LinkedList<Object> shortPath = new LinkedList<>();

                double dist;
                if ((Algorithms.shortestPath((Graph<Object, Double>) matrixGraph, port1, port2, Edge::compare, Edge::apply, 0.0, shortPath)) == null) {
                    return "Between the ports " + port1 + " and " + port2 + " there isn't a possible path!\n";

                }
                dist = Algorithms.shortestPath((Graph<Object, Double>) matrixGraph, port1, port2, Edge::compare, Edge::apply, 0.0, shortPath);
                return "The shortest path between the departure port " + port1 + " and the arrival port " + port2 + " is: " + dist + ".\n";

            } else {
                return "At least one of the ports " + port1 + " or " + port2 + " was not in the graph!\n";
            }
        } else {
            return "The departure port" + port1 + " or the arrival port " + port2 + " are not a port!\n";
        }

    }

    public String getLandPath(Object land1, Object land2, MatrixGraph matrixGraph) {

        if ((land1 instanceof Port && land2 instanceof City) || (land1 instanceof City && land2 instanceof Port) || (land1 instanceof City && land2 instanceof City)) {
            if (matrixGraph.validVertex(land1) && matrixGraph.validVertex(land2)) {
                LinkedList<Object> shortPath = new LinkedList<>();

                double dist;
                if ((Algorithms.shortestPath((Graph<Object, Double>) matrixGraph, land1, land2, Edge::compare, Edge::apply, 0.0, shortPath)) == null) {
                    return "Between the places " + land1 + " and " + land2 + " there isn't a possible path!\n";

                }
                dist = Algorithms.shortestPath((Graph<Object, Double>) matrixGraph, land1, land2, Edge::compare, Edge::apply, 0.0, shortPath);
                return "The shortest path between the departure place " + land1 + " and the arrival place " + land2 + " is: " + dist + ".\n";

            } else {
                return "At least one of the places " + land1 + " or " + land2 + " was not in the graph!\n";
            }
        } else {
            return "The departure place" + land1 + " or the arrival place " + land2 + " didn't meet the requirements\n";
        }
    }

    public String getLandOrSeaPath(Object place1, Object place2, MatrixGraph matrixGraph) {

        if (matrixGraph.validVertex(place1) && matrixGraph.validVertex(place2)) {
            LinkedList<Object> shortPath = new LinkedList<>();

            double dist;
            if ((Algorithms.shortestPath((Graph<Object, Double>) matrixGraph, place1, place2, Edge::compare, Edge::apply, 0.0, shortPath)) == null) {
                return "Between the places " + place1 + " and " + place2 + " there isn't a possible path!\n";

            }
            dist = Algorithms.shortestPath((Graph<Object, Double>) matrixGraph, place1, place2, Edge::compare, Edge::apply, 0.0, shortPath);
            return "The shortest path between the departure place " + place1 + " and the arrival place " + place2 + " is: " + dist + ".\n";

        } else {
            return "At least one of the places " + place1 + " or " + place2 + " was not in the graph!\n";
        }
    }
}
