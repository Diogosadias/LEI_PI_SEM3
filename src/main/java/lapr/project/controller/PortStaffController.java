package lapr.project.controller;

import lapr.project.data.ClientDatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PortStaffController {
    private ClientDatabase clientDatabase = new ClientDatabase();

    public PortStaffController(){
        //creation only
    }


    public File fillDina(String filename) throws IOException {
        File myObj = new File("FillDynamically.txt");
        FileWriter myWriter = new FileWriter("FillDynamically.txt");
        myWriter.write(clientDatabase.read(filename));
        myWriter.close();


        return  myObj;
    }
}
