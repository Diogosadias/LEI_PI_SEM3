package lapr.project.model;

import oracle.ucp.util.Pair;

import java.util.*;

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
        System.out.println("Ship1 MMSI \tShip2 MMSI \tdistOrig \tdistDest \tMovs \tTravelDist \tMovs \tTravelDist");
        for(Ship s:map.keySet()){
            List<Ship> values = Collections.singletonList(map.get(s));
            TreeMap <Double,Ship> map1 = new TreeMap<>();
            for(Ship t:values){
                map1.put(t.getTravelledDistance(),t);
            }
            List<Ship> values1 = new ArrayList<>(map.values());
            values.stream().sorted();
            Collections.reverse(values1);
            for(Ship t :values1){
                if(!(t.equals(s)))
                System.out.print(print(s,t));
            }
        }
        String t="";
        return t;
    }

    private String print(Ship s, Ship t) {
        Pair<Double,Double> coor1 =  s.getArrival();
        Pair<Double,Double> coor2 =  t.getArrival();
        Pair<Double,Double> coor3 =  s.getdeparture();
        Pair<Double,Double> coor4 =  t.getdeparture();

        return s.getMMSI() +"\t" + t.getMMSI() + "\t" + String.format("%.2f",s.dist(coor1.get1st(),coor1.get2nd(),coor2.get1st(),coor2.get2nd())) + "\t" + String.format("%.2f",s.dist(coor3.get1st(),coor3.get2nd(),coor4.get1st(),coor4.get2nd())) +
                "\t" + s.getMovements().size() + "\t" + String.format("%.2f",s.getTravelledDistance()) + "\t" + t.getMovements().size() + "\t" + String.format("%.2f",t.getTravelledDistance()) + "\r\n";
    }
}
