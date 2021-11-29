package lapr.project.controller;

import lapr.project.model.MMSTree;
import lapr.project.model.PairsCalculator;
import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TrafficManagerControllerTest {
    private String code1="211331640";
    private String code2= "DHBN";
    private String code3="IMO9193305";
    private String date= "31/12/2020 17:19";
    private String date2= "30/01/2021 17:19";
    private int n=5;
    TrafficManagerController trafficManagerController = new TrafficManagerController();

    TrafficManagerControllerTest() throws IOException {
    }


    /***
     * Test if number of n is bigger than number ofships
     */
    @Test
    public void insurefailN() throws IOException {
        try{
            MMSTree mmsiTree = new MMSTree();
            trafficManagerController.setMmsiTree(mmsiTree);
            trafficManagerController.getTopN(n,date,date2);

        }catch (UnsupportedOperationException | IOException ex){
            System.out.println("Ships are not enough to fulfill requirement!");
        }
        try{
            MMSTree mmsiTree = new MMSTree();
            trafficManagerController.setMmsiTree(mmsiTree);
            trafficManagerController.getTopN(0,date,date2);

        }catch (UnsupportedOperationException | IOException ex){
            System.out.println("Ships are not enough to fulfill requirement!");
        }
        try{
            MMSTree mmsiTree = new MMSTree();
            trafficManagerController.setMmsiTree(mmsiTree);
            trafficManagerController.getTopN(1,date,date2);

        }catch (UnsupportedOperationException | IOException ex){
            System.out.println("Ships are not enough to fulfill requirement!");
        }
        MMSTree mmsTree = new MMSTree();
        Ship s = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        mmsTree.insert(s);
        trafficManagerController.setMmsiTree(mmsTree);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        trafficManagerController.getTopN(1,date,date2);
        String expectResult = outContent.toString();
        assertEquals(expectResult,outContent.toString());

    }


    @Test
    void ensureSearchDetailsisNotNull() throws IOException {

        TrafficManagerController main = new TrafficManagerController();
        //Act
        //Assert
        try {
            main.searchDetails(null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }

    @Test
    void ensureSearchDateisNotNull() throws IOException {

        TrafficManagerController main = new TrafficManagerController();
        //Act
        //Assert
        try {
            main.searchDate(null, null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }

    @Test
    void ensureSearchDate2isNotNull() throws IOException {

        TrafficManagerController main = new TrafficManagerController();
        //Act
        //Assert
        try {
            main.searchDate(null, null, null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }

    @Test
    void ensureSummaryisNotNull() throws IOException {

        TrafficManagerController main = new TrafficManagerController();
        //Act
        //Assert
        try {
            main.summary(null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }

    @Test
    void ensureGetTopNisNotNull() throws IOException {

        TrafficManagerController main = new TrafficManagerController();
        //Act
        //Assert
        try {
            main.getTopN(null, null, null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }

    @Test
    void testimportFile() {
    }

    @Test
    void testsearchDetails() {
    }

    @Test
    void testsearchDate() {
    }

    @Test
    void testSearchDate() {
    }

    @Test
    void testsummary() {

    }



    @Test
    void testpairsofShips() {
        MMSTree tree = new MMSTree();
        PairsCalculator pc = new PairsCalculator(tree);
        String test = pc.pairs();
        assertEquals(test,pc.pairs());
    }
}