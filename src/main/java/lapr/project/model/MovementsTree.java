package lapr.project.model;

import lapr.project.utils.PL.AVL;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;
import static lapr.project.model.ShipMovements.*;

public class MovementsTree extends AVL<ShipMovements> {

    /***
     * Creates Movements Tree  for Ship - Used only once per Ship
     * @param elem - First ShipMovement
     */
    public void createTree(ShipMovements elem){
        insert(elem);
    }

    public static List<ShipMovements> getMoveByDate(String s) {

        return null;
    }

    public static List<ShipMovements> searchDateFrame(String s, String s1) throws IOException {
        if(getDate(s).isAfter(getDate(s1))) throw new IOException("Input Date is invalid!");
        else{
            return find(getDate(s),getDate(s1));
        }
    }


    public List<ShipMovements> getMoveByDateFrame(String s, String s1) {
        return null;
    }


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
            return node;
        }
        if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else if (node.getElement().compareTo(element) < 0) {
            node.setRight(insert(element, node.getRight()));
        }

        return node;
    }

    private static List<ShipMovements> find(LocalDateTime date, LocalDateTime date1) {


        return null;
    }

}
