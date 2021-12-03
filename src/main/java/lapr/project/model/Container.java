package lapr.project.model;

public class Container {
    private Integer id;
    private Double payload;
    private Double tare;
    private Double gross;

    public Container(Integer id,Double payload,Double tare,Double gross){
        this.id = id;
        this.payload = payload;
        this.tare = tare;
        this.gross = gross;
    }


    public String toString(){
        return "Container - "+ id +
                "\n\t" + payload + "kg - of PayLoad" +
                "\n\t" + tare + "kg - of Tare" +
                "\n\t" + gross + "kg - of Gross";
     }
}
