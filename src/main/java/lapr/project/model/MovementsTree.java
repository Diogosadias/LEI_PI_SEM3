package lapr.project.model;

import lapr.project.utils.PL.AVL;

import java.io.IOException;
import java.util.List;

import static lapr.project.model.ShipMovements.*;

public class MovementsTree extends AVL {

    public void createTree(){

    }
    public static List<ShipMovements> getMoveByDate(String s) {

        return null;
    }

    public static void searchDateFrame(String s, String s1) throws IOException {
        if(getDate(s).isAfter(getDate(s1))) throw new IOException("Input Date is invalid!");
    }

    public List<ShipMovements> getMoveByDateFrame(String s, String s1) {
        return null;
    }

    /*
    @Override
    public void insert(ShipMovements element) {
        if (element == null) return;
        insert(element, root());
    }

    private Node<ShipMovements> insert(ShipMovements element, Node<ShipMovements> node) {
        if (node == null) {
            return new Node<>(element, null, null);
        }
        if (node.getElement().compareTo(element) == 0) {
            node.getElement().incOcorrences();
            return node;
        }
        if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else if (node.getElement().compareTo(element) < 0) {
            node.setRight(insert(element, node.getRight()));
        }

        return node;
    }

     */
}
