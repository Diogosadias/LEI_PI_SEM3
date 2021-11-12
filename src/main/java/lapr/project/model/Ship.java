package lapr.project.model;

import oracle.ucp.util.Pair;
import lapr.project.utils.PL.BST;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Ship Class
 *
 * @author Diogo Dias {@literal <1161605@isep.ipp.pt>} on 10/11/2021.
 */
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
    private String meanSOG;

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

    public String getMeanSOG() {
        return meanSOG;
    }

    public void setMeanSOG(String meanSOG) {
        this.meanSOG = meanSOG;
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
        return "\t" + MMSI + "\t" + CallSign + "\t" + IMO + "\t" + meanSOG + "\t\t" + first + "\r\n";
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

    public String  getSummary() {
        return MMSI +"\t"+ VesselName + "\t"+ getMeanSOG();
    }
}
