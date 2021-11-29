package lapr.project.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PortManager {

    private PortTree portTree = new PortTree();;
    private String username;
    private String password;


    public PortManager(){
        //Creates Class Only
    }

    public String importPort(Object o) throws FileNotFoundException {
        if(o == null) return "The Program has encountered a problem. Ports were not successfully imported.";


        Scanner in = new Scanner(new File((String) o));
        in.nextLine();

        while (in.hasNext()) {
            String line = in.nextLine();
            String[] properties = line.split(",");
            Port port =null;
            try {
                port = new Port(properties[0],properties[1],Integer.parseInt(properties[2]),properties[3],Double.parseDouble(properties[4]),Double.parseDouble(properties[5]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(!portTree.find(port)){
                try {
                    portTree.insert(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Add to Database
            }
        }

        return "Ports were successfully imported!";

    }

    public PortTree<Port> getPortTree() {
        return portTree;
    }
}
