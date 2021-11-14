package lapr.project.model;




import oracle.ucp.util.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class TopN {

    private final TreeMap<Ship, List<TemporalMessages>> map = new TreeMap<>();

    private final ShipTree mmsiTree;

    public TopN(ShipTree mmsiTree) throws IOException {
        if(mmsiTree==null) throw new IOException("Tree is not Valid!");
        this.mmsiTree=mmsiTree;

    }

    public String getTop(Object n, Object date1, Object date2) throws IOException {
        //Get Movements from Date Range
        List<Ship> list = (List<Ship>) mmsiTree.inOrder();
        for (Ship s:list) {
            map.put(s,s.getMoveByDateFrame(date1,date2));
        }
        //Get Kms Traveled
        TreeMap<Ship, Double> km = new TreeMap<>();
        for(Ship s:map.keySet()){
            km.put(s,s.getTravelDistanceDates(map.get(s)));
        }
        km.values().stream().sorted(); //assure kms are sorted
        TreeMap<Object, Pair<Double,Ship>> result = new TreeMap<>();
        for(Ship s: km.keySet()) {
            Pair<Double,Ship> value = new Pair<>(km.get(s),s);
            result.put(s.getVesselType(),value);
        }

        return printlist(n,result);
    }



    private String printlist(Object n, TreeMap<Object, Pair<Double, Ship>> map) {
        int a= (int) n;
        for(Object o:map.keySet()){
            System.out.println("From Vessel Type: "+o);
            for(Pair<Double, Ship> d:map.values()){
                d.get2nd().print(d.get1st());
                n=(int)n-1;
                if((int)n>0) break;
            }
            n=a;
        }
        return null;
    }


    public ShipTree getTree() {
        return mmsiTree;
    }
}
