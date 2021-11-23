/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.PL.AVL;

/**
 *
 * @author Weeb
 */
public class IMOTree <E extends Comparable<E>> extends ShipTree <Ship> {

    public Ship getShip(Object code){
        return find(code.toString(),root());
    }

    public boolean find(Object code) {
        return (getShip(code)!=null);
    }

    private Ship find(String code, Node<Ship> root) {
        if(root==null) return null;
        if(root.getElement().getIMO().compareTo(code)>0)
            return find(code,root.getLeft());

        if(root.getElement().getIMO().compareTo(code)<0)
            return find(code,root.getRight());

        return root.getElement();
    }
    
     @Override
    public void insert(Ship element) {
        root = insert(element, root());
    } 
    
        private Node<Ship> insert( Ship element, Node<Ship> node){
        if(node==null){
            return new Node<>(element,null,null);
        }
        if(node.getElement().compareToIMO(element)>0){
            node.setLeft(insert(element,node.getLeft()));
        } else if(node.getElement().compareToIMO(element)<0){
            node.setRight(insert(element,node.getRight()));
        }

        return node;
    }
    
    
    
    
}
