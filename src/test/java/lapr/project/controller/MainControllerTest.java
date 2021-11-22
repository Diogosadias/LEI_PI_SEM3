package lapr.project.controller;

import lapr.project.model.MMSTree;
import lapr.project.model.Ship;
import lapr.project.model.ShipTree;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static lapr.project.model.TemporalMessages.getDate;

class MainControllerTest {
    private String code1="211331640";
    private String code2= "DHBN";
    private String code3="IMO9193305";
    private String date= "31/12/2020 17:19";
    private String date2= "30/01/2021 17:19";
    private int n=5;
    MainController mainController = new MainController();

    MainControllerTest() throws IOException {
    }


    /***
     * Test if number of n is bigger than number ofships
     */
    @Test
    public void insurefailN(){
        try{
            MMSTree mmsiTree = new MMSTree();
            mainController.setMmsiTree(mmsiTree);
            mainController.getTopN(n,date,date2);

        }catch (UnsupportedOperationException | IOException ex){
            System.out.println("Ships are not enough to fulfill requirement!");
        }
        try{
            MMSTree mmsiTree = new MMSTree();
            mainController.setMmsiTree(mmsiTree);
            mainController.getTopN(0,date,date2);

        }catch (UnsupportedOperationException | IOException ex){
            System.out.println("Ships are not enough to fulfill requirement!");
        }

    }

    @Test
    public void testimportFile() {
    }

    @Test
    public void testsearchDetails() {
    }

    @Test
    public void testsearchDate() {
    }

    @Test
    public void testSearchDate() {
    }

    @Test
    public void testsummary() {
    }

    @Test
    public void testgetTopN() {
    }

    @Test
    public void testpairsofShips() {
    }
}