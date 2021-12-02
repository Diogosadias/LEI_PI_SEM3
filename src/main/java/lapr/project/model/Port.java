package lapr.project.model;

import java.awt.geom.Point2D;
import java.io.IOException;

import static lapr.project.model.TemporalMessages.*;

public class Port implements Comparable<Port> {
    private String cont;
    private String country;
    private Integer code;
    private String city;
    private Double lat;
    private Double lon;
    private Point2D.Double coords;

    public Port(String cont, String country, Integer code, String city, Double lat, Double lon) throws IOException {
        this.cont = checkContinent(cont);
        this.country = checkCountry(country);
        this.code = checkCode(code);
        this.city = checkCity(city);
        this.lat = checkLatitude(lat);
        this.lon = checklongitude(lon);
        if(lon==null || lat==null) return;
        this.coords =  new Point2D.Double(lat,lon);

    }

    private Integer checkCode(Integer code) throws IOException {
        if(code == null) throw new IOException("Input is Invalid!");
        return code;    }

    private String checkCity(String city) throws IOException {
        if(city == null) throw new IOException("Input is Invalid!");
        return city;
    }

    private String checkCountry(String country) throws IOException {
        if(country == null) throw new IOException("Input is Invalid!");
        return country;
    }

    private String checkContinent(String cont) throws IOException {
        if(cont == null) throw new IOException("Input is Invalid!");
        return cont;
    }

    public boolean isValid() {
        if(coords!=null )
            if(cont!=null)
                if(country!=null)
                    if(code!=null)
                        if(city!=null)
                            return true;
        return false;
    }

    public Point2D.Double getCoords() {
        return coords;
    }



    public int compareTo(Port o) {
        return this.code.compareTo(o.code);
    }

    public void setCode(int i) {
        this.code=i;
    }

    public int getCode() { return code;
    }


    public String getCont() { return cont;
    }

    public String getCountry() { return country;
    }

    public String getLocation() { return city;
    }
}
