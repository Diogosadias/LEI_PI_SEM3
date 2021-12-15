package lapr.project.model;

import org.junit.jupiter.api.Test;
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

    }


}