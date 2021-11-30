package lapr.project.controller;

import lapr.project.model.*;

import java.io.IOException;
import java.util.List;

import static lapr.project.model.TemporalMessages.getDate;

public class TrafficManagerController {

    public void setMmsiTree(MMSTree mmsiTree) {
        this.mmsiTree = mmsiTree;
    }



    public MMSTree mmsiTree = new MMSTree();
    public IMOTree imoTree = new IMOTree();
    public CallSignTree csTree = new CallSignTree();
    private String s = "Input is Invalid!";
    private String s2 ="-------------------------------------------------------------------------------------------------";
    public Search search = new Search();
    private PairsCalculator pc = new PairsCalculator(mmsiTree);
    private TopN topsum = new TopN(mmsiTree);


    /*
    Mais tarde criar classe Software/APP para armazenar tudo o que é importante
     */
    public TrafficManagerController() throws IOException {
        //Only Initiated
    }

    public void importFile(String filename) throws IOException {

        Import importer = new Import();
        List<ShipTree> map = importer.readLine(filename,mmsiTree,imoTree,csTree);
        MMSTree mmsiTree= (MMSTree) map.get(0);
        IMOTree imoTree= (IMOTree) map.get(1);
        CallSignTree csTree= (CallSignTree) map.get(2);

        this.mmsiTree=mmsiTree;
        this.imoTree=imoTree;
        this.csTree=csTree;

    }

    public void searchDetails(Object code) throws IOException {
        System.out.println(search.searchDetails(code,this));
    }

    public void searchDate(Object code, Object date) throws IOException {
       System.out.println(search.searchDate(code,date,this));
    }

    public void searchDate(Object code, Object date1, Object date2) throws IOException {
       System.out.println(search.searchDate(code,date1,date2,this));
    }

    public void summary(Object code) throws IOException {
       System.out.println(search.summary(code,this));
    }

    public void getTopN(Object n, String date1, String date2) throws IOException {
        System.out.println(topsum.getTopNString(n,date1,date2));

    }

    public void pairsofShips() {
        System.out.println(pc.pairs());
    }
}
