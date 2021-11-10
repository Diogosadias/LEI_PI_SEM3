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


import static lapr.project.model.Import.*;
import static lapr.project.model.ShipMovements.*;
import static lapr.project.model.MovementsTree.*;
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
        //Arrange
        //If ship is already stored, only stores new position in ShipMovements, if not stores first movement.
        //Ship ship = new Ship();
        ShipMovements expectedresult = new ShipMovements("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        readLine("210950000,31/12/2020 17:19,42.97875,-66.97001,12.9,13.1,355,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B");
        //Act
        //ShipMovements result = getship("21095000").getMessages(0);
        //Assert
        //assertEquals(expectedresult,result);
    }




    /**
     *  Ensure Output Message is in order
     */
    @Test
    public void ensureOutputinOrder(){
        /*
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
        /*
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

        LocalDateTime result =getDate(null);
        //Assert
        assertNull(result);
    }

    /**
     *  Ensure Ship has movements for date
     */
    @Test
    public void ensureShipmovefordate(){
        /*
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
     *  Ensure date frame is valid
     */
    @Test
    public void ensureDateFrameisValid() throws IOException {
        //Arrange
        //Act
        //Assert
        assertThrows(IOException.class,()-> searchDateFrame("31/12/2020 17:19","30/12/2020 17:19"),"Input Date is invalid!");

    }

    /**
     *  Ensure Ship has movement for date frame
     */
    @Test
    public void ensureShipmovefordateframe(){
       /* List<ShipMovements> list=new ArrayList<>();
        ShipMovements expectedResult = new ShipMovements("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        list.add(expectedResult);
        //Act
        Ship ship =new Ship("210950000","31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355,"VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA","B");

        List<ShipMovements> result = ship.movements.getMoveByDateFrame("31/12/2020","01/01/2021");
        //Assert
        assertEquals(list,result);

        */
    }

    /**
     * Ensure Information Structure is well organized
     */
    @Test
    public void ensureShipmovementinfoisorganized(){
        /*
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

        List<ShipMovements> result = ship.movements.getMoveByDateFrame("31/12/2020","01/01/2021");
        //Assert
        assertEquals(result,list);
        */
    }
}