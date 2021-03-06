package lapr.project.data;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.ucp.util.Pair;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FleetManagerDatabase {
    public String daysIdle(DatabaseConnection databaseConnection) {
        ArrayList<Pair<String,Integer>> lista = new ArrayList<>();

        lista = getDaysIdle(databaseConnection,lista);



        if(lista.size()==0) return "There is no Information to Report!";

        String print = "The information about Ships and their Idle Days!\n";
        for(Pair a : lista){
            print = print + "Code - " + a.get1st() + " | Days - " + a.get2nd() +"\n";
        }

        return  print;
    }

    private ArrayList<Pair<String, Integer>> getDaysIdle(DatabaseConnection databaseConnection, ArrayList<Pair<String, Integer>> lista) {
        try {
            getDays(databaseConnection,lista);


        } catch (SQLException ex) {
            Logger.getLogger(FleetManagerDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }
        return lista;
    }

    private void getDays(DatabaseConnection databaseConnection, ArrayList<Pair<String, Integer>> lista) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        CallableStatement cstmt = connection.prepareCall("{? = call fnc_get_idle_ships()}");
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.executeUpdate();
        ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
        if(rs==null) return;
        while (rs.next()){
            String mmsi = rs.getString(1);
            Integer day =rs.getInt(2);
            lista.add(new Pair<>(mmsi,day));
        }
        return;
    }

    public String ocupacionPeriod(String shipId, String initialdate, String finaldate, DatabaseConnection databaseConnection) {
        ArrayList<Pair<String, String>> data = null;
        if(shipId==null) return "Ship Id is not Valid!";

        data = getOcupacionPeriod(shipId,initialdate,finaldate,databaseConnection,data);


        if(data==null) return "Ship doesn't have recorded Cargo Manifest for the Given Period!";
        String print="The Ship has the following average occupancy rate per manifest for the given period:\n";

        for(Pair a : data){
            print=print+a.get1st() + " - " + a.get2nd() +" %\n";
        }

        return  print;
    }

    private ArrayList<Pair<String, String>> getOcupacionPeriod(String shipId, String initialdate, String finaldate, DatabaseConnection databaseConnection, ArrayList<Pair<String, String>> data) {
        try {
            getORP(shipId,initialdate,finaldate,databaseConnection,data);


        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FleetManagerDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError((SQLException) ex);
        }
        return data;
    }

    private void getORP(String shipId, String initialdate, String finaldate, DatabaseConnection databaseConnection, ArrayList<Pair<String, String>> data) throws SQLException, ParseException {
        Connection connection = databaseConnection.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm");
        java.util.Date datei = sdf.parse(initialdate);
        java.util.Date datef = sdf.parse(finaldate);

        CallableStatement cstmt = connection.prepareCall("{? = call fncCheckAverageOccupancy (?,?,?)}");
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.setString(2, shipId);
        cstmt.setDate(3, new Date(datei.getTime()));
        cstmt.setDate(4, new Date(datef.getTime()));
        cstmt.executeUpdate();
        ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
        if(rs==null){
            data=null;
        }else{
            while (rs.next()) {
                String cmcode = rs.getString(1);
                String or = rs.getString(2);
                Pair<String,String> p =new Pair<>(cmcode,or);
                data.add(p);
            }

        }
        return;
    }

    public String aboveThreshold(DatabaseConnection databaseConnection) {
        ArrayList<ArrayList<String>> data = null;

        data = getAboveThreshold(databaseConnection,data);


        if(data==null) return "There are no Voyages where the Occupancy Rate was above Threshold!";
        String print="The Ship Voyages where the Occupancy Rate was above Threshold:\n";

        for(ArrayList a : data){
            print=print+"Voyage - From "+ a.get(0) +" at " + a.get(1)+ " To " + a.get(2) + "at " + a.get(3) +" => " + a.get(4) + "%";
        }

        return  print;
    }

    private ArrayList<ArrayList<String>> getAboveThreshold(DatabaseConnection databaseConnection, ArrayList<ArrayList<String>> data) {
        try {
            getThreshold(databaseConnection,data);


        } catch (SQLException ex) {
            Logger.getLogger(FleetManagerDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }
        return data;
    }

    private void getThreshold(DatabaseConnection databaseConnection, ArrayList<ArrayList<String>> data) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        CallableStatement cstmt = connection.prepareCall("{? = call Function()}"); //redo
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.executeUpdate();
        ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
        if(rs==null){
            data=null;
        }else{
            while (rs.next()){
                String from = rs.getString(1);
                String atfrom = rs.getString(2);
                String to = rs.getString(3);
                String atto = rs.getString(4);
                String threshold = rs.getString(5);
                ArrayList<String> list = new ArrayList<>();
                list.add(from);
                list.add(atfrom);
                list.add(to);
                list.add(atto);
                list.add(threshold);
                data.add(list);
            }

        }
        return;
    }
}
