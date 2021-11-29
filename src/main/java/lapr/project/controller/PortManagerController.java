package lapr.project.controller;

import lapr.project.model.PortManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
