package lapr.project.model;

import lapr.project.utils.PL.AVL;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

import static lapr.project.model.TemporalMessages.*;

public class MovementsTree <E extends Comparable<E>> extends AVL<TemporalMessages> {


    private List<TemporalMessages> list;

    /***
     * Creates Movements Tree  for Ship - Used only once per Ship
     * @param elem - First of TemporalMessages
     */
    public void createTree(TemporalMessages elem){
        insert(elem);
    }

    public  List<TemporalMessages> getMoveByDate(Object s) {
        if(s==null) return null;
        return find(s);
    }



    public List<TemporalMessages> getList() {
        return list;
    }

    public void setList(List<TemporalMessages> list) {
        this.list = list;
    }

    public  List<TemporalMessages> searchDateFrame(Object s, Object s1) throws IOException {
        if(getDate(s).isAfter(getDate(s1))) throw new IOException("Input Date is invalid!");
        else{
            return find(getDate(s),getDate(s1));
        }
    }


    public void printMoves(List<TemporalMessages> list){
        if(list==null) return;
        System.out.println("BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass ");
        for (TemporalMessages ms:list) {
            System.out.println(ms.printMessage());
        }

    }


    @Override
    public void insert(TemporalMessages element) {
        if (element == null) return;
        insert(element, root());
    }

    private Node<TemporalMessages> insert(TemporalMessages element, Node<TemporalMessages> node) {
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


    private  List<TemporalMessages> find(LocalDateTime date, LocalDateTime date1) {
        return find(date,date1,root());
    }

    private List<TemporalMessages> find(LocalDateTime date, LocalDateTime date1, Node<TemporalMessages> root) {
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


    private List<TemporalMessages> find(Object s) { return find(s,root());
    }

    private List<TemporalMessages> find(Object s, Node<TemporalMessages> root) {
        if (root==null || root.getElement().getBaseDateTime()==s)
            return list;

        if (root.getElement().getBaseDateTime().compareTo((LocalDateTime) s)<0)
            return find(s,root.getRight());

        // Key is smaller than root's key
        return find(s,root.getLeft());
    }

}
