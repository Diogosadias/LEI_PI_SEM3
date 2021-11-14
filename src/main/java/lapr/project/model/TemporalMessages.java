package lapr.project.model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TemporalMessages implements Comparable<TemporalMessages> {

    private LocalDateTime baseDateTime;
    private double latitude;
    private double longitude;
    private double sog;
    private double cog;
    private double heading;
    private String position;
    private String transceiverClass;

    public TemporalMessages() {
    }

    public TemporalMessages(LocalDateTime baseDateTime) throws IOException {
        if(baseDateTime==null) throw new IOException("Input is Invalid!") ;
        this.baseDateTime = baseDateTime;
    }

    public TemporalMessages(String s, double v, double v1, double v2, double v3, double i, String a, String b) throws IOException {
        this.baseDateTime = getDate(s);
        this.latitude = checkLatitude(v);
        this.longitude = checklongitude(v1);
        this.sog = v2;
        this.cog = checkCog(v3);
        this.heading = checkHeading(i);
        this.position = a;
        this.transceiverClass = b;

    }

    public TemporalMessages(LocalDateTime baseDateTime, double latitude, double longitude, double sog, double cog, double heading, String position, String transceiverClass) {
        this.baseDateTime = baseDateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.position=position;
        this.transceiverClass = transceiverClass;
    }

    private double checkHeading(double i) throws IOException {
        if (i < 0 || i > 359) {
            throw new IOException("not Available!");
        }
        return i;
    }

    private double checkCog(double v3) throws IOException {
        if (v3 < 0 || v3 > 359) {
            throw new IOException("not Available!");
        }
        return v3;
    }

    private double checklongitude(double v1) throws IOException {
        if (v1 < -180 || v1 > 180) {
            throw new IOException("not Available!");
        }
        return v1;
    }

    private double checkLatitude(double v) throws IOException {
        if (v < -90 || v > 90) {
            throw new IOException("not Available!");
        }
        return v;
    }

    public static LocalDateTime getDate(Object s) throws IOException {
        if (s == null) {
            throw new IOException("Input is Invalid!");
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse((String) s, format);
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

    public String printMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
        return baseDateTime.format(formatter) + "\t" + latitude + "\t" + longitude + "\t" + sog + "\t\t" + cog + "\t\t" + heading + "\t\t\t" + position + "\t\t\t" + transceiverClass;

    }

    @Override
    public int compareTo(TemporalMessages element) {
        return baseDateTime.compareTo(element.getBaseDateTime());
    }

}
