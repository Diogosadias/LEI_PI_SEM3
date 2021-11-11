package lapr.project.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ShipMovements implements Comparable<ShipMovements>{


    private LocalDateTime baseDateTime;
    private double latitude;
    private double longitude;
    private double sog;
    private double cog;
    private double heading;
    private String position;
    private String transceiverClass;

    public ShipMovements(String s, double v, double v1, double v2, double v3, double i, String a, String b) {
        this.baseDateTime=getDate(s);
        this.latitude=v;
        this.longitude=v1;
        this.sog=v2;
        this.cog=v3;
        this.heading=i;
        this.position=a;
        this.transceiverClass=b;

    }

    public static LocalDateTime getDate(Object s) {
        if(s == null) return null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse((CharSequence) s, format);
    }
    public LocalDateTime getBaseDateTime() {
        return baseDateTime;
    }

    public void setBaseDateTime(LocalDateTime baseDateTime) {
        this.baseDateTime = baseDateTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSog() {
        return sog;
    }

    public void setSog(double sog) {
        this.sog = sog;
    }

    public double getCog() {
        return cog;
    }

    public void setCog(double cog) {
        this.cog = cog;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTransceiverClass() {
        return transceiverClass;
    }

    public void setTransceiverClass(String transceiverClass) {
        this.transceiverClass = transceiverClass;
    }

    @Override
    public int compareTo(ShipMovements element) { return baseDateTime.compareTo(element.getDateTime());
    }

    private LocalDateTime getDateTime() {
        if(baseDateTime!=null) return baseDateTime;
        return null;
    }


}
