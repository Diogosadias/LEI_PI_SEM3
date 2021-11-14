package lapr.project.model;

import lapr.project.utils.PL.AVL;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;

public class ShipTree <E extends Comparable<E>> extends AVL<E> {
    public void ShipTree(){

    }

    public Ship getShip(Object code) {
        return null;
    }

    public boolean find(Object code) {
        return false;
    }

    public boolean isMMSI(Object code){
        if(isInt(code)) {
            Integer test = parseInt((String) code);
            if (test > 99999999 & test < 1000000000) return true;
        }
        return false;
    }

    private boolean isInt(Object code) {
        if (code == null) {
            return false;
        }
        try {
            Integer d = Integer.parseInt((String) code);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isISO(Object code) {
        String test = (String) code;
        if(test.substring(0,3)=="IMO") return true;
        return false;
    }

    public boolean isCS(Object code) {
        if(!isISO(code) & !isMMSI(code)) return true;
        return false;
    }






}
