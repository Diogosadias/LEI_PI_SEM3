/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
("Ship Code was not according regulations!")
 */
package lapr.project.model;

import java.io.IOException;

import lapr.project.controller.MainController;

import org.junit.Test;

/**
 * @author diasd
 */
public class MainControllerTest {

    @Test
    public void ensureSearchDetailsisNotNull() throws IOException {

        MainController main = new MainController();
        //Act
        //Assert
        try {
            main.searchDetails(null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }

    @Test
     public void ensureSearchDateisNotNull() throws IOException {

        MainController main = new MainController();
        //Act
        //Assert
        try {
            main.searchDate(null, null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }

    @Test
    public void ensureSearchDate2isNotNull() throws IOException {

        MainController main = new MainController();
        //Act
        //Assert
        try {
            main.searchDate(null, null, null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }

    @Test
    public void ensureSummaryisNotNull() throws IOException {

        MainController main = new MainController();
        //Act
        //Assert
        try {
            main.summary(null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }

    @Test
    public void ensureGetTopNisNotNull() throws IOException {

        MainController main = new MainController();
        //Act
        //Assert
        try {
            main.getTopN(null, null, null);
        } catch (IOException ex) {
            System.out.println("Input is Invalid!");
        }
    }
}
