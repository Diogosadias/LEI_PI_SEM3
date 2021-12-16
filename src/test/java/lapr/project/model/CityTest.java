package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;


class CityTest {

    @Test
    public void testAll(){
        City city = new City("Porto","Portugal");
        assertEquals(city.getCountry(),"Portugal");
        assertEquals(city.getName(),"Porto");
        city.setName("Londres");
        city.setCountry("UK");
        assertFalse(city.getCountry().equals("Portugal"));
        assertFalse(city.getName().equals("Porto"));
        assertEquals(city.getCountry(),"UK");
        assertEquals(city.getName(),"Londres");
        City t = new City("Viseu","Portugal",new Point2D.Double(0.2,0.5));

        assertEquals(t.getCountry(),"Portugal");
        assertEquals(t.getName(),"Viseu");
        assertEquals(t.getCoords().x,0.2);
        assertEquals(t.getCoords().y,0.5);
        t.setCoords(new Point2D.Double(0.5,0.2));

        assertEquals(t.getCoords().y,0.2);
        assertEquals(t.getCoords().x,0.5);


    }


}