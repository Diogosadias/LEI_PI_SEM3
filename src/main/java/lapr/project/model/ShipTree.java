package lapr.project.model;

import lapr.project.utils.PL.AVL;

import static java.lang.Integer.parseInt;

public class ShipTree <E extends Comparable<E>> extends AVL<E> {
    public  ShipTree(){
        //Only extends to other structures
    }



    public boolean isMMSI(Object code){
        if(isInt(code)) {
            Integer test = parseInt(code.toString());
            if (test > 99999999 && test < 1000000000) return true;
        }
        return false;
    }

    private boolean isInt(Object code) {
        if (code == null) {
            return false;
        }
        try {
            Integer d = Integer.parseInt(code.toString());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isISO(Object code) {
        String test = (String) code;
        if(test.substring(0,3).equals("IMO")) return true;
        String j=test.substring(0,3);
        return false;
    }

    public boolean isCS(Object code) {
        if(!isISO(code) & !isMMSI(code)) return true;
        return false;
    }






}
