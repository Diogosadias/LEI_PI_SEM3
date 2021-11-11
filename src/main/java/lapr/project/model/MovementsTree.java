package lapr.project.model;

import lapr.project.utils.PL.AVL;
import lapr.project.utils.PL.BST;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static lapr.project.model.ShipMovements.*;

public class MovementsTree <E extends Comparable<E>> extends AVL<ShipMovements> {


    private List<ShipMovements> list;

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
    public List<ShipMovements> getList() {
        return list;
    }

    public void setList(List<ShipMovements> list) {
        this.list = list;
    }

    public  List<ShipMovements> searchDateFrame(Object s, Object s1) throws IOException {
        if(getDate(s).isAfter(getDate(s1))) throw new IOException("Input Date is invalid!");
        else{
            return find(getDate(s),getDate(s1));
        }
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


    private  List<ShipMovements> find(LocalDateTime date, LocalDateTime date1) {
        return find(date,date1,root());
    }

    private List<ShipMovements> find(LocalDateTime date, LocalDateTime date1, Node<ShipMovements> root) {
        if(root==null) return list;
        if (root.getElement().getBaseDateTime().compareTo(date)<0) {
            find(date,date1,root.getLeft());
        }
        if (date.compareTo(root.getElement().getBaseDateTime()) <= 0 && date1.compareTo(root.getElement().getBaseDateTime()) >= 0) {
            list.add(root.getElement());
        }
        find(date,date1,root.getRight());
        return list;
    }


}
