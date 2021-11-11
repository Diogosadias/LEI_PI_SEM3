package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class PairsCalculator {
    private ShipTree mmsiTree ;

    public PairsCalculator(ShipTree mmsiTree){
        this.mmsiTree=mmsiTree;
    }

    public String pairs() {
        List<Ship> ships = (List<Ship>) mmsiTree.inOrder();
        TreeMap <Ship,Ship> map = new TreeMap<>();
        for(Ship s:ships) {
            for (Ship t : ships) {
                if(s.checkproximity(t)){
                    map.put(s,t);
                }
            }
        }
        return order(map);

    }

    private String order(TreeMap<Ship, Ship> map) {
        for(Ship s:map.keySet()){
            LinkedList<Ship> list = new LinkedList<>();
            for(Ship t:map.values()){

            }
        }
        return null;
    }
}
