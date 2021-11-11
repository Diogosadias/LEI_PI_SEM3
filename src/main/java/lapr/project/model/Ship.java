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
public class Ship<MMSI, VesselName, IMO, CallSign, VesselType, Length, Width, Draft, Cargo> implements Comparable {

    private MMSI MMSI;
    private VesselName VesselName;
    private IMO IMO;
    private CallSign CallSign;
    private VesselType VesselType;
    private Length Length;
    private Width Width;
    private Draft Draft;
    private Cargo Cargo;
    private MovementsTree movements = new MovementsTree();
    private String meanSOG;


    public Ship() {

        MMSI = (MMSI) "too bad";
    }

    public Ship(MMSI MMSI, VesselName VesselName, IMO IMO, CallSign CallSign, VesselType VesselType, Length Length, Width Width, Draft Draft, Cargo Cargo) {
        this.MMSI = MMSI;
        this.VesselName = VesselName;
        this.IMO = IMO;
        this.CallSign = CallSign;
        this.VesselType = VesselType;
        this.Length = Length;
        this.Width = Width;
        this.Draft = Draft;
        this.Cargo = Cargo;
    }
    

    

    public MMSI getMMSI() {
        return MMSI;
    }

    public void setMMSI(MMSI MMSI) {
        this.MMSI = MMSI;
    }

    public VesselName getVesselName() {
        return VesselName;
    }

    public void setVesselName(VesselName VesselName) {
        this.VesselName = VesselName;
    }

    public IMO getIMO() {
        return IMO;
    }

    public void setIMO(IMO IMO) {
        this.IMO = IMO;
    }

    public CallSign getCallSign() {
        return CallSign;
    }

    public void setCallSign(CallSign CallSign) {
        this.CallSign = CallSign;
    }

    public VesselType getVesselType() {
        return VesselType;
    }

    public void setVesselType(VesselType VesselType) {
        this.VesselType = VesselType;
    }

    public Length getLength() {
        return Length;
    }

    public void setLength(Length Length) {
        this.Length = Length;
    }

    public Width getWidth() {
        return Width;
    }

    public void setWidth(Width Width) {
        this.Width = Width;
    }

    public Draft getDraft() {
        return Draft;
    }

    public void setDraft(Draft Draft) {
        this.Draft = Draft;
    }

    public Cargo getCargo() {
        return Cargo;
    }

    public void setCargo(Cargo Cargo) {
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

    public void print(Object code,List<TemporalMessages> list){
        System.out.println(code);
        movements.printMoves(list);
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Ship{" + "MMSI=" + MMSI + ", VesselName=" + VesselName + ", IMO=" + IMO + ", CallSign=" + CallSign + ", VesselType=" + VesselType + ", Length=" + Length + ", Width=" + Width + ", Draft=" + Draft + ", Cargo=" + Cargo + '}';
    }


    public Double getKm(Collection<List<TemporalMessages>> values) {
        return null;
    }

    public String print(Double first) {
        return  "\t"+MMSI + "\t" + CallSign + "\t" + IMO + "\t" + meanSOG + "\t\t" + first+"\r\n";
    }

    public boolean checkproximity(Ship t) {
        if(MMSI.equals(t.getMMSI())) return false;
        if(dist(getdeparture(),t.getdeparture()) && dist(getArrival(),t.getArrival())){
            if(getKm((Collection<List<TemporalMessages>>) t.getMovements())>10 && getKm((Collection<List<TemporalMessages>>) getMovements())>10){
                return true;
            }
        }
        return false;
    }

    private boolean dist(Object getdeparture, Object getdeparture1) {
        return dist(getdeparture,getdeparture1);
    }

    protected Object getArrival() {
        Pair<Double,Double> value = getMovements().getmin();
        return value;
    }

    private boolean dist(Pair d, Pair a) {
        if(dist(d.get1st(),d.get2nd(),a.get1st(),a.get2nd())<5) return true;
        return false;
    }

    protected Double dist(Object x1, Object y1, Object x2, Object y2) {
        return Math.sqrt(((Double)y2 - (Double)y1) * ((Double)y2 - (Double)y1) + ((Double)x2 - (Double)x1) * ((Double)x2 - (Double)x1));
    }

    protected Object getdeparture() {
        Pair<Double,Double> value = getMovements().getmax();
        return value;
    }
}
