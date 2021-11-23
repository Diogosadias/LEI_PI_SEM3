package lapr.project.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShipTreeTest {
    /***
     * Ensure creating an getting Ship works
     */
    @Test
    public void ensurecreationandfetching(){
        ShipTree shipTree = new ShipTree();
        assertTrue(shipTree.isEmpty());
    }

    /***
     * Ensure verify Codes is working
     */
    @Test
    public void ensurecodes(){
        ShipTree shipTree = new ShipTree();
        assertFalse(shipTree.isMMSI(0));
        assertFalse(shipTree.isMMSI(99999999));
        assertFalse(shipTree.isMMSI(1000000000));
        assertFalse(shipTree.isMMSI(1000000001l));
        assertTrue(shipTree.isMMSI(109999999));
        assertFalse(shipTree.isMMSI(null));
        assertFalse(shipTree.isMMSI(20.5));

        assertFalse(shipTree.isISO(1));
        assertFalse(shipTree.isISO("asghdkenur"));
        assertTrue(shipTree.isISO("IMO123456"));

        assertTrue(shipTree.isCS("C4SQ2"));
        assertFalse(shipTree.isCS("IMO123456"));
        assertFalse(shipTree.isCS(109999999));
    }

    /***
     * Test inInt
     */
    @Test
    public void testisInt(){
        ShipTree shipTree = new ShipTree();
        assertFalse(shipTree.isInt("10999999"));
        assertTrue(shipTree.isInt(5));
    }

}
