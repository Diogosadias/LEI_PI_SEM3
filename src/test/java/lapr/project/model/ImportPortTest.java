package lapr.project.model;

import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ImportPortTest {
    /***
     * Test not Success and Success - importPort will return String to be printed in output file
     */
    @Test
    public void testFilesOutput(){
        /*
        String fileresult = importPort(null);
        assertEquals("The Program has encountered a problem. Ports were not successfully imported.",fileresult.toString());
        fileresult = importPort("Data/data-ships&ports/sports.csv");
        assertEquals("Ports were successfully imported!",fileresult.toString());

         */
    }

    /***
     * Test if Port is valid
     */
    @Test
    public void portValidity() throws IOException {

        try{
            new Port(null,"United Kingdom",29002,"Liverpool",53.46666667,-3.033333333);
        }catch (IOException ex){
            System.out.println("Input is Invalid!");
        }
        try{
            new Port("Europe",null,29002,"Liverpool",53.46666667,-3.033333333);
        }catch (IOException ex){
            System.out.println("Input is Invalid!");
        }
        try{
            new Port("Europe","United Kingdom",null,"Liverpool",53.46666667,-3.033333333);
        }catch (IOException ex){
            System.out.println("Input is Invalid!");
        }
        try{
            new Port("Europe","United Kingdom",29002,null,53.46666667,-3.033333333);
        }catch (IOException ex){
            System.out.println("Input is Invalid!");
        }
        try{
            new Port("Europe","United Kingdom",29002,"Liverpool", (double) -91,-3.033333333);
        }catch (IOException ex){
            System.out.println("not Available!");
        }
        try{
            new Port("Europe","United Kingdom",29002,"Liverpool",53.46666667, 181.0);
        }catch (IOException ex){
            System.out.println("not Available!");
        }

        Port port = new Port("Europe","United Kingdom",29002,"Liverpool",53.46666667,-3.033333333);
        assertTrue(port.isValid());

    }

    /***
     * Test if 2DTree is well constructed and works for Ports
     */
    @Test
    public void testPortTree(){
        /*

        PortTree expectedResult = new ArrayList<>();
        Add ports
        PortTree portTree = new PortTree();
        Port port = new Port("Europe","United Kingdom",29002,"Liverpool",53.46666667,-3.033333333);
        portTree.insert(port);
        assertEquals(portTree.root.getElement(),port);
        Port portx1 = new Port("Europe","United Kingdom",12345,"Lol",60,-3.033333333);
        Port portx2 = new Port("Europe","United Kingdom",23456,"LEL",50,-3.033333333);
        portTree.insert(portx1);
        assertEquals(portTree.root.getRigth().getElement(),portx1);
        portTree.insert(portx2);
        assertEquals(portTree.root.getLeft().getElement(),portx2);

        Port porty1 = new Port("Europe","United Kingdom",34567,"London",61,5);
        Port porty2 = new Port("Europe","United Kingdom",45678,"Jupiter",61,-5);
        portTree.insert(porty1);
        portTree.insert(porty2);
        assertEquals(portTree.root.getRigth().getRigth().getElement(),porty1);
        assertEquals(portTree.root.getRigth().getLeft().getElement(),porty2);


        Port portxy1 = new Port("Europe","United Kingdom",56789,"PORTO",40,5);
        Port portxy2 = new Port("Europe","United Kingdom",67890,"Peru",40,-5);
        portTree.insert(porty1);
        portTree.insert(porty2);
        assertEquals(portTree.root.getLeft().getRigth().getElement(),portxy1);
        assertEquals(portTree.root.getLeft().getLeft().getElement(),portxy2);


        assertEquals(portTree.inOrder(),expectedResult);


         */

    }

    /***
     * Test if Ports were added to Database
     */
    @Test
    public void tesPortsDatabse(){

    }
}
