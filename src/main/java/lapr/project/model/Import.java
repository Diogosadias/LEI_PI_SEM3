package lapr.project.model;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static lapr.project.model.TemporalMessages.getDate;

public class Import {

    //in.hasNextline();
    //String line = in.nexLine();    
    //String[] iteams = line.split(",");
    // ir ao main controleer fazer com que la leve o name file depois da ai posso dar hook up com as 3 trees e depois so tenho de fazer o codigo de import 
    private String SafeWord;

    public static List<Map> readLine(String FileName, MMSTree MMSI, IMOTree IMO, CallSignTree CallSign) throws IOException {

        Map<Integer, Ship> shipMap = new HashMap<Integer, Ship>();
        Map<String, Ship> shipMap1 = new HashMap<String, Ship>();
        Map<String, Ship> shipMap2 = new HashMap<String, Ship>();
        Map<String, TemporalMessages> moveMap = new HashMap<String, TemporalMessages>();
        int i = 0;
        String keyMMSI = "";
        String keyIMO = "";
        String keyCallsign = "";

        Scanner in = new Scanner(new File(FileName));
        in.nextLine();

        while (in.hasNext()) {
            String line = in.nextLine();
            String[] iteams = line.split(",");

            if (!keyMMSI.equals(iteams[0])) {

                keyMMSI = iteams[0];
                keyIMO = iteams[8];
                keyCallsign = iteams[9];

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                shipMap.put(Integer.parseInt(keyMMSI), new Ship(iteams[0], iteams[7], iteams[8], iteams[9], Integer.parseInt(iteams[10]), Integer.parseInt(iteams[11]), Integer.parseInt(iteams[12]), Double.parseDouble(iteams[13]), iteams[14]));

                shipMap1.put(keyIMO, new Ship(iteams[0], iteams[7], iteams[8], iteams[9], Integer.parseInt(iteams[10]), Integer.parseInt(iteams[11]), Integer.parseInt(iteams[12]), Double.parseDouble(iteams[13]), iteams[14]));

                shipMap2.put(keyCallsign, new Ship(iteams[0], iteams[7], iteams[8], iteams[9], Integer.parseInt(iteams[10]), Integer.parseInt(iteams[11]), Integer.parseInt(iteams[12]), Double.parseDouble(iteams[13]), iteams[14]));

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }

            moveMap.put(iteams[1], new TemporalMessages(getDate(iteams[1]), Double.parseDouble(iteams[2]), Double.parseDouble(iteams[3]), Double.parseDouble(iteams[4]), Double.parseDouble(iteams[5]), Double.parseDouble(iteams[6]), iteams[15]));
            shipMap.get(Integer.parseInt(keyMMSI)).insertMovements(moveMap.get(iteams[1]));
            shipMap1.get(keyIMO).insertMovements(moveMap.get(iteams[1]));
            shipMap2.get(keyCallsign).insertMovements(moveMap.get(iteams[1]));
        }



        List<Map> map = new ArrayList();
        map.add(shipMap);
        map.add(shipMap2);
        map.add(shipMap2);

        return map;

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

