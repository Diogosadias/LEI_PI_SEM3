package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import lapr.project.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import lapr.project.utils.PL.MatrixGraph;

public class TrafficManagerController {

    public void setMmsiTree(MMSTree mmsiTree) {
        this.mmsiTree = mmsiTree;
    }
    public PortManager pm = new PortManager();
    public PortTree portTree = pm.getPortTree();
    public MMSTree mmsiTree = new MMSTree();
    public IMOTree imoTree = new IMOTree();
    public CallSignTree csTree = new CallSignTree();
    private String s = "Input is Invalid!";
    private String s2 = "-------------------------------------------------------------------------------------------------";
    public Search search = new Search();
    private PairsCalculator pc = new PairsCalculator(mmsiTree);
    public TopN topsum = new TopN(mmsiTree);
    private ImportPortDatabase importPortDatabase =  new ImportPortDatabase();
    public DataBaseImport dataBaseImport = new DataBaseImport(importPortDatabase);
   


    /*
    Mais tarde criar classe Software/APP para armazenar tudo o que é importante
     */
    public TrafficManagerController() throws IOException {
        //Only Initiated
    }

    public void importFile(String filename) throws IOException {

        Import importer = new Import();
        List<ShipTree> map = importer.readLine(filename, mmsiTree, imoTree, csTree);
        MMSTree mmsiTree = (MMSTree) map.get(0);
        IMOTree imoTree = (IMOTree) map.get(1);
        CallSignTree csTree = (CallSignTree) map.get(2);

        this.mmsiTree = mmsiTree;
        this.imoTree = imoTree;
        this.csTree = csTree;

    }

    public void searchDetails(Object code) throws IOException {
        System.out.println(search.searchDetails(code, this));
    }

    public void searchDate(Object code, Object date) throws IOException {
        System.out.println(search.searchDate(code, date, this));
    }

    public void searchDate(Object code, Object date1, Object date2) throws IOException {
        System.out.println(search.searchDate(code, date1, date2, this));
    }

    public void summary(Object code) throws IOException {
        System.out.println(search.summary(code, this));
    }

    public void getTopN(Object n, String date1, String date2) throws IOException {
        System.out.println(topsum.getTopNString(n, date1, date2));

    }

    public void pairsofShips() {
        System.out.println(pc.pairs());
    }

    public File shipAvailableMonday(DatabaseConnection databaseConnection, String date1) throws IOException {
        File myObj = new File("Ship Next Monday.txt");
        try (FileWriter myWriter = new FileWriter("Ship Next Monday.txt")) {
            myWriter.write(search.nextMonday(databaseConnection,date1));
        }


        return  myObj;
    }


    

    public File closestPort(DatabaseConnection databaseConnection, String code3, String date) throws IOException {
        File myObj = new File("ClosestPort.txt");
        try (FileWriter myWriter = new FileWriter("ClosestPort.txt")) {
            myWriter.write(search.getClosestPort(databaseConnection,code3, date,this));
        }


        return  myObj;
    }

    public File buildFreight(DatabaseConnection databaseConnection, int n) throws IOException {
        File myObj = new File("FreightNetwork.txt");
        try (FileWriter myWriter = new FileWriter("FreightNetwork.txt")) {
            myWriter.write(dataBaseImport.buildFreight(databaseConnection,n));
        }


        return  myObj;
    }

    public File colorTheMap() throws IOException {
        File myObj = new File("MapRepresentation.txt");
        try (FileWriter myWriter = new FileWriter("MapRepresentation.txt")) {
            myWriter.write(dataBaseImport.colorMap(dataBaseImport.getMatrixGraph()));
        }


        return  myObj;
    }


    public File selectNPlaces(int n) throws IOException {
        File myObj = new File("NPlaces.txt");
        FileWriter myWriter = new FileWriter("NPlaces.txt");
        myWriter.write(search.selectNPlaces(n, dataBaseImport.getMatrixGraph()));
        myWriter.close();


        return  myObj;
    }


    public File criticalPorts(int n) throws IOException {
        File myObj = new File("GreaterCentrality.txt");
        FileWriter myWriter = new FileWriter("GreaterCentrality.txt");
        myWriter.write(search.greaterCentrality(n, dataBaseImport.getMatrixGraph()));
        myWriter.close();


        return  myObj;
    }

    public File findCircuit() throws IOException {
        File myObj = new File("Circuit.txt");
        FileWriter myWriter = new FileWriter("Circuit.txt");
        myWriter.write(search.findCiruit(dataBaseImport.getMatrixGraph()));
        myWriter.close();


        return  myObj;
    }
        public File getMaritimePath(Object port1, Object port2) throws IOException {

        port1 = dataBaseImport.getMatrixGraph().vertex(79);
        port2 = dataBaseImport.getMatrixGraph().vertex(70);



        File myObj = new File("MaritimePath.txt");
        FileWriter myWriter = new FileWriter("MaritimePath.txt");
        myWriter.write(search.getMaritimePath(port1, port2, dataBaseImport.getMatrixGraph()));
        myWriter.close();

        return myObj;
    }

    public File getLandPath(Object land1, Object land2) throws IOException {
        land1 = dataBaseImport.getMatrixGraph().vertex(3);
        land2 = dataBaseImport.getMatrixGraph().vertex(9);

        File myObj = new File("LandPath.txt");
        FileWriter myWriter = new FileWriter("LandPath.txt");
        myWriter.write(search.getLandPath(land1, land2, dataBaseImport.getMatrixGraph()));
        myWriter.close();

        return myObj;
    }

    public File getLandOrSeaPath(Object place1, Object place2) throws IOException {
        place1 = dataBaseImport.getMatrixGraph().vertex(3);
        place2 = dataBaseImport.getMatrixGraph().vertex(79);



        File myObj = new File("LandOrSeaPath.txt");
        FileWriter myWriter = new FileWriter("LandOrSeaPath.txt");
        myWriter.write(search.getLandOrSeaPath(place1, place2, dataBaseImport.getMatrixGraph()));
        myWriter.close();

        return myObj;
    }

    public void setMatrix(Object o) {
    }
}
