package lapr.project.model;

import java.awt.geom.Point2D;

public class City {
    private String name;
    private String country;
    private Point2D.Double coords;
    private String cont;

    public City(String name,String country){
        this.name = name;
        this.country=country;
    }

    public City(String name,String country,Point2D.Double coords){
        this.name = name;
        this.country=country;
        this.cont = "null";
        this.coords=coords;
    }

    public City(String name,String country,String cont,Point2D.Double coords){
        this.name = name;
        this.country=country;
        this.cont = cont;
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


    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String toString(){
        return "City [Name - "+name+ " ]" +
                "\n\t" + cont +
                "\n\t" + country +
                "\n[ " + coords.x + " ; " + coords.y + " ]";
    }
}
