package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
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
        FileWriter myWriter = new FileWriter("importResult.txt");
        myWriter.write(portManager.importPort(o));
        myWriter.close();
        

        return  myObj;
    }
    
    public PortManager getPortManager(){return portManager;}
<<<<<<< HEAD

    public void importToDatabase(DatabaseConnection databaseConnection,PortTree<Port> portTree) throws SQLException {
        List<Port> list= (List<Port>) portTree.inOrder();

        Connection connection = databaseConnection.getConnection();

        try {
            connection.setAutoCommit(false);
            for (Port port : list) {
                if (!portTree.save(databaseConnection, port)) {
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
=======
>>>>>>> 7e9f1c16a65296589627183eeceb842f3d09e5d6
}
