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

        assertFalse(tree.find("C4SQ2"));
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
        tree.insert(s);
        assertEquals(tree.getShip("C4SQ2"),s);
    }

    /***
     * Test IMOTree
     */
    @Test
    public void testImoTree(){
        IMOTree tree = new IMOTree();
        assertFalse(tree.find("C4SQ2"));
        Ship s = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        tree.insert(s);
        assertEquals(tree.getShip("IMO9395044"),s);
        assertNull(tree.getShip(210950000));
        assertTrue(tree.find("IMO9395044"));
        assertFalse(tree.find(210950000));
        Ship sbigger = new Ship("210950001","VARAMO","IMO9395045","D4SQ2",70,166,25,9.5,"NA");
        tree.insert(sbigger);
        assertEquals(tree.getShip("IMO9395045"),sbigger);
        assertNull(tree.getShip(210950001));
        assertTrue(tree.find("IMO9395045"));
        assertFalse(tree.find(210950001));
        Ship ssmaller = new Ship("210949999","VARAMO","IMO9395455","A4SQ2",70,166,25,9.5,"NA");
        tree.insert(ssmaller);
        assertEquals(tree.getShip("IMO9395455"),ssmaller);
        assertNull(tree.getShip(210949999));
        assertTrue(tree.find("IMO9395455"));
        assertFalse(tree.find(210949999));
        tree.insert(s);
        assertEquals(tree.getShip("IMO9395044"),s);


    }

    /***
     * Test MMSITree
     */
    @Test
    public void testMMSITree(){
        MMSTree tree = new MMSTree();
        assertFalse(tree.find("C4SQ2"));
        Ship s = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        tree.insert(s);
        assertEquals(tree.getShip(210950000),s);
        assertNull(tree.getShip("C4SQ2"));
        assertTrue(tree.find(210950000));
        assertFalse(tree.find("C4SQ2"));
        Ship sbigger = new Ship("210950001","VARAMO","IMO9395045","D4SQ2",70,166,25,9.5,"NA");
        tree.insert(sbigger);
        assertEquals(tree.getShip(210950001),sbigger);
        assertNull(tree.getShip("IMO9395045"));
        assertTrue(tree.find(210950001));
        assertFalse(tree.find("IMO9395045"));
        Ship ssmaller = new Ship("200949999","VARAMO","IMO9395455","A4SQ2",70,166,25,9.5,"NA");
        tree.insert(ssmaller);
        assertEquals(tree.getShip(200949999),ssmaller);
        assertNull(tree.getShip("IMO9395455"));
        assertTrue(tree.find(200949999));
        assertFalse(tree.find("IMO9395455"));
        tree.insert(s);
        assertEquals(tree.getShip(210950000),s);


    }
}
