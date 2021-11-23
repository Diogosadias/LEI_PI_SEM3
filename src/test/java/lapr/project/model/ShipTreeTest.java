package lapr.project.model;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class ShipTreeTest {
    /***
     * Ensure creating an getting Ship works
     */
    @Test
    public void ensurecreationandfetching(){
        ShipTree shipTree = new ShipTree();
        assertNull(shipTree.getShip(null));
    }

}
