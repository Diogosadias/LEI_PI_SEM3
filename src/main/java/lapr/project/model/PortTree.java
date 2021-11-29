package lapr.project.model;

import lapr.project.utils.PL.*;

import java.io.IOException;

public class PortTree <E extends Comparable<E>> extends KDTree<Port> {

    public void insert(Port port) throws IOException {
        insert(port,port.getCoords());
    }

    protected DoubleNode<? extends Object> find(DoubleNode<Port> node, E element) {
        if (node ==null) return null;
        if (node.getInfo().compareTo((Port) element)>0) return find(node.getLeft(),element) ;
        if (node.getInfo().compareTo((Port) element)<0) return find(node.getRight(),element) ;
        return node;
    }

    public boolean find(E property) {
        return find(root,property)!=null;
    }
}
