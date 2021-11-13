package lapr.project.model;

import oracle.ucp.util.Pair;
import lapr.project.utils.PL.BST;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class Ship implements Comparable<Ship> {

    private int MMSI;
    private String VesselName;
    private String IMO;
    private String CallSign;
    private int VesselType;
    private int Length;
    private int Width;
    private double Draft;
    private String Cargo;
    private MovementsTree movements;

    public Ship() {

        this.VesselName = "too bad!";

    }

    public Ship(int MMSI, String VesselName, String IMO, String CallSign, int VesselType, int Length, int Width, double Draft, String Cargo) {
        this.MMSI = MMSI;
        this.VesselName = VesselName;
        this.IMO = IMO;
        this.CallSign = CallSign;
        this.VesselType = VesselType;
        this.Length = Length;
        this.Width = Width;
        this.Draft = Draft;
        this.Cargo = Cargo;
        this.movements = new MovementsTree();
    }

    public int getMMSI() {
        return MMSI;
    }

    public void setMMSI(int MMSI) {
        this.MMSI = MMSI;
    }

    public String getVesselName() {
        return VesselName;
    }

    public void setVesselName(String VesselName) {
        this.VesselName = VesselName;
    }

    public String getIMO() {
        return IMO;
    }

    public void setIMO(String IMO) {
        this.IMO = IMO;
    }

    public String getCallSign() {
        return CallSign;
    }

    public void setCallSign(String CallSign) {
        this.CallSign = CallSign;
    }

    public int getVesselType() {
        return VesselType;
    }

    public void setVesselType(int VesselType) {
        this.VesselType = VesselType;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int Length) {
        this.Length = Length;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public double getDraft() {
        return Draft;
    }

    public void setDraft(double Draft) {
        this.Draft = Draft;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public MovementsTree getMovements() {
        return movements;
    }

    public void setMovements(MovementsTree movements) {
        this.movements = movements;
    }

    public List<TemporalMessages> getMoveByDateFrame(Object s, Object s1) throws IOException {
        return this.movements.searchDateFrame(s, s1);

    }

    public List<TemporalMessages> getMoveByDate(Object s) {
        return this.movements.getMoveByDate(s);
    }

    public void print(Object code, List<TemporalMessages> list) {
        System.out.println(code);
        movements.printMoves(list);
    }

    @Override
    public String toString() {
        return "Ship{" + "MMSI=" + MMSI + ", VesselName=" + VesselName + ", IMO=" + IMO + ", CallSign=" + CallSign + ", VesselType=" + VesselType + ", Length=" + Length + ", Width=" + Width + ", Draft=" + Draft + ", Cargo=" + Cargo + '}';
    }

    public Double getKm(Collection<List<TemporalMessages>> values) {
        return null;
    }

    public String print(Double first) {
        return "\t" + MMSI + "\t" + CallSign + "\t" + IMO + "\t\t" + first + "\r\n";
    }

    public boolean checkproximity(Ship t) {
        if (this.MMSI == (t.getMMSI())) {
            return false;
        }
        if (dist(getdeparture(), t.getdeparture()) && dist(getArrival(), t.getArrival())) {
            if (getKm((Collection<List<TemporalMessages>>) t.getMovements()) > 10 && getKm((Collection<List<TemporalMessages>>) getMovements()) > 10) {
                return true;
            }
        }
        return false;
    }

    private boolean dist(Object getdeparture, Object getdeparture1) {
        return dist(getdeparture, getdeparture1);
    }

    protected Object getArrival() {
        Pair<Double, Double> value = getMovements().getmin();
        return value;
    }

    private boolean dist(Pair d, Pair a) {
        if (dist(d.get1st(), d.get2nd(), a.get1st(), a.get2nd()) < 5) {
            return true;
        }
        return false;
    }

    protected Double dist(Object x1, Object y1, Object x2, Object y2) {
        return Math.sqrt(((Double) y2 - (Double) y1) * ((Double) y2 - (Double) y1) + ((Double) x2 - (Double) x1) * ((Double) x2 - (Double) x1));
    }

    protected Object getdeparture() {
        Pair<Double, Double> value = getMovements().getmax();
        return value;
    }

    @Override
    public int compareTo(Ship o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSummary() {
        return "Ship{" + " VesselName=" + VesselName + ", Start BaseDateTime=" + getStartBaseDateTime() + ", End BaseDateTime=" + getEndBaseDateTime() + ", TotalMovementTime" + getTotalMovementTime() + ", TotalNumberOfMovements" + getTotalNumberOfMovements() + ", MaxSOG" + getMaxSOG() + ", MeanSOG" + getMeanSOG() + ", MaxCOG=" + getMaxCOG()+ ", MeanCOG=" + getMeanCOG()+ ", DepartureLatitude" + getDepartureLatitude()+ ", DepartureLongitude" + getDepartureLongitude()+ ", ArrivalLatitude=" + getArrivalLatitude()+ ", ArrivalLongitude=" + getArrivalLongitude()+ ", TravelledDistance=" + getTravelledDistance()+ ", DeltaDistance=" + getDeltaDistance() + '}';
    }

    public double getMeanCOG() {
            List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        double somaCOG = list.get(0).getCog();

        while (list.iterator().hasNext()) {
            somaCOG = somaCOG + list.iterator().next().getCog();
        }
        double meanCOG = somaCOG / list.size();
        return meanCOG;
    }

    public double getMaxCOG() {
            List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        double maxCOG = list.get(0).getCog();

        while (list.iterator().hasNext()) {
            TemporalMessages atual = list.iterator().next();
            if (atual.getCog() > maxCOG) {
                maxCOG = atual.getCog();
            }
        }
        return maxCOG;
    }

    public double getMeanSOG() {
            List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        double somaSOG = list.get(0).getSog();

        while (list.iterator().hasNext()) {
            somaSOG = somaSOG + list.iterator().next().getSog();
        }
        double meanSOG = somaSOG / list.size();
        return meanSOG;
    }

    public double getMaxSOG() {
            List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        double maxSOG = list.get(0).getSog();

        while (list.iterator().hasNext()) {
            TemporalMessages atual = list.iterator().next();
            if (atual.getSog() > maxSOG) {
                maxSOG = atual.getSog();
            }
        }
        return maxSOG;
    }

    public double getDepartureLatitude() {
            List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return list.get(0).getLatitude();

    }

    public double getDepartureLongitude() {
            List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return list.get(0).getLongitude();

    }

    public double getArrivalLongitude() {
            List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return list.get(list.size() - 1).getLongitude();

    }

    public double getArrivalLatitude() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return list.get(list.size() - 1).getLatitude();

    }

    public LocalDateTime getStartBaseDateTime() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return list.get(0).getBaseDateTime();

    }

    public LocalDateTime getEndBaseDateTime() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return list.get(list.size() - 1).getBaseDateTime();
    }

    public double getTotalNumberOfMovements() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return list.size();
    }

    public int getTotalMovementTime() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return list.get(list.size() - 1).getBaseDateTime().toLocalTime().toSecondOfDay() - list.get(0).getBaseDateTime().toLocalTime().toSecondOfDay();

    }
    public Double getDeltaDistance(){
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return dist(list.get(0).getLatitude(), list.get(0).getLongitude(), list.get(list.size()-1).getLatitude(), list.get(list.size()-1).getLongitude());
    }
    
    public Double getTravelledDistance(){
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        double sum = dist(list.get(0).getLatitude(), list.get(0).getLongitude(), list.get(1).getLatitude(), list.get(1).getLongitude());
        int i=2;
        while (i<list.size()) {
            sum = sum + dist(list.get(i-1).getLatitude(), list.get(i-1).getLongitude(), list.get(i).getLatitude(), list.get(i).getLongitude());
            i++;
        }
        return sum;
    }
}
    
