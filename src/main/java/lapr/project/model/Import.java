package lapr.project.model;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static lapr.project.model.TemporalMessages.getDate;

public class Import {

    ;

    public static List<ShipTree> readLine(String FileName, MMSTree MMSI, IMOTree IMO, CallSignTree CallSign) throws IOException {
        if (MMSI == null || CallSign == null || IMO == null) {
            throw new IOException("Input is Invalid!");
        }

        String keyMMSI = "";
        String keyIMO = "";
        String keyCallsign = "";

        Scanner in = new Scanner(new File(FileName));
        in.nextLine();

        while (in.hasNext()) {
            String line = in.nextLine();
            String[] iteams = line.split(",");

            if (keyMMSI.compareTo(iteams[0]) != 0) {

                keyMMSI = iteams[0];
                keyIMO = iteams[8];
                keyCallsign = iteams[9];

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                Ship ship = new Ship(iteams[0], iteams[7], iteams[8], iteams[9], Integer.parseInt(iteams[10]), Integer.parseInt(iteams[11]), Integer.parseInt(iteams[12]), Double.parseDouble(iteams[13]), iteams[14]);

                MMSI.insert(ship);

                IMO.insert(ship);

                CallSign.insert(ship);
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }

            MMSI.getShip(keyMMSI).getMovements().insert(new TemporalMessages(getDate(iteams[1]), Double.parseDouble(iteams[2]), Double.parseDouble(iteams[3]), Double.parseDouble(iteams[4]), Double.parseDouble(iteams[5]), Double.parseDouble(iteams[6]), iteams[14], iteams[15]));
            IMO.getShip(keyIMO).getMovements().insert(new TemporalMessages(getDate(iteams[1]), Double.parseDouble(iteams[2]), Double.parseDouble(iteams[3]), Double.parseDouble(iteams[4]), Double.parseDouble(iteams[5]), Double.parseDouble(iteams[6]), iteams[14], iteams[15]));
            CallSign.getShip(keyCallsign).getMovements().insert(new TemporalMessages(getDate(iteams[1]), Double.parseDouble(iteams[2]), Double.parseDouble(iteams[3]), Double.parseDouble(iteams[4]), Double.parseDouble(iteams[5]), Double.parseDouble(iteams[6]), iteams[14], iteams[15]));

        }

        List<ShipTree> map = new ArrayList();
        map.add(MMSI);
        map.add(IMO);
        map.add(CallSign);
        in.close();
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

