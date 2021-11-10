package lapr.project.model;

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

    public Ship(){
        MMSI = (MMSI) "too bad";
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

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Ship{" + "MMSI=" + MMSI + ", VesselName=" + VesselName + ", IMO=" + IMO + ", CallSign=" + CallSign + ", VesselType=" + VesselType + ", Length=" + Length + ", Width=" + Width + ", Draft=" + Draft + ", Cargo=" + Cargo + '}';
    }
    
}
