package lapr.project.utils.PL;


/**
 * @author Diogo Dias
 * @param <E>
 */

public class KDTree <E extends Comparable<E>> {

    /** Nested static class for a binary search tree doublenode. */

    protected static class DoubleNode<E> {
        private E element;          // an element stored at this node
        private DoubleNode<E> left;       // a reference to the left child (if any)
        private DoubleNode<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e  the element to be stored
         * @param leftChild   reference to a left child node
         * @param rightChild  reference to a right child node
         */
        public DoubleNode(E e, DoubleNode<E> leftChild, DoubleNode<E> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public E getElement() { return element; }
        public DoubleNode<E> getLeft() { return left; }
        public DoubleNode<E> getRight() { return right; }

        // update methods
        public void setElement(E e) { element = e; }
        public void setLeft(DoubleNode<E> leftChild) { left = leftChild; }
        public void setRight(DoubleNode<E> rightChild) { right = rightChild; }

        public int compareX(E element) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int compareY(E element) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    //----------- end of nested DoubleNode class -----------

    protected DoubleNode<E> root = null;     // root of the tree


    /* Constructs an empty binary search tree. */
    public KDTree() {
        root = null;
    }

    /*
     * @return root Node of the tree (or null if tree is empty)
     */
    public DoubleNode<E> root() {
        return root;
    }

    /*
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty(){
        if(root == null) return true;
        return false;
    }

    public void insert(E element){root=insert(element,root(),true);
    }

    private DoubleNode<E> insert(E element, DoubleNode<E> node, boolean levelchecker){
        if(node==null) return new DoubleNode<>(element,null,null);
        if(levelchecker){
            if(node.compareX(element)>0) insert(element,node.getLeft(),false);
            else insert(element,node.getRight(),false);
        }
        if(levelchecker){
            if(node.compareY(element)>0) insert(element,node.getLeft(),true);
            else insert(element,node.getRight(),true);
        }
        return node;
    }


}
