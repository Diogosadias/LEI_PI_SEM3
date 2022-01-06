package lapr.project.model;

import oracle.ucp.util.Pair;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import lapr.project.utils.PL.MatrixGraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DataBaseImport {
    ImportPortDatabase importPortDatabase ;
    private MatrixGraph matrixGraph ;


    public DataBaseImport(ImportPortDatabase importPortDatabase){
        this.importPortDatabase = importPortDatabase;
    }

    public String buildFreight(DatabaseConnection databaseConnection,int n) throws IOException {

        if(databaseConnection==null) return "Freight Build Was Not Successful!";

        //get Ports List and Cities List from Database
        PortTree portTree = new PortTree();
        if(!importPortDatabase.getPortData(databaseConnection,portTree)) return "Ports Couldn't be Retrieved";
        List<Port> listPorts = (List<Port>) portTree.inOrder();

        List<City> listCity = new ArrayList<>();
        if(!importPortDatabase.getCities(databaseConnection,listCity)) return "Cities Couldn't be Retrieved";

        // Get Borders from Database Format -> TreeMap<String,List<String>>
        TreeMap<String,List<String>> borders = new TreeMap<>();
        if(!importPortDatabase.getBorders(databaseConnection,borders)) return "Borders Couldn't be Retrieved";

        //Get SeaDist from Database Format -> TreeMap<String,List<Pair<String,Double>>
        TreeMap<String,List<Pair<String,Double>>> seadist = new TreeMap<>();
        if(!importPortDatabase.getSeaDist(databaseConnection,seadist)) return "Sea Distances Couldn't be Retrieved";

        //Build Graph with all information
        buildGraph(listPorts,listCity,borders,seadist,n);


        if(matrixGraph.vertices().size()==0) return "Database has no data!";

        return matrixGraph.toString() + "\n\nGraph was Built";
    }

    public MatrixGraph buildGraph(List<Port> listPorts, List<City> listCity, TreeMap<String, List<String>> borders, TreeMap<String, List<Pair<String, Double>>> seadist,int n) throws IOException {
        MatrixGraph matrixGraph = new MatrixGraph(false); //Undirected Graph

        //Add Vertices
        matrixGraph.addVertices(listPorts,listCity);

        //Add Edges in Order Borders -> Capital Port -> Country Ports -> N Ports
        matrixGraph.addBorders(borders);
        matrixGraph.capitalPort();
        matrixGraph.portsConnection(seadist);
        matrixGraph.nportsConnect(n,seadist);

        this.matrixGraph =matrixGraph;

        return matrixGraph;
    }

    public MatrixGraph getMatrixGraph(){
        return matrixGraph;
    }

    public String colorMap(MatrixGraph matrixGraph) {

        //Create list of Countries and Their Collors
        
        return null;
    }
}
