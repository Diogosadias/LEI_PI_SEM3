package lapr.project.model;

public class Container {
    private Integer id;
    private Double payload;
    private Integer x;
    private Integer y;
    private Integer z;
    private Double temp;

    public Container(Integer id,Double payload,Integer x,Integer y,Integer z,Double temp){
        this.id = id;
        this.payload = payload;
        this.x = x;
        this.y = y;
        this.z=z;
        this.temp = temp;
    }

    public Container(Integer container_id, Double payload, Double temp) {
        this.id = container_id;
        this.payload = payload;
        this.temp = temp;
    }


    public String toString(){
        return "Container - "+ id +
                "\n\t" + payload + "kg - of PayLoad" +
                "\n\t(" + x + "," + y + "," +z +") - Position" +
                "\n\t" + temp + " ºC - of Temperature"  ;
     }

    public String toStringwPosition(){
        return "Container - "+ id +
                "\n\t" + payload + "kg - of PayLoad" +
                "\n\t" + temp + " ºC - of Temperature"  ;
    }
}
