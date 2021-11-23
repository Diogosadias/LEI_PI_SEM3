package lapr.project.model;

import lapr.project.utils.PL.AVL;

import static java.lang.Integer.parseInt;

public class ShipTree <E extends Comparable<E>> extends AVL<E> {
    public  ShipTree(){
        //Only extends to other structures
    }



    public boolean isMMSI(Object code){
        return (isInt(code) && (parseInt(code.toString()) > 99999999 && parseInt(code.toString()) < 1000000000));

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
        String test = code.toString();
        return test.startsWith("IMO");
    }

    public boolean isCS(Object code) {
        return (!isISO(code) && !isMMSI(code));
    }






}
