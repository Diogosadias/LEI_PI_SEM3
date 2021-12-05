package lapr.project.data;

import oracle.ucp.util.Pair;
import lapr.project.model.Container;
import lapr.project.model.PortTree;
import lapr.project.model.Ship;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipDatabase {
    public Integer getOccupancyRateManifest(DatabaseConnection databaseConnection, Integer cargoID, String ship_id, Integer rate) {


        try {
            rate = executeORManifest(databaseConnection,cargoID,ship_id,rate);

            //Save changes.

        } catch (SQLException ex) {
            Logger.getLogger(PortTree.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            rate = null;
        }


        return rate;
    }

    private Integer executeORManifest(DatabaseConnection databaseConnection, Integer cargoID, String ship_id, Integer rate) throws SQLException {

        Connection connection = databaseConnection.getConnection();


        CallableStatement cstmt = connection.prepareCall("{ ? = call checkShipsOccupancyRate(?,?)}");
        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.setInt(2, cargoID);
        cstmt.setInt(3, Integer.parseInt(ship_id));
        cstmt.executeUpdate();
        rate = cstmt.getInt(1);
        return rate;
    }

    public Integer getOccupancyRateTime(DatabaseConnection databaseConnection, String ship_id, LocalDateTime date, Integer rate) {

        try {
            rate = executeORManifestTime(databaseConnection,date,ship_id,rate);


        } catch (SQLException ex) {
            Logger.getLogger(ShipDatabase.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            rate = null;
        }
        return rate;
    }

    private Integer executeORManifestTime(DatabaseConnection databaseConnection, LocalDateTime date, String ship_id, Integer rate) throws SQLException {
        Connection connection = databaseConnection.getConnection();


        CallableStatement cstmt = connection.prepareCall("{? = call checkOccupancyRateMoment(?,?)}");
        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.setInt(2, Integer.parseInt(ship_id));
        cstmt.setString(3, date.toString()); //Erro
        cstmt.executeUpdate();
        rate = cstmt.getInt(1);
        return rate;
    }

    public Integer getOCT(DatabaseConnection databaseConnection, String ship_id, LocalDateTime date) {
        Connection connection = databaseConnection.getConnection();
        Integer rate=null;

        try {
            connection.setAutoCommit(false);
            rate = getOccupancyRateTime(databaseConnection, ship_id,date,rate);

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

    public Integer getORM(DatabaseConnection databaseConnection, Integer cargoID, String ship_id, Integer rate) {

        Connection connection = databaseConnection.getConnection();


        try {
            connection.setAutoCommit(false);

            rate = getOccupancyRateManifest(databaseConnection, cargoID,ship_id,rate);

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

        CallableStatement cstmt = connection.prepareCall("{? = call CHECKCONTAINERSTOBEOFFLOADED(?)}"); //Redo this call
        cstmt.registerOutParameter(1, Types.REF_CURSOR);
        cstmt.setInt(2, id);
        cstmt.executeUpdate();
        ResultSet rs = (ResultSet) cstmt.getRef(1);
        if(rs==null) return list;

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

        CallableStatement cstmt = connection.prepareCall("{? = call CHECKCONTAINERSTOBELOADED(?)}"); //Redo this call
        cstmt.registerOutParameter(1, Types.REF_CURSOR);
        cstmt.setInt(2, id);
        cstmt.executeUpdate();
        ResultSet rs = (ResultSet) cstmt.getRef(1);
        if(rs==null) return list;

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
