package lapr.project.model;

import lapr.project.utils.PL.*;

import java.io.IOException;

public class PortTree <E extends Comparable<E>> extends KDTree<Port> {

    public void insert(Port port) throws IOException {
        insert(port,port.getCoords());
    }

}
