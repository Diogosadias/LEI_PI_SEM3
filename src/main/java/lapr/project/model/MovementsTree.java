package lapr.project.model;

import oracle.ucp.util.Pair;
import lapr.project.utils.PL.AVL;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static lapr.project.model.TemporalMessages.*;

public class MovementsTree <E extends Comparable<E>> extends AVL<TemporalMessages> {


    private List<TemporalMessages> list = new ArrayList<>();


    public  String getMoveByDate(Object s) {
        if(s==null) return null;
        List<TemporalMessages> temp = find(s);
        System.out.println("Moves for : " + s);
        return printMoves(temp);
    }



    public List<TemporalMessages> getList() {
        return list;
    }

    public void setList(List<TemporalMessages> list) throws IOException {
        if(list ==null) throw new IOException("List is Null!");
        this.list = list;
    }

    public  String  searchDateFrame(Object s, Object s1) throws IOException {
        if(getDate(s).isAfter(getDate(s1))) throw new IOException("Input Date is invalid!");
        else{
            List<TemporalMessages> temp = find(getDate(s),getDate(s1));
            System.out.println("Moves from - " + s + " to -"+s1);
            return printMoves(temp);
        }
    }


    public String printMoves(List<TemporalMessages> list){
        if(list==null) return null;
        System.out.println("BaseDate Time \t\tLAT \t\tLON \t\tSOG \t\tCOG \t\tHeading \t\tCargo \t\tTranscieverClass ");
        for (TemporalMessages ms:list) {
            System.out.println(ms.printMessage());
        }

        String t="";
        return t;
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




    public   List<TemporalMessages> find(LocalDateTime date, LocalDateTime date1) {
        return find(date,date1,root());
    }

    private List<TemporalMessages> find(LocalDateTime date, LocalDateTime date1, Node<TemporalMessages> root) {
        if(root==null) return list;
        if (root.getElement().getBaseDateTime().isBefore(date)) {
            find(date,date1,root.getRight());
        }
        if (root.getElement().getBaseDateTime().isAfter(date1)) {
            find(date,date1,root.getLeft());
        }
        if (root.getElement().getBaseDateTime().isAfter(date) && root.getElement().getBaseDateTime().isBefore(date1)) {
            list.add(root.getElement());
            find(date,date1,root.getRight());
            find(date,date1,root.getLeft());
        }
        return list;
    }


    private List<TemporalMessages> find(Object s) {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
        LocalDateTime f = LocalDateTime.parse((String)s + " 00:00", formatter);
        LocalDateTime t = LocalDateTime.parse((String)s + " 24:00", formatter);
        return find(f,t,root());
    }

    


    public Pair<Double,Double> getmin() {return getmin(root());
    }

    Pair<Double, Double> getmin(Node<TemporalMessages> root) {
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
