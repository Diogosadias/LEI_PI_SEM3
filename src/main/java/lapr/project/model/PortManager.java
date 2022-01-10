package lapr.project.model;

import lapr.project.data.DatabaseConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PortManager {

    private PortTree portTree = new PortTree();;
    private String username;
    private String password;


    public PortManager(){
        //Creates Class Only
    }

    public String importPort(Object o) throws FileNotFoundException {
        if(o == null) return "The Program has encountered a problem. Ports were not successfully imported.";

        List<Double> list = new ArrayList<>();
        List<Port> listPort = new ArrayList<>();

        Scanner in = new Scanner(new File((String) o));
        in.nextLine();
        if(in.hasNext()) {
            String line = in.nextLine();
            String[] properties = line.split(",");
            Port port = null;
            try {
                port = new Port(properties[0], properties[1], Integer.parseInt(properties[2]), properties[3], Double.parseDouble(properties[4]), Double.parseDouble(properties[5]));
            } catch (IOException e) {
               return "Failure!";
            }

            list.add(port.getCoords().x);
            listPort.add(port);
        }

        while (in.hasNext()) {
             String line = in.nextLine();
             String[]properties = line.split(",");
             Port port =null;
            try {
                port = new Port(properties[0],properties[1],Integer.parseInt(properties[2]),properties[3],Double.parseDouble(properties[4]),Double.parseDouble(properties[5]));
            } catch (IOException e) {
                return "Failure!";
            }

            list.add(port.getCoords().x);
            listPort.add(port);

        }

        List <Double> aux = list.stream().sorted().collect(Collectors.toList());
        Double median = null;
        if(aux.size()%2==0){
            median = aux.get(aux.size()/2-1);
        } else{
            median = aux.get(aux.size()/2);
        }
        Port port =listPort.get(list.indexOf(median));
        if(!portTree.find(port)){
            try {
                portTree.insert(port);
                listPort.remove(port);
            } catch (IOException e) {
                return "Failure!";
            }
        }

        for (Port portAux: listPort) {
            if (!portTree.find(portAux)) {
                try {
                    portTree.insert(portAux);
                } catch (IOException e) {
                    return "Failure!";
                }
            }
        }



        return "Ports were successfully imported!";

    }

    public PortTree<Port> getPortTree() {
        return portTree;
    }

    public String mapResources(DatabaseConnection databaseConnection) {

        Connection connection = databaseConnection.getConnection();
        Object object = null;
        try {
            connection.setAutoCommit(false);

            //Get From Database
            if (object==null) {
                throw databaseConnection.getLastError();
            }
            connection.commit();
            System.out.println("Container Found!");

        } catch (
                SQLException | NullPointerException ex) {
            Logger.getLogger(PortManager.class.getName())
                    .log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PortManager.class.getName())
                        .log(Level.SEVERE, null, ex1);
            }
        }
        return object.toString() ;
    }
}
