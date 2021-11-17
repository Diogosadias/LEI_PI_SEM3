/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.PL;

/**
 *
 * @author DEI-ESINF
 * @param <E>
 */
public class AVL <E extends Comparable<E>> extends BST<E> {
    
    
    private int balanceFactor(Node<E> node){
        return height(node.getRight()) - height(node.getLeft());
    }
    
    private Node<E> rightRotation(Node<E> node){
        Node <E> leftson = node.getLeft();
        node.setLeft(leftson.getRight());
        leftson.setRight(node);
        return leftson;

    }
    
    private Node<E> leftRotation(Node<E> node){
        Node <E> rigthson = node.getRight();
        node.setRight(rigthson.getLeft());
        rigthson.setLeft(node);
        return rigthson;
    }
    
    private Node<E> twoRotations(Node<E> node){
        if (balanceFactor(node) < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        }
        else {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;

    }


    
    private Node<E> balanceNode(Node<E> node)    {
        if(balanceFactor(node)<-1) {
            if (balanceFactor(node.getLeft()) < 0)
                node = rightRotation(node);
            else
                node = twoRotations(node);
        }
        if(balanceFactor(node)>1){
            if(balanceFactor(node.getRight())>0)
                node = leftRotation(node);
            else
                node =twoRotations(node);
        }
            return node;
    }
    
    @Override
    public void insert(E element){
        root = insert(element, root);
    }
    private Node<E> insert(E element, Node<E> node){
        if(node==null){
            return new Node<E>(element,null,null);
        }
        if(node.getElement().compareTo(element)>0){
            node.setLeft(insert(element,node.getLeft()));
            node = balanceNode(node);
        } else if(node.getElement().compareTo(element)<0){
            node.setRight(insert(element,node.getRight()));
            node = balanceNode(node);
        }

        return node;
    }
    
    @Override  
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
            balanceNode(node);
        }
        else if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
            node = balanceNode(node);
        }
        else {
            node.setRight(remove(element, node.getRight()));
            node = balanceNode(node);
        }

        return node;
    }
    
    
    public boolean equals(Object otherObj) {

        if (this == otherObj) 
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        AVL<E> second = (AVL<E>) otherObj;
        return equals(root, second.root);
    }

    public boolean equals(Node<E> root1, Node<E> root2) {
        if (root1 == null && root2 == null) 
           return true;
        else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else  
                return false; 
        }
        else return false;
    }
   
}