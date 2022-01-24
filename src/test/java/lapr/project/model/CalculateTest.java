package lapr.project.model;

import java.io.IOException;

import lapr.project.controller.ShipCaptainController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateTest {

    @Test
    void testCalculate() throws IOException {

        ShipCaptainController main = new ShipCaptainController();

        String expectedResult = "The total mass placed on the vessel with IMO IMO9103685 is: 25000.0 kg, the pressure exerted by that mass is: 30705.96827647339 P and the difference in height is: 19.62762699432529 m";
        String result = main.shipCaptain.calculate("IMO9103685");

        assertEquals(expectedResult, result);

        expectedResult = "The total mass placed on the vessel with IMO IMO9655169 is: 25000.0 kg, the pressure exerted by that mass is: 27388.52355965892 P and the difference in height is: 18.657196365622017 m";
        result = main.shipCaptain.calculate("IMO9655169");
        assertEquals(expectedResult, result);

        expectedResult = "The total mass placed on the vessel with IMO IMO9776171 is: 25000.0 kg, the pressure exerted by that mass is: 38979.88076879541 P and the difference in height is: 22.09511809762948 m";
        result = main.shipCaptain.calculate("IMO9776171");
        assertEquals(expectedResult, result);

        expectedResult = "The IMO was invalid!";
        result = main.shipCaptain.calculate("IMO9123456");
        assertEquals(expectedResult, result);

    }
}
