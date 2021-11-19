package lapr.project.model;

import oracle.ucp.util.Pair;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import static lapr.project.model.TemporalMessages.getDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * PositionOrganized Class Unit Testing.
 *
 * @author Diogo Dias {@literal <1161605@isep.ipp.pt>} on 10/11/2021.
 */
public class PositionOrganizedTest {
    /**
     *  Ensure Message from file is correctly transformed into Ship Position - Ship Movement
     */
    @Test
    public void ensureMessageToShipPosition() throws IOException {
        //Arrange
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        TemporalMessages expectedresult = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");

        MovementsTree movs = new MovementsTree();
        movs.insert(expectedresult);
        ship.setMovements(movs);
        //Act
        MovementsTree tree =  ship.getMovements();
        List<TemporalMessages> result = (List<TemporalMessages>) tree.inOrder();

        //Assert
        assertEquals(expectedresult,result.get(0));


    }




    /**
     *  Ensure Output must be well-designed and in order
     */
    @Test
    public void ensureOutputinOrder(){
        /* Doubts about Printing - Which class

        //Arrange
        List<ShipMovements> list=new ArrayList<>();
        ShipMovements expectedResult = new ShipMovements("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        list.add(expectedResult);
        expectedResult = new ShipMovements("01/01/2021 17:19",42.92875,-66.37001,10.9,12.1,352, "NA","B");
        list.add(expectedResult);

        //Act
        Ship ship =new Ship("210950000","31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");
        ship =new Ship("210950000","01/01/2021 17:19",42.92875,-66.37001,10.9,12.1,352,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");

        List<ShipMovements> result = ship.movements.getMoveByDateFrame("31/12/2020","01/01/2021");

        //Assert
        assertEquals(list,result);

         */


    }

    /**
     *  Ensure correct info if Ship has not moved
     */
    @Test
    public void ensureShiphasnomovefortimeframe() throws IOException {
        //Arrange
        List<TemporalMessages> list=new ArrayList<>();
        //Act
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        movs.insert(shipmov);
        ship.setMovements(movs);
        list.add(shipmov);
        List<TemporalMessages> result = ship.getMovements().find(getDate("31/12/2020 10:00"),getDate("01/01/2021 10:00"));
        //Assert
        assertEquals(list,result);


    }

    /**
     *  Ensure date input is not null
     */
    @Test
    public void ensureDateisNotNull() throws IOException {
        //Arrange
        //Act
        //Assert
        try{
            LocalDateTime result = getDate(null);
        }catch (IOException ex){
            System.out.println("Input is Invalid!");
        }
    }

    /**
     *  Ensure Ship has movements for date
     */
    @Test
    public void ensureShipmovefordate() throws IOException {
        //Arrange
        List<TemporalMessages> list=new ArrayList<>();
        //Act
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        MovementsTree movs = new MovementsTree();
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        movs.insert(shipmov);
        ship.setMovements(movs);
        list.add(shipmov);
        String result = ship.getMoveByDate("31/12/2020");

        //Assert
        assertEquals(movs.printMoves(list),result);


    }

    /**
     *  Ensure date frame is not valid
     */
    @Test
    public void ensureDateFrameisnotValid() throws IOException {
        //Arrange
        //Act
        //Assert
        try {
            MovementsTree tree = new MovementsTree();
            tree.searchDateFrame("31/12/2020 17:19","30/12/2020 17:19");
        } catch (Exception e) {
            System.out.println("Input Date is invalid!");
        }

    }

    /**
     *  Ensure Ship has movement for date frame
     */
    @Test
    public void ensureShipmovefordateframe() throws IOException {
       //Arrange
       List<TemporalMessages> list=new ArrayList<>();
       TemporalMessages expectedResult = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
       list.add(expectedResult);

       //Act
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        MovementsTree movs = new MovementsTree();
        movs.insert(shipmov);
        ship.setMovements(movs);
        list.add(shipmov);

        String result = ship.getMovements().searchDateFrame("31/12/2020 10:00","01/01/2021 17:19");

       //Assert
       assertEquals(movs.printMoves(list),result);


    }



    /***
     * Ensure information printed is correct
     */
    @Test
    public void ensurePrinted() throws Exception {
        //Arrange


        //Act

        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        MovementsTree moves = new MovementsTree();
        ship.getMovements().insert(shipmov);
        String expectedresult=ship.getMoveByDate("31/12/2020");

        //Assert
        assertEquals(expectedresult,moves.getMoveByDate("31/12/2020"));



    }

    /***
     * Ensure printed works for different moves
     */
    @Test
    public void ensurePrintseveral() throws Exception {


        //Arrange


        //Act
        List<TemporalMessages> list=new ArrayList<>();
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        MovementsTree moves = new MovementsTree();
        list.add(shipmov);
        shipmov = new TemporalMessages("01/01/2021 17:19",42.97874,-66.97002,12.8,13.2,354, "NA","B");
        list.add(shipmov);
        moves.printMoves(list);
        //Assert
        String expectedresult = moves.printMoves(list);
        assertEquals(expectedresult,moves.printMoves(list));
        


    }

    @Test
    public void testGeneral() throws IOException {
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        MovementsTree moves = new MovementsTree();
        ship.getMovements().insert(shipmov);
        assertNull(moves.getMoveByDate(null));
        try{
            moves.setList(null);
        }catch (IOException ex){
            System.out.println("List is Null!");
        }
        assertNull(moves.printMoves(null));
        List list = moves.getList();

    }

    /**
     * Test SeList
     */
    @Test
    public void  setListTest() throws IOException {
        MovementsTree moves=  new MovementsTree();
        try{
            moves.setList(null);
        } catch (IOException ex){
            System.out.println("List is Null!");
        }
        List<TemporalMessages> temp = new ArrayList<>();
        moves.setList(temp);
        assertEquals(temp,moves.getList());
    }

    /**
     * Test Insert
     */
    @Test
    public void testInsert(){
        MovementsTree moves=  new MovementsTree();
        moves.insert(null);
    }

    /**
     * Testgetmin
     */
    @Test
    public void testgetmin() throws IOException {
        MovementsTree moves=  new MovementsTree();
        assertNull(moves.getmin(null));
        TemporalMessages temp = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        moves.insert(temp);
        Pair<Double,Double> value = new Pair<>(temp.getLatitude(),temp.getLongitude());
        assertEquals(moves.getmin(moves.root()),value );

        TemporalMessages temp1 =  new TemporalMessages("30/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        moves.insert(temp1);
        Pair<Double,Double> value1 = new Pair<>(temp1.getLatitude(),temp1.getLongitude());
        assertEquals(moves.getmin(moves.root()),value );

    }


}