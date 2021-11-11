package lapr.project.model;


import lapr.project.utils.PL.BST;

import java.io.IOException;
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



}
