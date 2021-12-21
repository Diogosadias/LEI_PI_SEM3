package lapr.project.utils.PL;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Diogo Dias
 * @param <T>
 */
public class KDTree<T> {

    private final Comparator<DoubleNode<T>> cmpX = (p1, p2) -> Double.compare(p1.getX(), p2.getX());
    private final Comparator<DoubleNode<T>> cmpY = (p1, p2) -> Double.compare(p1.getY(), p2.getY());



    /**
     * Nested static class for a binary search tree doublenode.
     */
    public static class DoubleNode<T> {

        protected Point2D.Double coords;
        protected T info;     // an element stored at this node
        private DoubleNode<T> left;       // a reference to the left child (if any)
        private DoubleNode<T> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e the element to be stored
         * @param leftChild reference to a left child node
         * @param rightChild reference to a right child node
         */
        public DoubleNode(T e, DoubleNode<T> leftChild, DoubleNode<T> rightChild) throws IOException {
            if (e == null) {
                throw new IOException("Input is Invalid!");
            }
            coords = new Point2D.Double(0, 0);
            info = e;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public T getInfo() {
            return info;
        }

        public DoubleNode<T> getLeft() {
            return left;
        }

        public DoubleNode<T> getRight() {
            return right;
        }

        public double getX() {
            return coords.x;
        }

        public double getY() {
            return coords.y;
        }

        // update methods
        public void setElement(T e) {
            info = e;
        }

        public void setLeft(DoubleNode<T> leftChild) {
            left = leftChild;
        }

        public void setRight(DoubleNode<T> rightChild) {
            right = rightChild;
        }

        public void setCoords(Point2D.Double location) {
            coords = location;
        }

        public void setObject(DoubleNode<T> node) {
            info = node.info;
            left = node.left;
            right = node.right;
            coords = node.coords;

        }
    }

    //----------- end of nested DoubleNode class -----------
    public DoubleNode<T> root = null;     // root of the tree


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
    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T element, Point2D.Double coords) throws IOException {
        DoubleNode n = new DoubleNode<>(element, null, null);
        n.setCoords(coords);
        if (root == null) {
            root = n;
            return;
        }
        insert(root(), n, true);
    }

    private DoubleNode<T> insert(DoubleNode<T> currentNode, DoubleNode<T> node, boolean levelchecker) throws IOException {
        if (node.coords.equals(currentNode.coords)) {
            return null;
        }

        int cmpResult = (levelchecker ? cmpX : cmpY).compare(node, currentNode);

        if (cmpResult == -1) {
            if (currentNode.getLeft() == null) {
                currentNode.setLeft(node);
            } else {
                insert(currentNode.getLeft(), node, !levelchecker);
            }
        } else if (currentNode.getRight() == null) {
            currentNode.setRight(node);
        } else {
            insert(currentNode.getRight(), node, !levelchecker);
        }

        return node;

    }

    /**
     * *
     * Returns an iterable collection of elements of the tree, reported in
     * in-order.
     *
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<T> inOrder() {
        List<T> snapshot = new ArrayList<>();
        if (root != null) {
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an in-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(DoubleNode<T> node, List<T> snapshot) {
        if (node == null) {
            return;
        }
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getInfo());
        inOrderSubtree(node.getRight(), snapshot);
    }


    /***
     * findNearestNeighbour - Finds nearest node given a location
     * @param x
     * @param y
     * @return
     */
    public T findNearesNeighbour(double x ,double y) {
        return findNearestNeighbour(root,x,y,root,true);
    }
    
    private T findNearestNeighbour(DoubleNode<T> node, double x, double y, DoubleNode<T> closestNode, boolean divX) {
        if (node == null) return null;

        double d = Point2D.distanceSq(node.coords.x, node.coords.y, x, y);
        double closestDist = Point2D.distanceSq(closestNode.coords.x,
                closestNode.coords.y, x, y);
        if (closestDist > d) {
            closestNode.setObject(node);}
        
            double delta = divX ? x - node.coords.x : y - node.coords.y;
            double delta2 = delta * delta;
            DoubleNode<T> node1 = delta < 0 ? node.left : node.right;
            DoubleNode<T> node2 = delta < 0 ? node.right : node.left;
            findNearestNeighbour(node1, x, y, closestNode, !divX);
            if (delta2 < closestDist) {
                findNearestNeighbour(node2, x, y, closestNode, !divX);
                

            }
            return closestNode.info;
        }
    public <E extends Comparable<E>> void remove(E p) {
        //Implement Remove
    }

}
