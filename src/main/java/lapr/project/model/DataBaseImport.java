package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.utils.PL.MatrixGraph;

public class DataBaseImport {


    public DataBaseImport(){
        //only Initiate
    }

    public String buildFreight(DatabaseConnection databaseConnection) {

        if(databaseConnection==null) return "Freight Build Was Not Successful!";
        //get Ports List and Cities List from Database

        // Get Borders from Database Format -> TreeMap<String,List<String>>

        //Get SeaDist from Database Format -> TreeMap<String,List<Pair<String,Double>>

        //Build Graph with all information
        MatrixGraph matrixGraph = buildGraph();


        return "Graph was built!";
    }

    private MatrixGraph buildGraph() {
        return null;
    }
}
