package lapr.project.controller;

import lapr.project.data.DatabaseConnection;
import lapr.project.model.Client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientController {
    private Client client = new Client();


    public ClientController(){
        //Creator Only
    }

    public File searchPosition(DatabaseConnection databaseConnection, String code) throws IOException {
        File myObj = new File("ContainerStatus - " + code + ".txt");
        FileWriter myWriter = new FileWriter("ContainerStatus - \" + code + \".txt");
        myWriter.write(client.search(databaseConnection, code));
        myWriter.close();


        return  myObj;
    }
}
