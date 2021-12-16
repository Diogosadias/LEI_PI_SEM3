package lapr.project.model;

import java.awt.geom.Point2D;

public class City {
    private String name;
    private String country;
    private Point2D.Double coords;

    public City(String name,String country){
        this.name = name;
        this.country=country;
    }

    public City(String name,String country,Point2D.Double coords){
        this.name = name;
        this.country=country;
        this.coords=coords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public Point2D.Double getCoords() {
        return coords;
    }

    public void setCoords(Point2D.Double coords) {
        this.coords = coords;
    }
}
