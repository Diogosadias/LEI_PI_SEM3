package lapr.project.model;

import lapr.project.utils.PL.MatrixGraph;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    /**
     * Test if Graph has 2 different types of Vertices - Classes
     */
    @Test
    public void testDiferentTypes(){

    }



    /**
     * Test if Graph has 2 different types of Vertices - Ports and Capitals/Cities
     */
    @Test
    public void testHasCitiesandPorts(){

    }

    /**
     * Check If the Capital is connected to the nearest Country Port
     */
    @Test
    public void testCapitalPortConnection(){

    }

    /**
     * Check If the Capital is not connect to Port from another Country
     */
    @Test
    public void ensureCapitalconnectsonlytoCountryPorts(){

    }

    /**
     * Check If the Capital is connected with all Capitals of Country it borders
     */
    @Test
    public void testCapitalBorderConnection(){

    }

    /**
     * Check If Capital is not connected with a Country it has no border with
     */
    @Test
    public void ensureNotConnectedNoBorder(){

    }

    /**
     * Ensure that a Port is connected with all the Ports of the Same Country
     */
    @Test
    public void ensurePortConnections(){

    }



    /**
     * Ensure n Port Connection
     */
    @Test
    public void nPortConnection(){

    }

    /**
     * Ensure distance is well-placed
     */
    @Test
    public void checkDistances(){

    }

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
    public void testBorders(){

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

        treeMap.put("Spain",list);
        assertTrue(matrixGraph.addBorders(treeMap));
        assertEquals(matrixGraph.edge(l,m).getWeight(),314.4748051008686);
        assertEquals(matrixGraph.edge(m,l).getWeight(),314.4748051008686);
        assertEquals(matrixGraph.edge(p,m).getWeight(),157.24938127194397);
    }

    /**
     * Check if all Capital Ports are added
     */
    @Test
    public void testCapitalPort(){


    }

    /**
     * Check if n Ports are added
     */
    @Test
    public void testNPorts(){

    }
}
