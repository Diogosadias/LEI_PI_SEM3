package lapr.project.ui;

import java.io.IOException;

public class PortStaffUI {

    public native void runC();
    static {
        System.loadLibrary("main");
    }


    public PortStaffUI() throws IOException {
        //Creation Only
    }

    public void runUI() throws IOException {

        new PortStaffUI().runC();

    }
}
