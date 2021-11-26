package lapr.project.utils.PL;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Diogo Dias
 */

public class KDTreeTest {
    Integer[] arr = {20,15,10,13,8,17,40,50,30,7};
    Integer[] arr1 = {20,15,10,13,8,17,40,50,30,7};
    int[] height={0,1,2,3,3,3,3,3,3,4};
    Integer[] inorderT= {7,8,10,13,15,17,20,30,40,50};
    Integer[] preorderT= {20, 15, 10, 8, 7, 13, 17, 40, 30, 50};
    Integer[] posorderT = {7, 8, 13, 10, 17, 15, 30, 50, 40, 20};

    KDTree<Integer> instance = new KDTree<>();

    /**
     * Test empty
     */
    @Test
    public void testEmpty() throws IOException {
        assertNull(instance.root);
        assertTrue(instance.isEmpty());
        assertNull(instance.root());
        instance.insert(1);
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testDouble() throws IOException {
        KDTree.DoubleNode node = new KDTree.DoubleNode(1,null,null);
        assertEquals(1,node.getinfo());
        KDTree.DoubleNode nodel = new KDTree.DoubleNode(0,null,null);
        KDTree.DoubleNode noder = new KDTree.DoubleNode(2,null,null);
        node.setLeft(nodel);
        node.setRight(noder);
        assertEquals(nodel.getinfo(),node.getLeft().getinfo());
        assertEquals(noder.getinfo(),node.getRight().getinfo());
        node.setElement(5);
        assertEquals(5,node.getinfo());
    }

    @Test
    public void testDoublecreation() throws IOException {
        try{
            new KDTree.DoubleNode<>(null,null,null);
        }catch (IOException ex){
            System.out.println("Input is Invalid!");
        }
    }

    /***
     * Test Compares
     */
    @Test
    public void testCompares(){

    }
}
