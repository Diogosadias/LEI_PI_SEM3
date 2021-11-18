/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.PL;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author DEI-ESINF
 */
public class AVLTest {
    
    public AVLTest() {
    }
    
      /**
     * Test of insert method, of class AVL.
     */
    @Test
    public void testInsert() {

        //test Simple right rotation 
        AVL<Integer> instance = new AVL();
        int arr[] = {8,4,10,2,6,3};
        Integer[] inorder1={2,3,4,6,8,10};
        for (int i=0; i<arr.length; i++)            //new elements
            instance.insert(arr[i]);
        
        List<Integer> lExpected = Arrays.asList(inorder1);           
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());

        //test Simple left rotation
        AVL<Integer> instance2 = new AVL();
        int arr2[] = {8,4,10,9,15,12};
        Integer[] inorder2={4,8,9,10,12,15};
        for (int i=0; i<arr2.length; i++)          
            instance2.insert(arr2[i]);
        lExpected = Arrays.asList(inorder2);
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance2.inOrder());
        assertEquals("height should be 2 ",instance2.height(), 2);
        
        //test double rotation 
        AVL<Integer> instance3 = new AVL();
        int arr3[] = {8,4,10,2,6,5};
        Integer[] inorder3={2,4,5,6,8,10};
        for (int i=0; i<arr3.length; i++)          
            instance3.insert(arr3[i]);
        lExpected = Arrays.asList(inorder3);
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance3.inOrder());
        assertEquals("height should be 2 ",instance3.height(), 2);
    }
      /**
     * Test of remove method, of class AVL.
     */
    @Test
    public void testRemove() {
        List<Integer> lExpected;
        AVL<Integer> instance;
        
        instance = new AVL();
        int arr[] = {8,4,10,2,6,3};
        for (int i=0; i<arr.length; i++)            
            instance.insert(arr[i]);
        

        //no rotations needed
        instance.remove(3);
        lExpected = Arrays.asList(2,4,6,8,10);           
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());
        assertEquals("height should be 2 ",instance.height(), 2);       

        //test Simple left rotation 
        instance.remove(2);
        lExpected = Arrays.asList(4,6,8,10);           
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());
        assertEquals("height should be 2 ",instance.height(), 2);       

        instance.remove(10);
        lExpected = Arrays.asList(4,6,8);           
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());
        assertEquals("height should be 1 ",instance.height(), 1);       

        instance.remove(6);
        lExpected = Arrays.asList(4,8);           
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());
        assertEquals("height should be 1 ", 1, instance.height());       

        instance.remove(4);
        lExpected = Arrays.asList(8);           
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());
        assertEquals("height should be 1 ", 0, instance.height());       

        instance.remove(8);
        lExpected = Arrays.asList();           
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());
        assertEquals("height should be 1 ", -1, instance.height());       

    }
    
     @Test
    public void testEquals() {
        AVL<Integer> instance = new AVL();
        int arr[] = {1, 8};
        for (int i = 0; i < arr.length; i++)
        {
            instance.insert(arr[i]);
        }
        AVL<Integer> instance2 = new AVL();
        int arr2[] = {1, 8};
        for (int i = 0; i < arr2.length; i++) 
        {
            instance2.insert(arr2[i]);
        }
        assertTrue("equals! ", instance.equals(instance2));
        instance2.remove(8);
        assertFalse("equals! ", instance.equals(instance2));
    }

    @Test
    public void testNullsvalues(){
        AVL<Integer> instance = new AVL();
        int arr[] = {1, 8};
        for (int i = 0; i < arr.length; i++)
        {
            instance.insert(arr[i]);
        }
        AVL<Integer> instance2 = new AVL();
        int arr2[] = {1, 8};
        for (int i = 0; i < arr2.length; i++)
        {
            instance2.insert(arr2[i]);
        }
        assertFalse(instance.equals(null));
        assertFalse(instance2.equals(null));
    }

    /**
     * Rotations Testing
     */
    @Test
    public void testRotations(){


        //test Big  rotation
        AVL<Integer> instance = new AVL();
        int arr[] = {8,4,10,2,6,3,10,22,11,16,21,1,5,7,6,9,23,12,14};
        Integer[] inorder1={1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 21, 22, 23};
        for (int i=0; i<arr.length; i++)            //new elements
            instance.insert(arr[i]);

        List<Integer> lExpected = Arrays.asList(inorder1);
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());
        instance.remove(4);
        instance.remove(1);

        inorder1= new Integer[]{2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 21, 22, 23};
        lExpected = Arrays.asList(inorder1);
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());



    }

    /**
     * Test BalanceNode
     */
    @Test
    public void testBalanceNode(){
        BST.Node no = new BST.Node(1,null,null);
        BST.Node no1 = new BST.Node(2,null,null);
        BST.Node no2 = new BST.Node(3,null,null);
        AVL arvore = new AVL();
        assertEquals(no,arvore.balanceNode(no));
        assertEquals(no2,arvore.balanceNode(no2));
        assertEquals(no1,arvore.balanceNode(no1));
        assertNull(arvore.balanceNode(null));

    }
}
