package lapr.project.data;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.ucp.util.Pair;
import lapr.project.model.Container;
import lapr.project.model.PortTree;
import lapr.project.model.Ship;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipDatabase {
    public Double getOccupancyRateManifest(DatabaseConnection databaseConnection, Integer cargoID, String shipId, Double rate) {


        try {
            rate = executeORManifest(databaseConnection,cargoID,shipId,rate);

            //Save changes.

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            rate = null;
        }


        return rate;
    }

    private Double executeORManifest(DatabaseConnection databaseConnection, Integer cargoID, String shipId, Double rate) throws SQLException {

        Connection connection = databaseConnection.getConnection();


        CallableStatement cstmt = connection.prepareCall("{ ? = call checkShipsOccupancyRate(?,?)}");
        cstmt.registerOutParameter(1, Types.DOUBLE);
        cstmt.setInt(3, cargoID);
        cstmt.setInt(2, Integer.parseInt(shipId));
        cstmt.executeUpdate();
        rate = cstmt.getDouble(1);
        return rate;
    }

    public Double getOccupancyRateTime(DatabaseConnection databaseConnection, String shipId, String date, Double rate) {

        try {
            rate = executeORManifestTime(databaseConnection,date,shipId,rate);


        } catch (SQLException ex) {
            Logger.getLogger(ShipDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            rate = null;
        }
        return rate;
    }

    private Double executeORManifestTime(DatabaseConnection databaseConnection, String date, String shipId, Double rate) throws SQLException {
        Connection connection = databaseConnection.getConnection();


        CallableStatement cstmt = connection.prepareCall("{? = call checkOccupancyRateMoment(?,?)}");
        cstmt.registerOutParameter(1, Types.DOUBLE);
        cstmt.setInt(2, Integer.parseInt(shipId));
        cstmt.setString(3, date);
        cstmt.executeUpdate();
        rate = cstmt.getDouble(1);
        return rate;
    }

    public Double getOCT(DatabaseConnection databaseConnection, String shipId, String date) {
        Connection connection = databaseConnection.getConnection();
        Double rate=null;

        try {
            connection.setAutoCommit(false);
            rate = getOccupancyRateTime(databaseConnection, shipId,date,rate);

            if (rate==null) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Occupancy Rate Retrieved!");


        }catch(SQLException ex){
            Logger.getLogger(ShipDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ShipDatabase.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }

        return rate;
    }

    public Double getORM(DatabaseConnection databaseConnection, Integer cargoID, String shipId, Double rate) {

        Connection connection = databaseConnection.getConnection();


        try {
            connection.setAutoCommit(false);

            rate = getOccupancyRateManifest(databaseConnection, cargoID,shipId,rate);

            if (rate==null) {
                throw databaseConnection.getLastError();
            }

            connection.commit();
            System.out.println("Occupancy Rate Retrieved!");


        }catch(SQLException ex){
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PortTree.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        return rate;
    }

    public List<Container> getOffload(DatabaseConnection databaseConnection, Integer id) {
        List<Container> list = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();


        try {
            connection.setAutoCommit(false);
            list =executeOffLoad(databaseConnection, id,list);

            if (list==null) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Offload Completed!");


        }catch(SQLException ex ){
            Logger.getLogger(ShipDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ShipDatabase.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        return list;
    }

    private List<Container> executeOffLoad(DatabaseConnection databaseConnection, Integer id, List<Container> list) {

        try {
            list = executeOff(databaseConnection,id);


        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            list = null;
        }
        return list;
    }

    private List<Container> executeOff(DatabaseConnection databaseConnection, Integer id) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        List<Container> list = new ArrayList<>();

        CallableStatement cstmt = connection.prepareCall("{? = call CHECKCONTAINERSTOBEOFFLOADED(?)}");
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.setInt(2, id);
        cstmt.executeUpdate();
        ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
        if(rs==null) return list;

        while (rs.next()){
            Integer containerId = rs.getInt(1);
            Double payload = rs.getDouble(2);
            Integer x = rs.getInt(3);
            Integer y = rs.getInt(4);
            Integer z = rs.getInt(5);
            Double temp = rs.getDouble(6);
            Container c = new Container(containerId,payload,x,y,z,temp);
            list.add(c);
        }
        return list;
    }

    public List<Ship> getNextMonday(DatabaseConnection databaseConnection, String date1) {
        List<Ship> ship = null;
        Connection connection = databaseConnection.getConnection();


        try {
            connection.setAutoCommit(false);

            ship =executeNextMonday(databaseConnection, date1,ship);

            if (ship==null) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Ship Found!");


        }catch(SQLException ex){
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PortTree.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        return ship;
    }

    private List<Ship> executeNextMonday(DatabaseConnection databaseConnection, String date1, List<Ship> ship) {


        try {
            ship = executeMonday(databaseConnection,date1);


        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            ship = null;
        }
        return ship;
    }

    private List<Ship> executeMonday(DatabaseConnection databaseConnection, String date1) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        List<Ship> list = null;
        Integer mmsi;
        Double lat;
        Double lon;


        CallableStatement cstmt = connection.prepareCall("{? = call nextMonday(?)}"); //Redo this call
        cstmt.setString(2,date1);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.executeUpdate();
        ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);

        while (rs.next()){
            mmsi = rs.getInt(1);
            lat = rs.getDouble(2);
            lon = rs.getDouble(3);
            Ship ship = new Ship(mmsi.toString(),lat,lon);
            list.add(ship);
        }

        return list;
    }

    public List<Container> getLoad(DatabaseConnection databaseConnection, Integer id) {
        List<Container> list = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();


        try {
            connection.setAutoCommit(false);

            list =executeLoad(databaseConnection, id,list);

            if (list==null) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Load Completed!");


        }catch(SQLException ex){
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PortTree.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        return list;
    }

    private List<Container> executeLoad(DatabaseConnection databaseConnection, Integer id, List<Container> list) {

        try {
            list = executeLoad(databaseConnection,id);

            //Save changes.


        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            list = null;
        }
        return list;
    }

    private List<Container> executeLoad(DatabaseConnection databaseConnection, Integer id) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        List<Container> list = new ArrayList<>();

        CallableStatement cstmt = connection.prepareCall("{? = call CHECKCONTAINERSTOBELOADED(?)}");
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.setInt(2, id);
        cstmt.executeUpdate();
        ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
        if(rs==null) return list;

        while (rs.next()){
            Integer containerId = rs.getInt(1);
            Double payload = rs.getDouble(2);
            Double temp = rs.getDouble(3);
            Container c = new Container(containerId,payload,temp);
            list.add(c);
        }
        return list;
    }

    public Pair<Integer, Double> year(DatabaseConnection databaseConnection, String year,String shipId) {
        Pair<Integer, Double> c = new Pair<>(null,null);
        Connection connection = databaseConnection.getConnection();


        try {
            connection.setAutoCommit(false);

            c=yearload(databaseConnection, year,c,shipId);

            if (c==null) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Information Found!");


        }catch(SQLException ex){
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PortTree.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        return c;
    }

    private Pair<Integer, Double> yearload(DatabaseConnection databaseConnection, String year, Pair<Integer, Double> c,String shipId) {

        try {
            c = yearexecute(databaseConnection,year,shipId);

            //Save changes.

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            c = null;
        }
        return c;
    }

    private Pair<Integer, Double> yearexecute(DatabaseConnection databaseConnection, String year,String shipId) throws SQLException {
        Connection connection = databaseConnection.getConnection();


        CallableStatement cstmt = connection.prepareCall("{? = call US_207(?)}");
        cstmt.setInt(2, Integer.parseInt(shipId));
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.executeUpdate();
        String t = cstmt.getString(1);
        String[] arrOfStr = t.split(",");
        Integer first = Integer.parseInt(arrOfStr[0]);
        Double second = Double.parseDouble(arrOfStr[1]);
        
        return  new Pair<>(first,second);
    }
}
