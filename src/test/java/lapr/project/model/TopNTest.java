package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TopNTest {

    /***
     * Ensure Constructor can't be null
     */
    @Test
    void testgetTop() throws IOException {
        try{
            TopN top = new TopN(null);
        }catch (IOException ex){
            System.out.println("Tree is not Valid!");
        }
        ShipTree mmsiTree =new ShipTree();
        TopN top = new TopN(mmsiTree);
        assertEquals(mmsiTree,top.getTree());
    }
}