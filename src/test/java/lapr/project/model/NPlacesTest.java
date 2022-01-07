package lapr.project.model;

import lapr.project.controller.TrafficManagerController;
import lapr.project.data.ImportPortDatabase;
import lapr.project.utils.PL.MatrixGraph;
import org.junit.jupiter.api.Test;
import oracle.ucp.util.Pair;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static lapr.project.model.Search.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class NPlacesTest {

    private String[] listAux = {"Lisboa","Paris","Madrid","London","Roma","Berlin"} ;
    private String[] listCont = {"Lisboa","Paris","Madrid","London","Roma","Berlin"} ;
    private String[] listAux2 = {"Portugal","France","Spain","UK","Italy","Germany"} ;
    private String[] listPorts = {"Lisboa","Paris","Madrid","London","Roma","Berlin"};
    MatrixGraph instance;


    /**
     * Test PrintL
     */
    @Test
    public void testPrintL() throws IOException {

        LinkedList<Pair<Object,Double>> list = new LinkedList<>();
        Port port = new Port("Europe","Portugal",12,"Porto",0.1,0.2);
        City p =new City("Paris","França",new Point2D.Double(1,1));
        City l =new City("Lisboa","Portugal",new Point2D.Double(2,2));
        list.add(new Pair<>(port,2.0));
        list.add(new Pair<>(p,2.0));
        list.add(new Pair<>(l,2.0));

        String result="\nPorto, Portugal - average distance  - 2.0\n" +
                "Paris, França - average distance  - 2.0\n" +
                "Lisboa, Portugal - average distance  - 2.0";
        assertEquals(printL(list),result);

    }

    /**
     * Test AvgShortPath
     */
    @Test
    public void testAvgShortpath(){
        Double[][] teste = new Double[2][2];
        for(int i = 0; i<teste.length;i++){
            for(int j = 0; j< teste.length;j++){
                teste[i][j] = Double.valueOf(i+j);
            }
        }
        Double value = getShortAverage(teste,0);
        Double result = 0.5;
        assertEquals(value,result);
        value = getShortAverage(teste,1);
        result = 1.5;
        assertEquals(value,result);
    }

    /**
     * Test nClosestPlaces
     */
    @Test
    public void testnClosestPlaces() throws IOException {
        Port port = new Port("Europe","Portugal",12,"Porto",0.1,0.2);
        City p =new City("Paris","França",new Point2D.Double(1,1));
        City l =new City("Lisboa","Portugal",new Point2D.Double(2,2));
        MatrixGraph matrixGraph = new MatrixGraph(false);
        matrixGraph.addVertex(port);
        matrixGraph.addVertex(p);
        matrixGraph.addVertex(l);
        matrixGraph.addEdge(port,p,1.0);
        matrixGraph.addEdge(p,l,2.0);
        matrixGraph.addEdge(port,l,1.0);

        Double[][] floydMatrix = matrixGraph.floydMatrix();


        LinkedList<Pair<Object,Double>> list = new LinkedList<>();
        list.add(new Pair<Object,Double>(port,1.0));

        LinkedList<Pair<Object,Double>> expected = nClosestPlaces(matrixGraph,floydMatrix,1);
        assertEquals(list,expected);

    }

    /**
     * Test US303
     */
    @Test
    public void testUS() throws IOException {
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

        String result = "\nFor the Continent : null\n" +
                "\n" +
                "Madrid, Madrid - average distance  - 282.89092352507106\n" +
                "-----------------\n" +
                "For the Continent : Europe\n" +
                "There are no Connections to Calculate\n" +
                "-----------------";
        Search search = new Search();
        String expected = search.selectNPlaces(1,instance);
        assertEquals(expected,result);
        TrafficManagerController trafficManagerController = new TrafficManagerController();

        trafficManagerController.dataBaseImport.setMatrixGraph(instance);

        Scanner in = new Scanner(result);
        Scanner ou = new Scanner(trafficManagerController.selectNPlaces(1));
        assertEquals(in.nextLine(),ou.nextLine());

    }
}
