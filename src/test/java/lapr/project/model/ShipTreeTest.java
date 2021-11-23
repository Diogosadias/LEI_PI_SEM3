package lapr.project.model;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

    }

}
