package lapr.project.utils.PL;


import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Comparator;

/**
 * @author Diogo Dias
 * @param <T>
 */

public class KDTree <T> {

    private final Comparator<DoubleNode<T>> cmpX = (p1, p2) -> Double.compare(p1.getX(), p2.getX());
    private final Comparator<DoubleNode<T>> cmpY = (p1, p2) -> Double.compare(p1.getY(), p2.getY());

    /** Nested static class for a binary search tree doublenode. */


    protected static class DoubleNode<T> {
        protected Point2D.Double coords;
        protected T info;     // an element stored at this node
        private DoubleNode<T> left;       // a reference to the left child (if any)
        private DoubleNode<T> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e  the element to be stored
         * @param leftChild   reference to a left child node
         * @param rightChild  reference to a right child node
         */
        public DoubleNode(T e, DoubleNode<T> leftChild, DoubleNode<T> rightChild) throws IOException {
            if(e==null) throw new IOException("Input is Invalid!");
            coords = new Point2D.Double(0, 0);
            info = e;
            left = leftChild;
            right = rightChild;
        }

        

        // accessor methods
        public T getinfo() { return info; }
        public DoubleNode<T> getLeft() { return left; }
        public DoubleNode<T> getRight() { return right; }
        public double getX() {
            return coords.x;
        }
        public double getY() {
            return coords.y;
        }
        // update methods
        public void setElement(T e) { info = e; }
        public void setLeft(DoubleNode<T> leftChild) { left = leftChild; }
        public void setRight(DoubleNode<T> rightChild) { right = rightChild; }
        public void setCoords(Point2D.Double location){ coords=location;}





    }

    //----------- end of nested DoubleNode class -----------

    protected DoubleNode<T> root = null;     // root of the tree


    /* Constructs an empty binary search tree. */
    public KDTree() {
        root = null;
    }

    /*
     * @return root Node of the tree (or null if tree is empty)
     */
    public DoubleNode<T> root() {
        return root;
    }

    /*
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty(){
        return root==null;
    }

    public void insert(T element,Point2D.Double coords) throws IOException {
        DoubleNode n =new DoubleNode<>(element,null,null);
        n.setCoords(coords);
        if (root==null) {
            root = n;
            return;
        }
        insert(root(),n,true);
    }

    private DoubleNode<T> insert(DoubleNode<T> currentNode, DoubleNode<T> node, boolean levelchecker) throws IOException {
        if (node.coords.equals(currentNode.coords))   return null;

        int cmpResult = (levelchecker ? cmpX : cmpY).compare(node, currentNode);

        if (cmpResult == -1)
            if (currentNode.getLeft() == null)
                currentNode.setLeft(node);
            else{
                levelchecker = !levelchecker;
                insert(currentNode.getLeft(), node, levelchecker);
            }

        else
        if (currentNode.getRight() == null)
            currentNode.setRight(node);
        else{
            levelchecker = !levelchecker;
            insert(currentNode.getRight(), node, !levelchecker);
        }


        return node;

    }


}
