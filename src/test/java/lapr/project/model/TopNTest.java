package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static lapr.project.model.TemporalMessages.getDate;
import static org.junit.jupiter.api.Assertions.*;

class TopNTest {

    /***
     * Ensure Constructor can't be null
     */
    @Test
   public void testgetTop() throws IOException {
        try{
            TopN top = new TopN(null);
        }catch (IOException ex){
            System.out.println("Tree is not Valid!");
        }
        ShipTree mmsiTree =new ShipTree();
        TopN top = new TopN(mmsiTree);
        assertEquals(mmsiTree,top.getTree());
    }

    /**
     * Get Ships from specific date Range Moves
     */
    @Test
   public void ensuredaterangeworks() throws IOException {

        ShipTree mmsiTree =new ShipTree();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov1 = new TemporalMessages("01/01/2021 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        MovementsTree movs = new MovementsTree();
        movs.insert(shipmov);
        movs.insert(shipmov1);
        ship.setMovements(movs);
        mmsiTree.insert(ship);

        Ship ship1 = new Ship("210950020","VARAMI","IMO9395042","C4SQ1",70,166,25,9.5,"NA");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 17:10",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov3 = new TemporalMessages("01/01/2021 17:00",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        MovementsTree movs1 = new MovementsTree();
        movs1.insert(shipmov2);
        movs1.insert(shipmov3);
        ship1.setMovements(movs1);
        mmsiTree.insert(ship1);
        TopN top = new TopN(mmsiTree);
        String t = top.getTop(2,getDate("31/12/2020 10:00"),getDate("02/01/2021 21:00"));
        assertEquals(t,top.getTop(2,getDate("31/12/2020 10:00"),getDate("02/01/2021 21:00")));

    }
}