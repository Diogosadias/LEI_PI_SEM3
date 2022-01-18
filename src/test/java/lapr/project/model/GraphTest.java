package lapr.project.model;

import lapr.project.data.ImportPortDatabase;
import oracle.ucp.util.Pair;
import lapr.project.utils.PL.MatrixGraph;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    private String[] listAux = {"Lisboa","Paris","Madrid","London","Roma","Berlin"} ;
    private String[] listCont = {"Lisboa","Paris","Madrid","London","Roma","Berlin"} ;
    private String[] listAux2 = {"Portugal","France","Spain","UK","Italy","Germany"} ;
    private String[] listPorts = {"Lisboa","Paris","Madrid","London","Roma","Berlin"};
    MatrixGraph instance;





    /**
     * Check if all vertices are added
     */
    @Test
    public void testVertices() throws IOException {
        List<City> cityList = new ArrayList<>();
        List<Port> portList = new ArrayList<>();

        MatrixGraph matrixGraph = new MatrixGraph(false);
        matrixGraph.addVertices(portList,  cityList);
        assertTrue(matrixGraph.vertices().size()==0);

        City p =new City("Porto","Portugal");
        cityList.add(p);
        cityList.add(new City("Lisboa","Portugal"));
        portList.add(new Port("Europe","Portugal",12,"Porto",0.1,0.2));
        matrixGraph.addVertices(portList,  cityList);
        assertTrue(matrixGraph.vertices().size()==3);
        assertEquals(matrixGraph.vertex(0),p);
        assertFalse(matrixGraph.addVertices(null,  null));
        assertFalse(matrixGraph.addVertices(portList,  null));
        assertFalse(matrixGraph.addVertices(null,  cityList));

        City v =new City("Viseu","Portugal",new Point2D.Double(0.1,0.5));
        assertTrue(matrixGraph.addVertex(v));



    }

    /**
     * Check if all border are added
     */
    @Test
    public void testBorders() throws IOException {

        List<City> cityList = new ArrayList<>();
        List<Port> portList = new ArrayList<>();

        City p =new City("Paris","França",new Point2D.Double(1,1));
        cityList.add(p);
        City l =new City("Lisboa","Portugal",new Point2D.Double(2,2));
        cityList.add(l);

        MatrixGraph matrixGraph = new MatrixGraph(false);
        matrixGraph.addVertices(portList,  cityList);

        City m =new City("Madrid","Spain",new Point2D.Double(0,0));
        matrixGraph.addVertex(m );
        assertEquals(matrixGraph.vertices().size(),3);

        TreeMap<String,List<String>> treeMap = new TreeMap<>();
        List<String> list = new ArrayList<>();
        list.add("Portugal");
        list.add("França");
        list.add("Andorra");
        Port port = new Port("Europe","Portugal",12,"Porto",0.1,0.2);
        matrixGraph.addVertex(port);

        treeMap.put("Spain",list);
        assertTrue(matrixGraph.addBorders(treeMap));
        assertEquals(matrixGraph.edge(l,m).getWeight(),314.4748051008686);
        assertEquals(matrixGraph.edge(m,l).getWeight(),314.4748051008686);
        assertEquals(matrixGraph.edge(p,m).getWeight(),157.24938127194397);
        assertNull(matrixGraph.edge(p,l));
        assertNull(matrixGraph.edge(l,l));
        assertNull(matrixGraph.edge(port,l));
    }

    /**
     * Check if all Capital Ports are added
     */
    @Test
    public void testCapitalPort() throws IOException {


        List<City> cityList = new ArrayList<>();
        List<Port> portList = new ArrayList<>();

        City p =new City("Paris","França",new Point2D.Double(1,1));
        cityList.add(p);
        City l =new City("Lisboa","Portugal",new Point2D.Double(2,2));
        cityList.add(l);

        MatrixGraph matrixGraph = new MatrixGraph(false);
        matrixGraph.addVertices(portList,  cityList);

        City m =new City("Madrid","Spain",new Point2D.Double(0,0));
        matrixGraph.addVertex(m );


        TreeMap<String,List<String>> treeMap = new TreeMap<>();
        List<String> list = new ArrayList<>();
        list.add("Portugal");
        list.add("França");
        list.add("Andorra");
        Port port = new Port("Europe","Portugal",12,"Porto",0.1,0.2);
        matrixGraph.addVertex(port);
        matrixGraph.addBorders(treeMap);


        assertTrue(matrixGraph.capitalPort());
        assertEquals(matrixGraph.edge(port,l).getWeight(),290.9955464123579);

        MatrixGraph matrixGraph1 =matrixGraph.clone();
        matrixGraph1.removeEdge(port,l);
        assertNull(matrixGraph1.edge(port,l));
        Port lisbPort = new Port("Europe","Portugal",15,"Lisboa",2.1,2.2);

        matrixGraph1.addVertex(lisbPort);

        assertTrue(matrixGraph1.capitalPort());
        assertNull(matrixGraph1.edge(port,l));
        assertEquals(matrixGraph1.edge(lisbPort,l).getWeight(),24.85120922437356);

    }

    /**
     * Check if all Ports of Same countries Connections are added
     */
    @Test
    public void testPortsConnection() throws IOException {

        //Result should be same but need redo to Floyd Arwshal

        List<City> cityList = new ArrayList<>();
        List<Port> portList = new ArrayList<>();

        City p =new City("Paris","França",new Point2D.Double(1,1));
        cityList.add(p);
        City l =new City("Lisboa","Portugal",new Point2D.Double(2,2));
        cityList.add(l);

        MatrixGraph matrixGraph = new MatrixGraph(false);
        matrixGraph.addVertices(portList,  cityList);

        City m =new City("Madrid","Spain",new Point2D.Double(0,0));
        matrixGraph.addVertex(m );


        TreeMap<String,List<String>> treeMap = new TreeMap<>();
        List<String> list = new ArrayList<>();
        list.add("Portugal");
        list.add("França");
        list.add("Andorra");
        Port port = new Port("Europe","Portugal",12,"Porto",0.1,0.2);
        matrixGraph.addVertex(port);

        Port lisbPort = new Port("Europe","Portugal",15,"Lisboa",2.1,2.2);

        matrixGraph.addVertex(lisbPort);
        matrixGraph.addBorders(treeMap);
        matrixGraph.capitalPort();

        assertEquals(matrixGraph.edge(lisbPort,l).getWeight(),24.85120922437356);

        Port BarcelonaPort = new Port("Europe","Spain",12,"Barcelona",0.1,0.3);


        TreeMap<String, List<Pair<String,Double>>> treeMap1 = new TreeMap<>();
        List<Pair<String,Double>> listPort1 = new ArrayList<>();
        Pair<String,Double> p1 = new Pair<String,Double>("Barcelona",2.0);
        Pair<String,Double> p2 = new Pair<String,Double>("Lisboa",5.0);

        listPort1.add(p1);
        listPort1.add(p2);
        treeMap1.put("Porto",listPort1);


        assertTrue(matrixGraph.portsConnection(treeMap1));
        assertEquals(matrixGraph.edge(port,lisbPort).getWeight(),5.0);
        assertNull(matrixGraph.edge(BarcelonaPort,port));


    }

    /**
     * Check if n Ports are added
     */
    @Test
    public void testNPorts() throws IOException {


        List<City> cityList = new ArrayList<>();
        List<Port> portList = new ArrayList<>();

        City p =new City("Paris","França",new Point2D.Double(1,1));
        cityList.add(p);
        City l =new City("Lisboa","Portugal",new Point2D.Double(2,2));
        cityList.add(l);

        MatrixGraph matrixGraph = new MatrixGraph(false);
        matrixGraph.addVertices(portList,  cityList);

        City m =new City("Madrid","Spain",new Point2D.Double(0,0));
        matrixGraph.addVertex(m );


        TreeMap<String,List<String>> treeMap = new TreeMap<>();
        List<String> list = new ArrayList<>();
        list.add("Portugal");
        list.add("França");
        list.add("Andorra");
        Port port = new Port("Europe","Portugal",12,"Porto",0.1,0.2);
        matrixGraph.addVertex(port);

        Port lisbPort = new Port("Europe","Portugal",15,"Lisboa",2.1,2.2);

        matrixGraph.addVertex(lisbPort);
        matrixGraph.addBorders(treeMap);



        Port barcelonaPort = new Port("Europe","Spain",12,"Barcelona",0.1,0.3);
        matrixGraph.addVertex(barcelonaPort);


        matrixGraph.capitalPort();
        TreeMap<String, List<Pair<String,Double>>> treeMap1 = new TreeMap<>();
        List<Pair<String,Double>> listPort1 = new ArrayList<>();
        Pair<String,Double> p1 = new Pair<String,Double>("Barcelona",2.0);
        Pair<String,Double> p2 = new Pair<String,Double>("Lisboa",5.0);
        Pair<String,Double> p3 = new Pair<String,Double>("Porto",2.0);
        Pair<String,Double> p4 = new Pair<String,Double>("Lisboa",10.0);

        listPort1.add(p1);
        listPort1.add(p2);
        treeMap1.put("Porto",listPort1);
        List<Pair<String,Double>> listPort2 = new ArrayList<>();

        listPort2.add(p3);
        listPort2.add(p4);
        treeMap1.put("Barcelona",listPort2);

        matrixGraph.portsConnection(treeMap1);
        List<City> li = new ArrayList<>();
        li.add(m);
        assertEquals(matrixGraph.adjVertices(barcelonaPort),li);
        assertEquals(matrixGraph.adjVertices(lisbPort).size(),2);

        assertTrue(matrixGraph.nportsConnect(1,treeMap1));


        assertEquals(matrixGraph.adjVertices(barcelonaPort).size(),3);
        assertEquals(matrixGraph.adjVertices(lisbPort).size(),3);


        assertTrue(matrixGraph.nportsConnect(2,treeMap1));
        assertEquals(matrixGraph.adjVertices(barcelonaPort).size(),3);
        assertEquals(matrixGraph.adjVertices(lisbPort).size(),3);

        /*

        If we cannot add edges to vertices if that is going to increase number of vertices above n, we cant add vertices!
         */



    }

    /**
     * Test Graph Build
     */
    @Test
    public void testGraphBuild() throws IOException {
        List<Port> listPorts = new ArrayList<>();
        List<City> listCities = new ArrayList<>();
        TreeMap<String,List<String>> borders = new TreeMap<>();
        TreeMap<String,List<Pair<String,Double>>> seadist = new TreeMap<>();
        for(int i = 0; i<listAux.length;i++){
            listPorts.add(new Port("Europe",listAux2[i],i,listAux[i],Double.valueOf(i),Double.valueOf(i)));
            listCities.add(new City(listAux[i],listCont[i],new Point2D.Double(i,i) ));
        }
        for(int i = 0; i<listAux.length-1;i++){
            borders.put(listAux[i], Collections.singletonList(listAux[i + 1]));
        }
        List<String> list = borders.get(listAux[2]);
        borders.put(listAux[2],list);
        List<Pair<String,Double>> listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Berlin",20.0));
        listAu.add(new Pair<String,Double>("London",20.0));
        seadist.put("Lisboa",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Madrid",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Berlin",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("London",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Madrid",listAu);
        City c = new City("Brazil","Rio de Janeiro",new Point2D.Double(20.0,20.0));
        listCities.add(c);
        ImportPortDatabase importPortDatabase = new ImportPortDatabase();

        DataBaseImport dataBaseImport = new DataBaseImport(importPortDatabase);
        instance = dataBaseImport.buildGraph(listPorts,listCities,borders,seadist,0);

        assertFalse(instance.isDirected());
        assertTrue(instance.validVertex(c));
        //Prove that Graph has Ports and Cities
        assertTrue(instance.vertex(0) instanceof City);
        assertTrue(instance.vertex(7) instanceof Port);

        assertEquals(instance,dataBaseImport.getMatrixGraph());
        MatrixGraph i = instance.clone();
        i.removeVertex(c);
        dataBaseImport.setMatrixGraph(i);
        assertEquals(dataBaseImport.getMatrixGraph(),i);

    }

    /**
     * Test GetPathAverage
     */
    @Test
    public void testAverage() throws IOException {

        List<Port> listPorts = new ArrayList<>();
        List<City> listCities = new ArrayList<>();
        TreeMap<String,List<String>> borders = new TreeMap<>();
        TreeMap<String,List<Pair<String,Double>>> seadist = new TreeMap<>();
        for(int i = 0; i<listAux.length;i++){
            listPorts.add(new Port("Europe",listAux2[i],i,listAux[i],Double.valueOf(i),Double.valueOf(i)));
            listCities.add(new City(listAux[i],listCont[i],new Point2D.Double(i,i) ));
        }
        for(int i = 0; i<listAux.length-1;i++){
            borders.put(listAux[i], Collections.singletonList(listAux[i + 1]));
        }
        List<String> list = borders.get(listAux[2]);
        borders.put(listAux[2],list);
        List<Pair<String,Double>> listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Berlin",20.0));
        listAu.add(new Pair<String,Double>("London",20.0));
        seadist.put("Lisboa",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Madrid",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Berlin",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("London",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Madrid",listAu);
        City c = new City("Brazil","Rio de Janeiro",new Point2D.Double(20.0,20.0));
        listCities.add(c);
        ImportPortDatabase importPortDatabase = new ImportPortDatabase();

        DataBaseImport dataBaseImport = new DataBaseImport(importPortDatabase);
        instance = dataBaseImport.buildGraph(listPorts,listCities,borders,seadist,0);


        assertEquals(instance.getContinents().size(),2);
        assertEquals(instance.getContinents().get(1),"Europe");

        instance.operatechanges("Europe");
        assertFalse(instance.validVertex(c));




    }

    /**
     * Test if algorithm creates List of Shortest Path - 1 vertex
     */
    @Test
    public void testshortestPathGraph() throws IOException {
        List<Port> listPorts = new ArrayList<>();
        List<City> listCities = new ArrayList<>();
        TreeMap<String,List<String>> borders = new TreeMap<>();
        TreeMap<String,List<Pair<String,Double>>> seadist = new TreeMap<>();
        for(int i = 0; i<listAux.length;i++){
            listPorts.add(new Port("Europe",listAux2[i],i,listAux[i],Double.valueOf(i),Double.valueOf(i)));
            listCities.add(new City(listAux[i],listCont[i],new Point2D.Double(i,i) ));
        }
        for(int i = 0; i<listAux.length-1;i++){
            borders.put(listAux[i], Collections.singletonList(listAux[i + 1]));
        }
        List<String> list = borders.get(listAux[2]);
        borders.put(listAux[2],list);
        List<Pair<String,Double>> listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Berlin",20.0));
        listAu.add(new Pair<String,Double>("London",20.0));
        seadist.put("Lisboa",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Madrid",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Berlin",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("London",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Madrid",listAu);
        City c = new City("Brazil","Rio de Janeiro",new Point2D.Double(20.0,20.0));
        listCities.add(c);
        ImportPortDatabase importPortDatabase = new ImportPortDatabase();

        Search search = new Search();
        DataBaseImport dataBaseImport = new DataBaseImport(importPortDatabase);
        instance = dataBaseImport.buildGraph(listPorts,listCities,borders,seadist,0);

        ArrayList<LinkedList<Object>> lista = new ArrayList<>();


        search.applyDijktra(lista,instance);


        assertEquals(instance.vertex(0),lista.get(0).get(0));
        //obtain shortest path second value
        assertEquals(lista.get(3).get(3),instance.vertex(3));
        //where before hadn't a path
        assertNull(instance.edge(instance.vertex(0),instance.vertex(4)));







    }

    /**
     * Test if vertices with greater centrality are obtained
     */
    @Test
    public void testGreaterCentrality() throws IOException {
        List<Port> listPorts = new ArrayList<>();
        List<City> listCities = new ArrayList<>();
        TreeMap<String,List<String>> borders = new TreeMap<>();
        TreeMap<String,List<Pair<String,Double>>> seadist = new TreeMap<>();
        for(int i = 0; i<listAux.length;i++){
            listPorts.add(new Port("Europe",listAux2[i],i,listAux[i],Double.valueOf(i),Double.valueOf(i)));
            listCities.add(new City(listAux[i],listCont[i],new Point2D.Double(i,i) ));
        }
        Port portCenter = new Port("Europe","Ola",45678,"Belas",25.0,35.0);
        listPorts.add(portCenter);
        for(int i = 0; i<listAux.length-1;i++){
            borders.put(listAux[i], Collections.singletonList(listAux[i + 1]));
        }
        List<String> list = borders.get(listAux[2]);
        borders.put(listAux[2],list);
        List<Pair<String,Double>> listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Berlin",20.0));
        listAu.add(new Pair<String,Double>("London",20.0));
        seadist.put("Lisboa",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Madrid",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Berlin",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("London",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Madrid",listAu);
        City c = new City("Brazil","Rio de Janeiro",new Point2D.Double(20.0,20.0));
        listCities.add(c);
        ImportPortDatabase importPortDatabase = new ImportPortDatabase();

        Search search = new Search();
        DataBaseImport dataBaseImport = new DataBaseImport(importPortDatabase);
        instance = dataBaseImport.buildGraph(listPorts,listCities,borders,seadist,5);




        String result = search.greaterCentrality(2,instance);
        String expected = "Port [code - 0 ]\n" +
                "\tEurope\n" +
                "\tPortugal\n" +
                "\tLisboa\n" +
                "[ 0.0 ; 0.0 ]\n" +
                "This port is Critical and is traversed in 9 paths.\n" +
                "-----\n" +
                "Port [code - 3 ]\n" +
                "\tEurope\n" +
                "\tUK\n" +
                "\tLondon\n" +
                "[ 3.0 ; 3.0 ]\n" +
                "This port is Critical and is traversed in 9 paths.\n" +
                "-----\n";

        assertEquals(result,expected);




    }

    /**
     * Test if all circuits are being created
     */
    @Test
    public void testCircuits() throws IOException {
        List<Port> listPorts = new ArrayList<>();
        List<City> listCities = new ArrayList<>();
        TreeMap<String,List<String>> borders = new TreeMap<>();
        TreeMap<String,List<Pair<String,Double>>> seadist = new TreeMap<>();
        for(int i = 0; i<listAux.length;i++){
            listPorts.add(new Port("Europe",listAux2[i],i,listAux[i],Double.valueOf(i),Double.valueOf(i)));
            listCities.add(new City(listAux[i],listCont[i],new Point2D.Double(i,i) ));
        }
        Port portCenter = new Port("Europe","Ola",45678,"Belas",25.0,35.0);
        listPorts.add(portCenter);
        for(int i = 0; i<listAux.length-1;i++){
            borders.put(listAux[i], Collections.singletonList(listAux[i + 1]));
        }
        List<String> list = borders.get(listAux[2]);
        borders.put(listAux[2],list);
        List<Pair<String,Double>> listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Berlin",20.0));
        listAu.add(new Pair<String,Double>("London",20.0));
        seadist.put("Lisboa",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("Madrid",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Berlin",listAu);

        listAu = new ArrayList<>();
        listAu.add(new Pair<String, Double>("London",20.0));
        listAu.add(new Pair<String,Double>("France",20.0));
        seadist.put("Madrid",listAu);
        City c = new City("Brazil","Rio de Janeiro",new Point2D.Double(20.0,20.0));
        listCities.add(c);
        ImportPortDatabase importPortDatabase = new ImportPortDatabase();

        DataBaseImport dataBaseImport = new DataBaseImport(importPortDatabase);
        instance = dataBaseImport.buildGraph(listPorts,listCities,borders,seadist,5);
        /*

        List<List<Object>> circuits = instance.searchCircuits(instance.vertex(0));

        List<Object> listRes = new ArrayList<>();

        assertEquals(circuits.get(0),listRes);

        assertEquals(circuits.size(),3);

         */

    }

    /**
     * Test comparisson of Circuits
     */
    @Test
    public void testComparison(){

    }


}
