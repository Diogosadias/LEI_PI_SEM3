package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.data.ImportPortDatabase;
import lapr.project.model.Port;
import lapr.project.model.PortManager;
import lapr.project.model.PortTree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortManagerController {
    private PortManager portManager = new PortManager();
    
    public PortManagerController(){
        //Constructor
    }

    public File importPort(Object o) throws IOException {
        File myObj = new File("importResult.txt");
        try (FileWriter myWriter = new FileWriter("importResult.txt")) {
            myWriter.write(portManager.importPort(o));
        }
        

        return  myObj;
    }
    
    public PortManager getPortManager(){return portManager;}

    public void importToDatabase(DatabaseConnection databaseConnection,PortTree<Port> portTree) throws SQLException {


        Connection connection = databaseConnection.getConnection();
        ImportPortDatabase importPortDatabase = new ImportPortDatabase();


        try {
            connection.setAutoCommit(false);
            List<Port> list= (List<Port>) portTree.inOrder();



            for (Port port : list) {
                if (!importPortDatabase.save(databaseConnection, port)) {
                    throw databaseConnection.getLastError();
                }
                connection.commit();
                System.out.println("Port Added!");

            }
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

    }

    public File mapResources(DatabaseConnection databaseConnection) throws IOException {
        File myObj = new File("MapResources.txt");
        try (FileWriter myWriter = new FileWriter("MapResources.txt")) {
            myWriter.write(portManager.mapResources(databaseConnection));
        }


        return  myObj;
    }

    public File generateReport(String portID, DatabaseConnection databaseConnection) throws IOException {
        File myObj = new File("Report.txt");
        FileWriter myWriter = new FileWriter("Report.txt");
        myWriter.write(portManager.generateReport(portID,databaseConnection));
        myWriter.close();


        return  myObj;
    }
}
