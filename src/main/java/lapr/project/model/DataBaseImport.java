package lapr.project.model;

import lapr.project.data.DatabaseConnection;
import lapr.project.utils.PL.MatrixGraph;

public class DataBaseImport {


    public DataBaseImport(){
        //only Initiate
    }

    public String buildFreight(DatabaseConnection databaseConnection) {

        if(databaseConnection==null) return "Freight Build Was Not Successful!";
        //get Ports List and Cities List

        // Get Borders

        //Get SeaDist

        //Build Graph
        MatrixGraph matrixGraph = buildGraph();


        return "Graph was built!";
    }

    private MatrixGraph buildGraph() {
        return null;
    }
}
