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
 * @param <E>
 */
public class MMSTree<E extends Comparable<E>> extends ShipTree<Ship> {

    @Override
    public void insert(Ship element) {
        root = insert(element, root());
    }

    private Node<Ship> insert(Ship element, Node<Ship> node) {
        if (node == null) {
            return new Node<>(element, null, null);
        }
        if (node.getElement().compareToMMSI(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else if (node.getElement().compareToMMSI(element) < 0) {
            node.setRight(insert(element, node.getRight()));
        }

        return node;
    }

}
