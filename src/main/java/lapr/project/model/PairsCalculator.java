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
            List<Ship> values = (List<Ship>) map.get(s);
            TreeMap <Double,Ship> map1 = new TreeMap<>();
            for(Ship t:values){
                map1.put(t.getKm((Collection<List<TemporalMessages>>) t.getMovements()),t);
            }
            List<Ship> values1 =(List<Ship>) map.values();
            Collections.reverse(values1);
            for(Ship t :values1){
                System.out.print(print(s,t));
            }
        }
        return null;
    }

    private String print(Ship s, Ship t) {
        Pair<Double,Double> coor1 = (Pair<Double, Double>) s.getArrival();
        Pair<Double,Double> coor2 = (Pair<Double, Double>) t.getArrival();
        Pair<Double,Double> coor3 = (Pair<Double, Double>) s.getdeparture();
        Pair<Double,Double> coor4 = (Pair<Double, Double>) t.getdeparture();

        return s.getMMSI() +"\t" + t.getMMSI() + "\t" + s.dist(coor1.get1st(),coor1.get2nd(),coor2.get1st(),coor2.get2nd()) + "\t" + s.dist(coor3.get1st(),coor3.get2nd(),coor4.get1st(),coor4.get2nd()) +
                "\t" + s.getMovements().size() + "\t" + s.getKm((Collection<List<TemporalMessages>>) s.getMovements()) + "\t" + t.getMovements().size() + "\t" + t.getKm((Collection<List<TemporalMessages>>) t.getMovements()) + "\r\n";
    }
}
