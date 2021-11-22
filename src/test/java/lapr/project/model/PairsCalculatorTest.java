package lapr.project.model;



import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

class PairsCalculatorTest {
    ShipTree mmsiTree = new ShipTree();

    @Test
    public void testpairs() {
        PairsCalculator pair = new PairsCalculator(mmsiTree);
        assertEquals(pair.gettree(),mmsiTree);
    }

    /**
     * Test General
     */
    @Test
    public void testGeneral() throws IOException {
        ShipTree otherTree = new ShipTree();
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 17:20",49.97875,-66.97001,12.9,13.1,355, "NA","B");
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        Ship ship1 =new Ship("210952000","VARAMO","IMO9392044","C42Q2",70,166,25,9.5,"NA");
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 10:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 17:20",49.97875,-66.97001,12.9,13.1,355, "NA","B");
        ship.getMovements().insert(shipmov);
        ship.getMovements().insert(shipmov3);
        ship1.getMovements().insert(shipmov1);
        ship1.getMovements().insert(shipmov2);
        otherTree.insert(ship);
        otherTree.insert(ship1);
        PairsCalculator pair = new PairsCalculator(otherTree);

        String t = pair.pairs();
        assertEquals(t,pair.pairs());

    }
    /**
     * Test Proximity
     */
    @Test
    public void testProximity() throws IOException {
        ShipTree otherTree = new ShipTree();
        TemporalMessages shipmov = new TemporalMessages("31/12/2020 17:19",42.97875,-66.97001,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov3 = new TemporalMessages("31/12/2020 17:20",49.97875,-66.97001,12.9,13.1,355, "NA","B");
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2",70,166,25,9.5,"NA");
        Ship ship1 =new Ship("210952000","VARAMO","IMO9392044","C42Q2",70,166,25,9.5,"NA");
        TemporalMessages shipmov1 = new TemporalMessages("31/12/2020 10:19",0.97875,0.97001,12.9,13.1,355, "NA","B");
        TemporalMessages shipmov2 = new TemporalMessages("31/12/2020 17:20",49.97875,-66.97001,12.9,13.1,355, "NA","B");
        ship.getMovements().insert(shipmov);
        ship.getMovements().insert(shipmov3);
        ship1.getMovements().insert(shipmov1);
        ship1.getMovements().insert(shipmov2);
        otherTree.insert(ship);
        otherTree.insert(ship1);
        PairsCalculator pair = new PairsCalculator(otherTree);

        String t = pair.pairs();
        assertEquals(t,pair.pairs());

    }
}