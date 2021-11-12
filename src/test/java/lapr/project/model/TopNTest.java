package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TopNTest {

    /***
     * Ensure Constructor can't be null
     */
    @Test
    void testgetTop() {
        try{
            TopN top = new TopN(null);
        }catch (IOException ex){
            System.out.println("Tree is not Valid!");
        }
    }
}