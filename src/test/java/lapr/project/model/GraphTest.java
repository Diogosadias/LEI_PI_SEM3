package lapr.project.model;

import lapr.project.utils.PL.MatrixGraph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        MatrixGraph matrixGraph = new MatrixGraph(true);
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




    }

    /**
     * Check if all border are added
     */
    @Test
    public void testBorders(){

        List<City> cityList = new ArrayList<>();
        List<Port> portList = new ArrayList<>();

        City p =new City("Porto","Portugal");
        cityList.add(p);
        cityList.add(new City("Lisboa","Portugal"));

        MatrixGraph matrixGraph = new MatrixGraph(true);
        matrixGraph.addVertices(portList,  cityList);

        matrixGraph.addVertex(new City("Madrid","Spain") );
        assertEquals(matrixGraph.vertices().size(),3);
        String[][] list = {{"Spain","Portugal"}};

        assertTrue(matrixGraph.addBorders(list));
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
