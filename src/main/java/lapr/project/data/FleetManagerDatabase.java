package lapr.project.data;

import oracle.ucp.util.Pair;
import lapr.project.model.Client;
import lapr.project.model.Port;
import lapr.project.model.PortTree;
import lapr.project.model.Ship;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FleetManagerDatabase {
    public String daysIdle(String shipId, Integer year, DatabaseConnection databaseConnection) {
        Integer days = 0;
        if(shipId==null) return "Ship Id is not Valid!";

        days = getDaysIdle(shipId,year,databaseConnection,days);


        if(days==0) return "Ship doesn't have recorded Idle Days!";

        return  "The Ship has been Idle " + days + " days this year\n";
    }

    private Integer getDaysIdle(String shipId, Integer year, DatabaseConnection databaseConnection, Integer days) {
        try {
            getDays(shipId,year,databaseConnection,days);


        } catch (SQLException ex) {
            Logger.getLogger(FleetManagerDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }
        return days;
    }

    private void getDays(String shipId, Integer year, DatabaseConnection databaseConnection, Integer days) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        CallableStatement cstmt = connection.prepareCall("{? = call Function(?,?)}"); //redo
        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.setString(2, shipId);
        cstmt.setInt(3, year);
        cstmt.executeUpdate();
        Integer rs = cstmt.getInt(1);
        if(rs==null){
            days=0;
        }else{
            days=rs;
        }
        return;
    }

    public String ocupacionPeriod(String shipId, String initialdate, String finaldate, DatabaseConnection databaseConnection) {
        ArrayList<Pair<Integer,Double>> data = null;
        if(shipId==null) return "Ship Id is not Valid!";

        data = getOcupacionPeriod(shipId,initialdate,finaldate,databaseConnection,data);


        if(data==null) return "Ship doesn't have recorded Cargo Manifest for the Given Period!";
        String print="The Ship has the following average occupancy rate per manifest for the given period:\n";

        for(Pair a : data){
            print=print+a.get1st() + " - " + a.get2nd() +" %\n";
        }

        return  print;
    }

    private ArrayList<Pair<Integer, Double>> getOcupacionPeriod(String shipId, String initialdate, String finaldate, DatabaseConnection databaseConnection, ArrayList<Pair<Integer, Double>> data) {
        try {
            getORP(shipId,initialdate,finaldate,databaseConnection,data);


        } catch (SQLException ex) {
            Logger.getLogger(FleetManagerDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }
        return data;
    }

    private void getORP(String shipId, String initialdate, String finaldate, DatabaseConnection databaseConnection, ArrayList<Pair<Integer, Double>> data) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        CallableStatement cstmt = connection.prepareCall("{? = call Function(?,?,?)}"); //redo
        cstmt.registerOutParameter(1, Types.REF_CURSOR);
        cstmt.setString(2, shipId);
        cstmt.setString(3, initialdate);
        cstmt.setString(4, finaldate);
        cstmt.executeUpdate();
        ResultSet rs = cstmt.getResultSet();
        if(rs==null){
            data=null;
        }else{
            while (rs.next()){
                Integer cmcode = rs.getInt(1);
                Double or = rs.getDouble(2);
                Pair<Integer,Double> p =new Pair<>(cmcode,or);
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
        cstmt.registerOutParameter(1, Types.REF_CURSOR);
        cstmt.executeUpdate();
        ResultSet rs = cstmt.getResultSet();
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
