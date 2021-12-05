package lapr.project.model;

import oracle.ucp.util.Pair;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Math.*;

public class Ship implements Comparable<Ship> {

    private String MMSI;
    private String VesselName;
    private String IMO;
    private String CallSign;
    private int VesselType;
    private int Length;
    private int Width;
    private double Draft;
    private String Cargo;
    private MovementsTree movements;
    private Double lat;
    private Double lon;

    public Ship() {

        this.VesselName = "too bad!";

    }

    public Ship(String MMSI, String VesselName, String IMO, String CallSign, int VesselType, int Length, int Width, double Draft, String Cargo) {
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

    public Ship(String toString, Double lat, Double lon) {
        this.MMSI = toString;
        this.lat=lat;
        this.lon=lon;
    }

    public String getMMSI() {
        return MMSI;
    }

    public void setMMSI(String MMSI) {
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

    public String getMoveByDate(Object s) {
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

    public String print(Double first) {

        return "\t" + MMSI + "\t" + CallSign + "\t" + IMO + "\t\t" + String.format("%.2f", first) + "\r\n";
    }

    public boolean checkproximity(Ship t) {
        if (t == null || t.getdeparture() == null || t.getArrival() == null) {
            return false;
        }
        if (this.MMSI == (t.getMMSI())) {
            return false;
        }
        if (distPorts(getdeparture(), t.getdeparture()) && distPorts(getArrival(), t.getArrival())) {
            if (t.getTravelledDistance() > 10 && getTravelledDistance() > 10) {
                return true;
            }
        }
        return false;
    }

    protected Pair<Double, Double> getArrival() {
        Pair<Double, Double> value = getMovements().getmin();
        return value;
    }

    boolean distPorts(Pair d, Pair a) {
        if (d == null || a == null) {
            return false;
        }
        if (dist(d.get1st(), d.get2nd(), a.get1st(), a.get2nd()) < 5) {
            return true;
        }
        return false;
    }

    protected Double dist(Object x1, Object y1, Object x2, Object y2) {
        Double constant = 6371.0;

        y1 = Math.toRadians((Double) y1);
        y2 = Math.toRadians((Double) y2);
        x1 = Math.toRadians((Double) x1);
        x2 = Math.toRadians((Double) x2);
        Double dlat = (Double) x2 - (Double) x1;

        Double dlon = (Double) y2 - (Double) y1;
        Double a = sin(dlat / 2) * sin(dlat / 2) + cos((Double) x1) - cos((Double) x2) + sin(dlon / 2) * sin(dlon / 2);
        Double a1 = 1.0 - a;
        if (a < 0) {
            return dist(Math.toDegrees((Double) x2), Math.toDegrees((Double) y2), Math.toDegrees((Double) x1), Math.toDegrees((Double) y1));
        }
        Double c = 2 * atan2(sqrt(a), sqrt(a1));
        return constant * c;
    }

    protected Pair<Double, Double> getdeparture() {
        Pair<Double, Double> value = getMovements().getmax();
        return value;
    }

    public int compareToMMSI(Ship o) {
        return this.MMSI.compareTo(o.MMSI);
    }

    public int compareToIMO(Ship o) {
        return this.IMO.compareTo(o.getIMO());
    }

    public int compareToCallSign(Ship o) {
        return this.CallSign.compareTo(o.getCallSign());
    }

    public String getSummary(Object code) {
        return "The Ship with code " + code + " has:\n" + "\n" + "VesselName=" + VesselName + "\n" + "Start BaseDateTime=" + getStartBaseDateTime() + "\n" + "End BaseDateTime=" + getEndBaseDateTime() + "\n" + "TotalMovementTime" + getTotalMovementTime() + "\n" + "TotalNumberOfMovements" + getTotalNumberOfMovements() + "\n" + "MaxSOG" + getMaxSOG() + "\n" + "MeanSOG" + getMeanSOG() + "\n" + "MaxCOG=" + getMaxCOG() + "\n" + "MeanCOG=" + getMeanCOG() + "\n" + "DepartureLatitude" + getDepartureLatitude() + "\n" + "DepartureLongitude" + getDepartureLongitude() + "\n" + "ArrivalLatitude=" + getArrivalLatitude() + "\n" + "ArrivalLongitude=" + getArrivalLongitude() + "\n" + "TravelledDistance=" + "\n" + getTravelledDistance() + "\n" + "DeltaDistance=" + getDeltaDistance() + "\n" + "\n";
    }

    public double getMeanCOG() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        double somaCOG = list.get(0).getCog();
        int i = 1;
        while (i < list.size()) {
            somaCOG = somaCOG + list.get(i).getCog();
            i++;
        }
        double meanCOG = somaCOG / list.size();
        return meanCOG;
    }

    public double getMaxCOG() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        double maxCOG = list.get(0).getCog();
        int i = 1;

        while (i < list.size()) {
            TemporalMessages atual = list.get(i);
            if (atual.getCog() > maxCOG) {
                maxCOG = atual.getCog();
            }
            i++;
        }
        return maxCOG;
    }

    public double getMeanSOG() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        double somaSOG = list.get(0).getSog();
        int i = 1;
        while (i < list.size()) {
            somaSOG = somaSOG + list.get(i).getSog();
            i++;
        }
        double meanSOG = somaSOG / list.size();
        return meanSOG;
    }

    public double getMaxSOG() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        double maxSOG = list.get(0).getSog();
        int i = 1;
        while (i < list.size()) {
            TemporalMessages atual = list.get(i);
            if (atual.getSog() > maxSOG) {
                maxSOG = atual.getSog();
            }
            i++;
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

    public int getTotalNumberOfMovements() {

        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return list.size();
    }

    public Duration getTotalMovementTime() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        Duration duration = Duration.between(list.get(0).getBaseDateTime(), list.get(list.size() - 1).getBaseDateTime());
        return duration;

    }

    public Double getDeltaDistance() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        return dist(list.get(0).getLatitude(), list.get(0).getLongitude(), list.get(list.size() - 1).getLatitude(), list.get(list.size() - 1).getLongitude());
    }

    public Double getTravelledDistance() {
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        if (list.size() < 2) {
            return 0.0;
        }
        double sum = dist(list.get(0).getLatitude(), list.get(0).getLongitude(), list.get(1).getLatitude(), list.get(1).getLongitude());
        int i = 2;
        while (i < list.size()) {
            sum = sum + dist(list.get(i - 1).getLatitude(), list.get(i - 1).getLongitude(), list.get(i).getLatitude(), list.get(i).getLongitude());
            i++;
        }
        return sum;
    }

    public Double getTravelDistanceDates(List<TemporalMessages> messages) {
        List<TemporalMessages> list = messages;
        if (messages.size() < 2) {
            return 0.0;
        }
        double sum = dist(list.get(0).getLatitude(), list.get(0).getLongitude(), list.get(1).getLatitude(), list.get(1).getLongitude());
        int i = 2;
        while (i < list.size()) {
            sum = sum + dist(list.get(i - 1).getLatitude(), list.get(i - 1).getLongitude(), list.get(i).getLatitude(), list.get(i).getLongitude());
            i++;
        }
        return sum;
    }

    public double[] getCoordsbyBaseDateTime(Object date) {
        double array[] = new double[2];
        List<TemporalMessages> list = (List<TemporalMessages>) movements.inOrder();
        int i = 0;
        while (i < list.size()) {
            if (list.get(i).getBaseDateTime() == date) {
                array[0] = list.get(i).getLatitude();
                array[1] = list.get(i).getLongitude();
            }
            i++;
        }
        return array;
    }

    @Override
    public int compareTo(Ship o) {
        return compareToMMSI(o);
    }

    public String toStringwPosition() {
        return "The Ship with code " + MMSI + " Located:\n" +  "Latitude =" + lat + "\n" + "Longitude =" + lon;
    }
}
