package lapr.project.data;

import lapr.project.model.PortTree;

import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
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

    public boolean getOccupancyRateTime(DatabaseConnection databaseConnection,String ship_id, String date, Double rate) {
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

    private Double executeORManifestTime(DatabaseConnection databaseConnection, String date, String ship_id) throws SQLException {
        Connection connection = databaseConnection.getConnection();


        CallableStatement cstmt = connection.prepareCall("{? = CALL checkOccupancyRateMoment(?,?)}");
        cstmt.registerOutParameter(1, Types.FLOAT);
        cstmt.setInt(2, Integer.parseInt(ship_id));

        try {
            cstmt.setDate(3, (Date) Date.from(getDate(date).atZone(ZoneId.systemDefault()).toInstant()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cstmt.executeUpdate();
        Double rate = cstmt.getDouble(1);
        return rate;
    }

    public Double getOCT(DatabaseConnection databaseConnection, String ship_id, String date) {
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
}
