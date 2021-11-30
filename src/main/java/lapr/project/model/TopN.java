package lapr.project.model;




import lapr.project.controller.TrafficManagerController;
import oracle.ucp.util.Pair;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;

import static lapr.project.model.TemporalMessages.getDate;

public class TopN {

    private final TreeMap<Ship, List<TemporalMessages>> map = new TreeMap<>();

    private String s = "Input is Invalid!";
    private String s2 ="-------------------------------------------------------------------------------------------------";

    private final ShipTree mmsiTree;

    public TopN(ShipTree mmsiTree) throws IOException {
        if(mmsiTree==null) throw new IOException("Tree is not Valid!");
        this.mmsiTree=mmsiTree;

    }

    public String getTop(Object n, LocalDateTime date1, LocalDateTime date2) throws IOException {
        //Get Movements from Date Range
        List<Ship> list = (List<Ship>) mmsiTree.inOrder();
        for (Ship s:list) {
            map.put(s,s.getMovements().find(date1,date2));
        }
        //Get Kms Traveled
        List<Pair<Ship, Double>> km = new ArrayList<>();
        for(Ship s:map.keySet()){
            km.add(new Pair<>(s,s.getTravelDistanceDates(map.get(s))));
        }

        List<Object> first = new ArrayList<>();
        List <Pair<Ship,Double>> second = new ArrayList<>();
        List<Pair<Ship,Double>> topn = new ArrayList<>();
        for(int i = 0; i<km.size();){
            Pair <Ship,Double> t = getmax(km);
            topn.add(t);
            km.remove(getmax(km));
        }
        Collections.reverse(topn);

        for(Pair<Ship,Double> s: topn) {
            Pair<Ship,Double> value = s;
            if(!(first.contains(s.get1st().getVesselType()))) first.add(s.get1st().getVesselType());
            second.add(value);
        }

        return printlist(n,first,second);
    }

    private Pair<Ship, Double> getmax(List<Pair<Ship, Double>> km) {
        Double max =km.get(0).get2nd();
        int l=0;
        for (int i = 1; i < km.size(); i++) {
            if(km.get(i).get2nd()>max){
                max = km.get(i).get2nd();
                l=i;
            }
        }
        return km.get(l);
    }


    private String printlist(Object n, List<Object> map, List<Pair<Ship, Double>> jo) {
        int a= (int) n;
        List h=  map;
        List <Pair<Ship,Double>> l = (List<Pair<Ship,Double>>) jo;
        List<Pair<Ship,Double>> topn = new ArrayList<>();
        for(int i = 0; i<l.size();){
            Pair <Ship,Double> t = getmax(l);
            topn.add(t);
            l.remove(getmax(l));
        }
        l=topn;

        for(int i = 0 ; i<h.size();i++){
            System.out.println("From Vessel Type: "+h.get(i));
            for (int j = 0;j<l.size();j++){
            if(h.get(i).equals(l.get(j).get1st().getVesselType())) {
                System.out.print(l.get(j).get1st().print(l.get(j).get2nd()));
                n = (int) n - 1;
                if ((int) n == 0) break;
            }
            }
            n=a;
        }
        String t = "";
        return t;
    }


    public ShipTree getTree() {
        return mmsiTree;
    }

    public String getTopNString(Object n, String date1, String date2) throws IOException {
        if (n == null) { throw new IOException(s);}
        if ((int) n > mmsiTree.size()) {
            throw new UnsupportedOperationException("Ships are not enough to fulfill requirement!");
        }
        return getTop(n,  getDate(date1),  getDate(date2)) + s2;
    }
}
