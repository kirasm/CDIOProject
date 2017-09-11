import LogicLayer.Sensor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Timothy on 09-06-2017.
 */

public class SensorTest {

    /**
     * Creating the Sensor Object & variables.
     */

    private Sensor newSensor;
    private int id, type;
    private double value;

    /**
     * Initialize variables & object.
     */

    @Before
    public void setUp() {

        id = 123;
        type = 1;
        value = 41.1231;

        newSensor = new Sensor(id, type, value);

    }

    /**
     * Testing getID method.
     * @throws Exception
     */

    @Test
    public void getID() throws Exception {

        assertEquals(id, newSensor.getID());

    }

    /**
     * Testing getValue method.
     * @throws Exception
     */

    @Test
    public void getValue() throws Exception {

        assertEquals(value, newSensor.getValue(), 0.1);

    }

    /**
     * Testing getType method.
     * @throws Exception
     */

    @Test
    public void getType() throws Exception {

        assertEquals(type, newSensor.getType());

    }

    @Test
    public void findSensor() throws Exception {



    }

}