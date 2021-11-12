package lapr.project.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import lapr.project.utils.PL.BST;

public class Import {

    //in.hasNextline();
    //String line = in.nexLine();    
    //String[] iteams = line.split(",");
    // ir ao main controleer fazer com que la leve o name file depois da ai posso dar hook up com as 3 trees e depois so tenho de fazer o codigo de import 
    private String SafeWord;

    public static void readLine(String FileName, MMSTree MMSI, IMOTree IMO, CallSignTree CallSign) throws FileNotFoundException {
        Scanner in = new Scanner(new File(FileName));

        while (in.hasNext()) {

        }

    }
}