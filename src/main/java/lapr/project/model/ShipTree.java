package lapr.project.model;

import lapr.project.utils.PL.AVL;

import static java.lang.Integer.parseInt;

public class ShipTree <E extends Comparable<E>> extends AVL<E> {
    public  ShipTree(){
        //Only extends to other structures
    }



    public boolean isMMSI(Object code){
        if(code == null) return false;
        return (isInt(code) && (parseInt(code.toString()) > 99999999 && parseInt(code.toString()) < 1000000000));

    }

    public boolean isInt(Object code) {
        try{
            Number n = (Integer) code;
        }catch (ClassCastException ex){
            return false;
        }
        return true;
    }

    public boolean isISO(Object code) {
        return code.toString().startsWith("IMO");
    }

    public boolean isCS(Object code) {
        return (!isISO(code) && !isMMSI(code));
    }






}
