package lapr.project.data;

import lapr.project.model.Container;
import lapr.project.model.PortTree;

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
}
