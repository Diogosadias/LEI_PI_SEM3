package lapr.project.data;

import oracle.ucp.util.Pair;
import lapr.project.model.Container;
import lapr.project.model.PortTree;
import lapr.project.model.Ship;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lapr.project.model.TemporalMessages.getDate;

public class ShipDatabase {
    public boolean getOccupancyRateManifest(DatabaseConnection databaseConnection, Integer cargoID, String ship_id, Double rate) {
        boolean returnValue = false;

        try {
            rate = executeORManifest(databaseConnection,cargoID,ship_id);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private Double executeORManifest(DatabaseConnection databaseConnection, Integer cargoID, String ship_id) throws SQLException {

        Connection connection = databaseConnection.getConnection();


        CallableStatement cstmt = connection.prepareCall("{ ? = call checkShipsOccupancyRate(?,?)}");
        cstmt.registerOutParameter(1, Types.FLOAT);
        cstmt.setInt(2, cargoID);
        cstmt.setInt(3, Integer.parseInt(ship_id));
        cstmt.executeUpdate();
        Double rate = cstmt.getDouble(1);
        return rate;
    }

    public boolean getOccupancyRateTime(DatabaseConnection databaseConnection, String ship_id, LocalDateTime date, Double rate) {
        boolean returnValue = false;

        try {
            rate = executeORManifestTime(databaseConnection,date,ship_id);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private Double executeORManifestTime(DatabaseConnection databaseConnection, LocalDateTime date, String ship_id) throws SQLException {
        Connection connection = databaseConnection.getConnection();


        CallableStatement cstmt = connection.prepareCall("{? = call checkOccupancyRateMoment(?,?)}");
        cstmt.registerOutParameter(1, Types.FLOAT);
        cstmt.setInt(2, Integer.parseInt(ship_id));
        cstmt.setDate(3, Date.valueOf(date.toLocalDate()));
        cstmt.executeUpdate();
        Double rate = cstmt.getDouble(1);
        return rate;
    }

    public Double getOCT(DatabaseConnection databaseConnection, String ship_id, LocalDateTime date) {
        Connection connection = databaseConnection.getConnection();
        Double rate=null;

        try {
            connection.setAutoCommit(false);

            if (!getOccupancyRateTime(databaseConnection, ship_id,date,rate)) {
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

    public Double getORM(DatabaseConnection databaseConnection, Integer cargoID, String ship_id) {

        Connection connection = databaseConnection.getConnection();

        Double rate = null;

        try {
            connection.setAutoCommit(false);

            if (!getOccupancyRateManifest(databaseConnection, cargoID,ship_id,rate)) {
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

            if (!executeOffLoad(databaseConnection, id,list)) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Offload Completed!");


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

    private boolean executeOffLoad(DatabaseConnection databaseConnection, Integer id, List<Container> list) {
        boolean returnValue = false;

        try {
            list = executeOff(databaseConnection,id);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private List<Container> executeOff(DatabaseConnection databaseConnection, Integer id) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        List<Container> list = new ArrayList<>();

        CallableStatement cstmt = connection.prepareCall("{? = call offload(?,?)}"); //Redo this call
        cstmt.setInt(2, id);
        ResultSet rs = cstmt.executeQuery();

        while (rs.next()){
            Integer container_id = rs.getInt(1);
            Double payload = rs.getDouble(2);
            Double tare = rs.getDouble(3);
            Double gross = rs.getDouble(4);
            Container c = new Container(container_id,payload,tare,gross);
            list.add(c);
        }
        return list;
    }

    public Ship getNextMonday(DatabaseConnection databaseConnection, LocalDateTime date1) {
        Ship ship = null;
        Connection connection = databaseConnection.getConnection();


        try {
            connection.setAutoCommit(false);

            if (!executeNextMonday(databaseConnection, date1,ship)) {
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

    private boolean executeNextMonday(DatabaseConnection databaseConnection, LocalDateTime date1, Ship ship) {
        boolean returnValue = false;

        try {
            ship = executeMonday(databaseConnection,date1);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private Ship executeMonday(DatabaseConnection databaseConnection, LocalDateTime date1) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        Ship ship = null;
        Integer mmsi;
        String vesselName;
        String callSign;
        int vesselType ;
        int length;
        int width;
        double draft;
        String cargo;
        String IMO;


        CallableStatement cstmt = connection.prepareCall("{? = call nextMonday(?)}"); //Redo this call
        cstmt.setDate(2,Date.valueOf(date1.toLocalDate()));
        ResultSet rs = cstmt.executeQuery();

        while (rs.next()){
            mmsi = rs.getInt(1);
            vesselName = rs.getString(2);
            callSign = rs.getString(6);
            vesselType = rs.getInt(10) ;
            length = rs.getInt(7);
            width = rs.getInt(8);
            draft = Double.parseDouble(null); //replace by access
            cargo = null; //replace by access
            IMO = rs.getString(3);
            ship = new Ship(mmsi.toString(),vesselName,IMO,callSign,vesselType,length,width,draft,cargo);
        }

        return ship;
    }

    public List<Container> getLoad(DatabaseConnection databaseConnection, Integer id) {
        List<Container> list = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();


        try {
            connection.setAutoCommit(false);

            if (!executeLoad(databaseConnection, id,list)) {
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

    private boolean executeLoad(DatabaseConnection databaseConnection, Integer id, List<Container> list) {
        boolean returnValue = false;

        try {
            list = executeLoad(databaseConnection,id);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private List<Container> executeLoad(DatabaseConnection databaseConnection, Integer id) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        List<Container> list = new ArrayList<>();

        CallableStatement cstmt = connection.prepareCall("{? = call load(?)}"); //Redo this call
        cstmt.setInt(2, id);
        ResultSet rs = cstmt.executeQuery();

        while (rs.next()){
            Integer container_id = rs.getInt(1);
            Double payload = rs.getDouble(2);
            Double tare = rs.getDouble(3);
            Double gross = rs.getDouble(4);
            Container c = new Container(container_id,payload,tare,gross);
            list.add(c);
        }
        return list;
    }

    public Pair<Integer, Double> year(DatabaseConnection databaseConnection, String year) {
        Pair<Integer, Double> c = new Pair<>(null,null);
        Connection connection = databaseConnection.getConnection();


        try {
            connection.setAutoCommit(false);

            if (!yearload(databaseConnection, year,c)) {
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

    private boolean yearload(DatabaseConnection databaseConnection, String year, Pair<Integer, Double> c) {
        boolean returnValue = false;

        try {
            c = yearexecute(databaseConnection,year);

            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private Pair<Integer, Double> yearexecute(DatabaseConnection databaseConnection, String year) throws SQLException {
        Connection connection = databaseConnection.getConnection();

        CallableStatement cstmt = connection.prepareCall("{? = call year(?)}"); //Redo this call
        cstmt.setInt(2, Integer.parseInt(year));
        cstmt.executeQuery();
        Integer first = cstmt.getInt(1);
        Double second = cstmt.getDouble(2);

        Pair<Integer, Double> c = new Pair<>(first,second);
        return c;
    }
}
