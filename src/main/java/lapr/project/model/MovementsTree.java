package lapr.project.model;

import oracle.ucp.util.Pair;
import lapr.project.utils.PL.AVL;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import static lapr.project.model.TemporalMessages.*;

public class MovementsTree <E extends Comparable<E>> extends AVL<TemporalMessages> {


    private List<TemporalMessages> list = new ArrayList<>();

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
        root = insert(element, root());
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
        if (root.getElement().getBaseDateTime().isBefore(date)) {
            find(date,date1,root.getLeft());
        }
        if (date.isBefore(root.getElement().getBaseDateTime())  && date1.isAfter(root.getElement().getBaseDateTime())) {
            list.add(root.getElement());
        }
        find(date,date1,root.getRight());
        return list;
    }


    private List<TemporalMessages> find(Object s) {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
        LocalDateTime f = LocalDateTime.parse((String)s + " 00:00", formatter);
        LocalDateTime t = LocalDateTime.parse((String)s + " 24:00", formatter);
        return find(f,t,root());
    }

    
    public String getSummary(Object code) {
        return null;
    }


    public Pair<Double,Double> getmin() {return getmin(root());
    }

    private Pair<Double, Double> getmin(Node<TemporalMessages> root) {
        if(root==null) return null;
        if(root.getLeft()==null){
            Pair<Double,Double> value = new Pair<>(root.getElement().getLatitude(),root.getElement().getLongitude());
            return value;
        } else  return getmin(root.getLeft());

    }

    public Pair<Double, Double> getmax() { return getmax(root());
    }

    private Pair<Double, Double> getmax(Node<TemporalMessages> root) {
        if(root==null) return null;
        if(root.getRight()==null){
            Pair<Double,Double> value = new Pair<>(root.getElement().getLatitude(),root.getElement().getLongitude());
            return value;
        } else  return getmin(root.getRight());
    }
}
