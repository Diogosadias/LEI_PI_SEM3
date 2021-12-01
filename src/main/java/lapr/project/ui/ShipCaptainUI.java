package lapr.project.ui;

import lapr.project.controller.ShipCaptainController;
import lapr.project.model.CargoManifest;

public class ShipCaptainUI {
    ShipCaptainController shipCaptainController = new ShipCaptainController();
    private CargoManifest cargoManifest = new CargoManifest();
    private String date= "31/12/2020 00:01";


    public ShipCaptainUI(){
        //Creation Only
    }

    public void runUI() {
        shipCaptainController.offloaded();

        shipCaptainController.loaded();

        shipCaptainController.yearlymainfest();

        shipCaptainController.occupancyratemanifest(cargoManifest);

        shipCaptainController.occupancyrateTime(date);

    }
}
