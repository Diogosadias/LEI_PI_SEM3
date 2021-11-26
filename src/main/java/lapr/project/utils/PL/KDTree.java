package lapr.project.utils.PL;


import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Comparator;

/**
 * @author Diogo Dias
 * @param <T>
 */

public class KDTree <T> {

    private final Comparator<DoubleNode<T>> cmpX = new Comparator<DoubleNode<T>>(){
        @Override
        public int compare(DoubleNode<T> p1, DoubleNode<T> p2) {
            return Double.compare(p1.getX(), p2.getX());
        }
    };
    private final Comparator<DoubleNode<T>> cmpY = new Comparator<DoubleNode<T>>(){
        @Override
        public int compare(DoubleNode<T> p1, DoubleNode<T> p2) {
            return Double.compare(p1.getY(), p2.getY());
        }
    };

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
            if(checkElement(e)==null) throw new IOException("Input is Invalid!");
            coords = new Point2D.Double(0, 0);
            info = checkElement(e);
            left = leftChild;
            right = rightChild;
        }

        private T checkElement(T e) {
            if(e==null) return null;
            return e;
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

    public void insert(T element) throws IOException {
        if (root==null) {
            root=new DoubleNode<>(element,null,null);
            return;
        }
        root=insert(new DoubleNode<>(element,null,null),root(),true);
    }

    private DoubleNode<T> insert(DoubleNode<T> currentNode, DoubleNode<T> node, boolean levelchecker) throws IOException {
        if (node.coords.equals(currentNode.coords))   return null;

        int cmpResult = (levelchecker ? cmpX : cmpY).compare(node, currentNode);

        if (cmpResult == -1)
            if (currentNode.left == null)
                currentNode.left = node;
            else
                insert(currentNode.left, node, !levelchecker);
        else
        if (currentNode.right == null)
            currentNode.right = node;
        else
            insert(currentNode.right, node, !levelchecker);

        return node;

    }


}
