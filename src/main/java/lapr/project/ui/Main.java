package lapr.project.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Welcome to our Application!" +
                "\nPlease Select Your Role from the following:" +
                "\n1 - Traffic Manager" +
                "\n2 - Port Manager" +
                "\n3 - Ship Captain" +
                "\n4 - Client" +
                "\n5 - Other");

        String inputString = scanner.nextLine();
        switch (inputString) {
            case "1":
                TrafficManagerUI trafficManagerUI = new TrafficManagerUI();
                trafficManagerUI.run();
                break;
            case "2":
                PortManagerUI portManagerUI = new PortManagerUI();
                portManagerUI.runUI();
                break;
            case "3":
                ShipCaptainUI shipCaptainUI = new ShipCaptainUI();
                shipCaptainUI.runUI();
                break;
            case "4":
                ClientUI clientUI = new ClientUI();
                clientUI.runUI();
                break;
            case "5":
                break;
            default:
        }


        /*
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, String.valueOf(value));
        }

         */
    }
}

