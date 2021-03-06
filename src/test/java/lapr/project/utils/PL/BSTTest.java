
package lapr.project.utils.PL;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import javax.lang.model.element.Element;

/**
 *
 * @author DEI-ESINF
 */
public class BSTTest {
    Integer[] arr = {20,15,10,13,8,17,40,50,30,7};
    int[] height={0,1,2,3,3,3,3,3,3,4};
    Integer[] inorderT= {7,8,10,13,15,17,20,30,40,50};
    Integer[] preorderT= {20, 15, 10, 8, 7, 13, 17, 40, 30, 50};
    Integer[] posorderT = {7, 8, 13, 10, 17, 15, 30, 50, 40, 20};
    
    BST<Integer> instance = new BST();

    @Test
    public void testBSTTest() {
        BST arvore = new BST();
        assertNull(arvore.root);
    }
    @Test
    public void testSomething(){
        BST t = new BST();
        assertNull(t.root);
        assertTrue(t.isEmpty());
        assertNull(t.find(t.root, 1));
        t.insert(1);
        assertEquals(t.root.getElement(),new BST.Node(1,null,null).getElement());
    }
    

    /**
     * Test of size method, of class BST.
     */
    @Test
    public void testSize() {

        for(int i :arr)
            instance.insert(i);
        System.out.println("size");
        assertEquals("size should be = 10",instance.size(), arr.length);
        
        BST<String> sInstance = new BST();
        assertEquals("size should be = 0",sInstance.size(), 0);
        assertTrue(sInstance.isEmpty());
        sInstance.insert("A");
        assertEquals("size should be = 1",sInstance.size(), 1);
        sInstance.insert("B");
        assertEquals("size should be = 2",sInstance.size(), 2);
        sInstance.insert("A");
        assertEquals("size should be = 2",sInstance.size(), 2);
        assertFalse(sInstance.isEmpty());
    }
   
    /**
     * Test of insert method, of class BST.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        int arr[] = {20,15,10,13,8,17,40,50,30,20,15,10};
        BST<Integer> instance = new BST();
        for (int i=0; i<9; i++){            //new elements
            instance.insert(arr[i]);
            assertEquals("size should be = "+(i+1),instance.size(), i+1);
        }
        for(int i=9; i<arr.length; i++){    //duplicated elements => same size
            instance.insert(arr[i]);
            assertEquals("size should be = 9",instance.size(), 9);
        }
    }
    /**
     * Test of remove method, of class BST.
     */
    @Test
    public void testRemove() {

        for(int i :arr)
            instance.insert(i);
        System.out.println("remove");
        Integer[] arr = {20,40,15,10,13,8,17,50,30,7};


        int qtd=arr.length;
        try {
            instance.remove(999);
        } catch (IllegalArgumentException ex){
            System.out.println("Element does not exist");
        }

        assertEquals("size should be = "+qtd, instance.size(), qtd);
        for (int i=0; i<arr.length; i++){
            instance.remove(arr[i]);
            qtd--;
            assertEquals("size should be = "+qtd, qtd,instance.size());
        }

        try {
            instance.remove(999);
        } catch (IllegalArgumentException ex){
            System.out.println("Element does not exist");
        }
    }

    /**
     * Test of isEmpty method, of class BST.
     */
    @Test
    public void testIsEmpty() {

        for(int i :arr)
            instance.insert(i);
        System.out.println("isempty");
        
        assertFalse("the BST should be NOT empty", instance.isEmpty());        
        instance = new BST();
        assertTrue("the BST should be empty",instance.isEmpty());        

        instance.insert(11);
        assertFalse("the BST should be NOT empty", instance.isEmpty());
        
        instance.remove(11);
        assertTrue("the BST should be empty", instance.isEmpty());

        assertNull(instance.smallestElement());
    }
/**
     * Test of height method, of class BST.
     */
    @Test
    public void testHeight() {
        System.out.println("height");
        Integer[] list = {20,40,15,10,13,8,17,50,30,7};
        int[] height={0,1,1,2,3,3,3,3,3,4};


        instance = new BST();
        assertEquals("height should be = -1", instance.height(), -1);
        for(int idx=0; idx<list.length; idx++){
            instance.insert(list[idx]);
            assertEquals("height should be = "+height[idx], instance.height(), height[idx]);            
        }
        instance = new BST();
        assertEquals("height should be = -1", instance.height(), -1);
    }
    /**
     * Test of smallestelement method, of class TREE.
     */
    @Test
    public void testSmallestElement() {

        for(int i :arr)
            instance.insert(i);
        System.out.println("smallestElement");
        BST arvore = new BST();
        arvore.root = null;
        assertNull(arvore.smallestElement());
        assertEquals(new Integer(7), instance.smallestElement());
        instance.remove(7);
        assertEquals(new Integer(8), instance.smallestElement());
        instance.remove(8);
        assertEquals(new Integer(10), instance.smallestElement());
    }

   

/**
     * Test of inOrder method, of class BST.
     */
    @Test
    public void testInOrder() {

        for(int i :arr)
            instance.insert(i);
        System.out.println("inOrder");
        BST arvore = new BST();
        Integer[] lits = new Integer[0];
        List<Integer> list = Arrays.asList(lits);
        assertEquals(list,arvore.inOrder());
        List<Integer> lExpected = Arrays.asList(inorderT);
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());
    }


    /**
     * Tests for Coverage
     */
    @Test
    public void updateMethods() {
        //Arrange
        Object o = new Object();
        BST.Node<Object> node = new BST.Node<>(o, null, null);
        assertEquals(o,node.getElement());
        Object o1 = new Object();
        assertNotEquals(o1,node.getElement());
        node.setElement(o1);
        assertEquals(o1, node.getElement());
        BST.Node<Object> node1 = new BST.Node<>(o, null, null);
        node.setLeft(node1);
        assertEquals(node.getLeft(),node1);

    }

    /**
     * Test IsEmpty()
     */
    @Test
    public void ensureEmptiness(){
        BST<Integer> bst = new BST();
        assertTrue(bst.isEmpty());
        int i = 0;
        bst.insert( i);
        assertFalse(bst.isEmpty());
        bst.remove(i);
        assertTrue(bst.isEmpty());

    }

    /**
     * Test find
     */
    @Test
    public void findtest(){
        BST<Integer> bst = new BST();
        int i = 0,j=1,l=-1;
        assertNull(bst.find(null,i));
        bst.insert(i);
        bst.insert(l);
        BST.Node <Integer> node = new BST.Node(l,null,null);
        assertEquals(bst.find(bst.root(),l).getElement(),node.getElement());
        bst.insert(j);
        BST.Node<Integer> node1 = new BST.Node(j,null,null);
        assertEquals(bst.find(bst.root(),j).getElement(),node1.getElement());
        assertNull(bst.find(null,i));
    }

    /**
     * Test insert
     */
    @Test
    public void insertTest(){
        BST<Integer> bst = new BST<>();
        assertNull(bst.smallestElement());
        bst.insert(0);
        int i = bst.smallestElement();
        assertEquals(i,0);
        bst.insert(-1);
        i =bst.smallestElement();
        assertEquals(i,-1);
    }

    /***
     * Test setElement Node
     */
    @Test
    public void testSetElement(){
        BST<Integer> n = new BST();
        assertTrue(n.isEmpty());
        BST.Node<Integer> node = new BST.Node<>(1, null, null);
        assertTrue(node.getElement().equals(1));
        node.setElement(2);
        assertTrue(node.getElement().equals(2));
        node.setElement(null);
        assertTrue(node.getElement().equals(2));
    }
}
