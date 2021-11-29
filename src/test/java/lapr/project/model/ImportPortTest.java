package lapr.project.model;


import lapr.project.utils.PL.KDTree;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ImportPortTest {
    /***
     * Test not Success and Success - importPort will return String to be printed in output file
     */
    @Test
    public void testFilesOutput() throws IOException {
        PortManager portManager = new PortManager();
        String fileresult = portManager.importPort(null);
        assertEquals("The Program has encountered a problem. Ports were not successfully imported.",fileresult.toString());
        fileresult = portManager.importPort("Data/data-ships&ports/sports.csv");
        assertEquals("Ports were successfully imported!",fileresult.toString());

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
    public void testPortTree() throws IOException {



        PortTree portTree = new PortTree();
        Port port = new Port("Europe","United Kingdom",29002,"Liverpool",53.46666667,-3.033333333);
        portTree.insert(port);
        assertEquals(portTree.root().getInfo(),port);
        Port portx1 = new Port("Europe","United Kingdom",12345,"Lol", 60.0,-3.033333333);
        Port portx2 = new Port("Europe","United Kingdom",23456,"LEL", 50.0,-3.033333333);
        portTree.insert(portx1);
        assertEquals(portTree.root().getRight().getInfo(),portx1); //rigth
        portTree.insert(portx2);
        assertEquals(portTree.root().getLeft().getInfo(),portx2); //left

        Port porty1 = new Port("Europe","United Kingdom",34567,"London",61.0,5.0);
        Port porty2 = new Port("Europe","United Kingdom",45678,"Jupiter",61.0,-5.0);
        portTree.insert(porty1);
        portTree.insert(porty2);
        assertEquals(portTree.root().getRight().getRight().getInfo(),porty1);
        assertEquals(portTree.root().getRight().getLeft().getInfo(),porty2);


        Port portxy1 = new Port("Europe","United Kingdom",56789,"PORTO",40.0,5.0);
        Port portxy2 = new Port("Europe","United Kingdom",67890,"Peru",40.0,-5.0);
        portTree.insert(portxy1);
        portTree.insert(portxy2);
        assertEquals(portTree.root().getLeft().getRight().getInfo(),portxy1);
        assertEquals(portTree.root().getLeft().getLeft().getInfo(),portxy2);
        List<Port> expectedResult = new ArrayList<>();
        expectedResult.add(portxy2);
        expectedResult.add(portx2);
        expectedResult.add(portxy1);
        expectedResult.add(port);
        expectedResult.add(porty2);
        expectedResult.add(portx1);
        expectedResult.add(porty1);



        assertEquals(portTree.inOrder(),expectedResult);




    }

    /***
     * Test if Ports were added to Database
     */
    @Test
    public void tesPortsDatabse(){

    }

    /***
     * Test if imports imports Ports
     */
    @Test
    public void ensureFullImport() throws FileNotFoundException {
        PortManager portManager = new PortManager();
        String fileresult = portManager.importPort("Data/data-ships&ports/sports.csv");
        assertEquals("Ports were successfully imported!",fileresult.toString());
        Port port = null;
        try {
            port = new Port("Europe","United Kingdom",29002,"Liverpool",53.46666667,-3.033333333);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(portManager.getPortTree().find(port));
        port.setCode(12345);
        assertFalse(portManager.getPortTree().find(port));

    }
}
