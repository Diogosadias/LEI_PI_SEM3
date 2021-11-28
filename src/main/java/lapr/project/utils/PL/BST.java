package lapr.project.utils.PL;

import java.io.IOException;
import java.util.*;


/**
 *
 * @author DEI-ESINF
*/

public class BST<E extends Comparable<E>> implements BSTInterface<E> {
  
    
      /** Nested static class for a binary search tree node. */
    
      protected static class Node<E> {
        private E element;          // an element stored at this node
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e  the element to be stored
         * @param leftChild   reference to a left child node
         * @param rightChild  reference to a right child node
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
          element = e;
          left = leftChild;
          right = rightChild;
        }

        // accessor methods
        public E getElement() { return element; }
        public Node<E> getLeft() { return left; }
        public Node<E> getRight() { return right; }

        // update methods
        public void setElement(E e) { element = e; }
        public void setLeft(Node<E> leftChild) { left = leftChild; }
        public void setRight(Node<E> rightChild) { right = rightChild; }
      } 

    //----------- end of nested Node class -----------
    
    protected Node<E> root = null;     // root of the tree

    
    /* Constructs an empty binary search tree. */
    public BST() {    
          root = null;
    }

    /*
    * @return root Node of the tree (or null if tree is empty)
    */
    public Node<E> root() {
       return root;
    }  

    /*
    * Verifies if the tree is empty
    * @return true if the tree is empty, false otherwise
    */
    public boolean isEmpty(){
        return root == null;
    }
    
    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element    the element to find
     * @return the Node that contains the Element, or null otherwise
     * 
     * This method despite not being essential is very useful. 
     * It is written here in order to be used by this class and its 
     * subclasses avoiding recoding.
     * So its access level is protected
     */
    protected Node<E> find(Node<E> node, E element) {
        if (node ==null) return null;
        if (node.getElement().compareTo(element)>0) return find(node.getLeft(),element) ;
        if (node.getElement().compareTo(element)<0) return find(node.getRight(),element) ;
        return node;
    }
    
    /*
    * Inserts an element in the tree.
    */
    public void insert(E element){
        root= insert(element,root());
    }
    
    private Node<E> insert(E element, Node<E> node){
        if(node==null) return new Node<>(element,null,null);
        if(node.getElement().compareTo(element)>0)  node.setLeft(insert(element,node.getLeft()));
        if(node.getElement().compareTo(element)<0)  node.setRight(insert(element,node.getRight()));
        return node;
    }

    /**
    * Removes an element from the tree maintaining its consistency as a Binary Search Tree.
    */
    public void remove(E element){
        root = remove(element, root());
    }
 
    private Node<E> remove(E element, Node<E> node) {
        
        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }   
        if (element.compareTo(node.getElement())==0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        }
        else if (element.compareTo(node.getElement()) < 0) 
            node.setLeft( remove(element, node.getLeft()) );
        else 
            node.setRight( remove(element, node.getRight()) );

        return node;
    }

    /*
    * Returns the number of nodes in the tree.
    * @return number of nodes in the tree
    */
    public int size(){
        return size(root());
    }
    
    private int size(Node<E> node){
        if (node == null)
            return 0;
        else
            return(1 + size(node.getLeft()) + size(node.getRight()));
    }
    
    /*
    * Returns the height of the tree
    * @return height 
    */
    public int height(){
        return height(root());
    }

    /*
    * Returns the height of the subtree rooted at Node node.
    * @param node A valid Node within the tree
    * @return height 
    */  
    protected int height(Node<E> node){
        if(node==null) return -1;
        Integer hl = height(node.getLeft());
        Integer hr =height(node.getRight());
        return 1+((hl<hr)? hr:hl);
    } 
    
    /**
    * Returns the smallest element within the tree.
    * @return the smallest element within the tree
    */
    public E smallestElement(){
        return smallestElement(root());
    } 
    
    protected E smallestElement(Node<E> node){
        if(node==null) return null;
        if(node.getLeft()==null) return node.getElement();
        return smallestElement(node.getLeft());
    } 
    
   /*
   * Returns an iterable collection of elements of the tree, reported in in-order.
   * @return iterable collection of the tree's elements reported in in-order
   */
    public Iterable<E> inOrder(){
        List<E> snapshot = new ArrayList<>();
        if (root!=null)
          inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;    
    }
  /**
   * Adds elements of the subtree rooted at Node node to the given
   * snapshot using an in-order traversal
   * @param node       Node serving as the root of a subtree
   * @param snapshot  a list to which results are appended
   */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

} //----------- end of BST class -----------




  

  