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

    /***
     * Test CSTree
     */
    @Test
    public void testCallSign(){
        CallSignTree tree = new CallSignTree();
        Ship s = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        tree.insert(s);
        assertEquals(tree.getShip("C4SQ2"),s);
        assertNull(tree.getShip(210950000));
        assertTrue(tree.find("C4SQ2"));
        assertFalse(tree.find(210950000));
        Ship sbigger = new Ship("210950001","VARAMO","IMO9395045","D4SQ2",70,166,25,9.5,"NA");
        tree.insert(sbigger);
        assertEquals(tree.getShip("D4SQ2"),sbigger);
        assertNull(tree.getShip(210950001));
        assertTrue(tree.find("D4SQ2"));
        assertFalse(tree.find(210950001));
        Ship ssmaller = new Ship("210949999","VARAMO","IMO9395455","A4SQ2",70,166,25,9.5,"NA");
        tree.insert(ssmaller);
        assertEquals(tree.getShip("A4SQ2"),ssmaller);
        assertNull(tree.getShip(210949999));
        assertTrue(tree.find("A4SQ2"));
        assertFalse(tree.find(210949999));

    }

}
