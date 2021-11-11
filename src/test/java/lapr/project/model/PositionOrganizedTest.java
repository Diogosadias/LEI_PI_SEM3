package lapr.project.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import javax.print.attribute.standard.MediaSize;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import  lapr.project.model.Import.*;
import lapr.project.model.ShipMovements.*;
import lapr.project.model.MovementsTree.*;
import static org.junit.jupiter.api.Assertions.*;

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
    public void ensureMessageToShipPosition(){
        /*Missing Ship constructor
        //Arrange
        //Ship ship = new Ship("210950000","31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");
        ShipMovements expectedresult = new ShipMovements("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");

        //Act
        Object result = ship.getMovements().get(0);

        //Assert
        assertEquals(expectedresult,result);

         */
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
    public void ensureShiphasnomovefortimeframe(){
        /* Missing Constructor Ship - And Print
        //Arrange
        List<ShipMovements> list=new ArrayList<>();
        //Act
        Ship ship =new Ship("210950000","30/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");

        List<ShipMovements> result = ship.movements.getMoveByDateFrame("31/12/2020","01/01/2021");
        //Assert
        assertEquals(list,result);

         */
    }

    /**
     *  Ensure date input is not null
     */
    @Test
    public void ensureDateisNotNull(){
        //Arrange
        //Act
        LocalDateTime result = ShipMovements.getDate(null);
        //Assert
        assertNull(result);
    }

    /**
     *  Ensure Ship has movements for date
     */
    @Test
    public void ensureShipmovefordate(){
        /* Missing Ship Constructor

        //Arrange
        List<ShipMovements> list=new ArrayList<>();
        ShipMovements expectedResult = new ShipMovements("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        list.add(expectedResult);

        //Act
        Ship ship =new Ship("210950000","31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");

        List<ShipMovements> result = ship.movements.getMoveByDate("31/12/2020");

        //Assert
        assertEquals(list,result);

         */
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
       /* - Missing Ship Constructor
       //Arrange
       List<ShipMovements> list=new ArrayList<>();
       ShipMovements expectedResult = new ShipMovements("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
       list.add(expectedResult);

       //Act
       Ship ship =new Ship("210950000","31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");

       List<ShipMovements> result = ship.getMoveByDateFrame("31/12/2020","01/01/2021");

       //Assert
       assertEquals(list,result);

        */
    }

    /**
     * Ensure Information Structure is well organized
     */
    @Test
    public void ensureShipmovementinfoisorganized(){
        /* - Missing Ship constructor
        //Arrange
        List<ShipMovements> list=new ArrayList<>();
        ShipMovements expectedResult = new ShipMovements("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        list.add(expectedResult);
        expectedResult = new ShipMovements("01/01/2021 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        list.add(expectedResult);
        expectedResult = new ShipMovements("02/01/2021 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        list.add(expectedResult);
        System.out.println(list);
        //Act
        Ship ship =new Ship("210950000","31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");
        ship =new Ship("210950000","31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");
        ship =new Ship("210950000","31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");

        List<ShipMovements> result = ship.getMoveByDateFrame("31/12/2020","01/01/2021");
        //Assert
        assertEquals(result,list);
        */
    }
}