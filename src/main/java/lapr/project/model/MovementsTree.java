package lapr.project.model;

import lapr.project.utils.PL.AVL;

import java.io.IOException;
import java.util.List;

import static lapr.project.model.ShipMovements.*;

public class MovementsTree extends AVL {
    public static List<ShipMovements> getMoveByDate(String s) {

        return null;
    }

    public static void searchDateFrame(String s, String s1) throws IOException {
        if(getDate(s).isAfter(getDate(s1))) throw new IOException("Input Date is invalid!");
    }

    public List<ShipMovements> getMoveByDateFrame(String s, String s1) {
        return null;
    }
}
