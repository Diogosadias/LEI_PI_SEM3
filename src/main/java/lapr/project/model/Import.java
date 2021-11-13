package lapr.project.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
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
        in.nextLine();
        String keyMMSI = "";
        String keyIMO = "";
        String keyCallsign = "";
        Map<Integer, Ship> kMap = new HashMap<Integer, Ship>();
        int k = 0;

        while (in.hasNext()) {
            String line = in.nextLine();
            String[] iteams = line.split(",");

            if (!keyMMSI.equals(iteams[0])) {
                k++;
                kMap.put(k, new Ship());
                keyMMSI = iteams[0];
                MMSI.insert(kMap.get(k - 1));

            }
            if (!keyIMO.equals(iteams[8])) {
                k++;
                kMap.put(k, new Ship());
                keyIMO = iteams[8];
                IMO.insert(kMap.get(k - 1));

            }
            if (!keyCallsign.equals(iteams[9])) {
                k++;
                kMap.put(k, new Ship());
                keyCallsign = iteams[9];
                CallSign.insert(kMap.get(k - 1));

            }

        }

    }
}

//0 MMSI
//1 BaseDateTime
//2 LAT
//3 LON
//4 SOG
//5 COG
//6 Heading
//7 VesselName
//8 IMO
//9 CallSign
//10  VesselType
//11 Length
//12 Width
//13 Draft
//14 Cargo
//15 TranscieverClass


//        this.MMSI = MMSI;
//        this.VesselName = VesselName;
//        this.IMO = IMO;
//        this.CallSign = CallSign;
//        this.VesselType = VesselType;
//        this.Length = Length;
//        this.Width = Width;
//        this.Draft = Draft;
//        this.Cargo = Cargo;


