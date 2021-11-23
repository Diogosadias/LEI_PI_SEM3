package lapr.project.model;

import lapr.project.utils.PL.AVL;

import static java.lang.Integer.parseInt;

public class ShipTree <E extends Comparable<E>> extends AVL<E> {
    public  ShipTree(){
        //Only extends to other structures
    }



    public boolean isMMSI(Object code){
        if(isInt(code)) {
            if (parseInt(code.toString()) > 99999999 && parseInt(code.toString()) < 1000000000) return true;
        }
        return false;
    }

    private boolean isInt(Object code) {
        if (code == null) return false;
        try {
            Integer.parseInt(code.toString());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isISO(Object code) {
        String test = (String) code;
        if(test.startsWith("IMO")) return true;
        return false;
    }

    public boolean isCS(Object code) {
        if(!isISO(code) && !isMMSI(code)) return true;
        return false;
    }






}
