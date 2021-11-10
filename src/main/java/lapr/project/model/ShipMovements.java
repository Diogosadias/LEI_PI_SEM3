package lapr.project.model;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
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

    public static LocalDateTime getDate(String s) {
        if(s == null) return null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse(s, format);
        return date;
    }


    public int compareTo(ShipMovements element) { return baseDateTime.compareTo(element.getDateTime());
    }

    private LocalDateTime getDateTime() { return baseDateTime;
    }

    public void incOcorrences() {
    }
}
