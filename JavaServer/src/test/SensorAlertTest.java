import LogicLayer.SensorAlert;
import LogicLayer.Sensor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

@SuppressWarnings("Duplicates")

/**
 * Created by Matt_Lab on 08/06/2017.
 */
public class SensorAlertTest {

    private ArrayList<SensorAlert> freezer_Fail_Hot = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> freezer_Pass = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> fridge_Fail_Hot = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> fridge_Fail_Cold = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> fridge_Pass = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> keep_Hot_Fail_Cold = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> keep_Hot_Fail_Time = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> keep_Hot_Pass = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> heat_Up_Fail_Time = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> heat_Up_Pass = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> cool_Down_Fail_Time = new ArrayList<SensorAlert>();
    private ArrayList<SensorAlert> cool_Down_Pass = new ArrayList<SensorAlert>();
    int[] time_Fail = {45, 90, 135, 180, 275};
    int[] time_Pass = {30, 60, 90, 120, 150};


    @Before
    public void setUp() throws Exception {

        /**
         * freezer objects that fail too hot
         */
        freezer_Fail_Hot.add(new SensorAlert(new Sensor(500, 5, -11.3)));
        freezer_Fail_Hot.add(new SensorAlert(new Sensor(500, 5, -10.3)));
        freezer_Fail_Hot.add(new SensorAlert(new Sensor(500, 5, -13.3)));
        freezer_Fail_Hot.add(new SensorAlert(new Sensor(500, 5, -11.3)));
        freezer_Fail_Hot.add(new SensorAlert(new Sensor(500, 5, -12.3)));


        /**
         * freezer objects that pass
         */
        freezer_Pass.add(new SensorAlert(new Sensor(501, 5, -19.3)));
        freezer_Pass.add(new SensorAlert(new Sensor(501, 5, -20.3)));
        freezer_Pass.add(new SensorAlert(new Sensor(501, 5, -18.3)));
        freezer_Pass.add(new SensorAlert(new Sensor(501, 5, -21.3)));
        freezer_Pass.add(new SensorAlert(new Sensor(501, 5, -25.3)));


        /**
         * Fridge objects that fail too hot
         */
        fridge_Fail_Hot.add(new SensorAlert(new Sensor(400, 4, 6.5)));
        fridge_Fail_Hot.add(new SensorAlert(new Sensor(400, 4, 7.6)));
        fridge_Fail_Hot.add(new SensorAlert(new Sensor(400, 4, 10.3)));
        fridge_Fail_Hot.add(new SensorAlert(new Sensor(400, 4, 8.8)));
        fridge_Fail_Hot.add(new SensorAlert(new Sensor(400, 4, 6.2)));

        /**
         * Fridge objects that fail too cold
         */
        fridge_Fail_Cold.add(new SensorAlert(new Sensor(401, 4, -2.3)));
        fridge_Fail_Cold.add(new SensorAlert(new Sensor(401, 4, -5.3)));
        fridge_Fail_Cold.add(new SensorAlert(new Sensor(401, 4, -1.2)));
        fridge_Fail_Cold.add(new SensorAlert(new Sensor(401, 4, 0.0)));
        fridge_Fail_Cold.add(new SensorAlert(new Sensor(401, 4, -3.8)));

        /**
         * Fridge objects that pass
         */
        fridge_Pass.add(new SensorAlert(new Sensor(402, 4, 3.2)));
        fridge_Pass.add(new SensorAlert(new Sensor(402, 4, 1.1)));
        fridge_Pass.add(new SensorAlert(new Sensor(402, 4, 1.9)));
        fridge_Pass.add(new SensorAlert(new Sensor(402, 4, 2.5)));
        fridge_Pass.add(new SensorAlert(new Sensor(402, 4, 4.9)));

        /**
         * Keep_Hot objects that fail too cold
         */
        keep_Hot_Fail_Cold.add(new SensorAlert(new Sensor(300, 3, 60)));
        keep_Hot_Fail_Cold.add(new SensorAlert(new Sensor(300, 3, 57)));
        keep_Hot_Fail_Cold.add(new SensorAlert(new Sensor(300, 3, 61)));
        keep_Hot_Fail_Cold.add(new SensorAlert(new Sensor(300, 3, 64.9)));
        keep_Hot_Fail_Cold.add(new SensorAlert(new Sensor(300, 3, 64.5)));

        /**
         * Keep_Hot objects that fail time
         */
        keep_Hot_Fail_Time.add(new SensorAlert(new Sensor(301, 3, 65.5)));
        keep_Hot_Fail_Time.add(new SensorAlert(new Sensor(301, 3, 67.1)));
        keep_Hot_Fail_Time.add(new SensorAlert(new Sensor(301, 3, 70.7)));
        keep_Hot_Fail_Time.add(new SensorAlert(new Sensor(301, 3, 69.9)));
        keep_Hot_Fail_Time.add(new SensorAlert(new Sensor(301, 3, 67.6)));

        /**
         * Keep_Hot objects that pass time
         */
        keep_Hot_Pass.add(new SensorAlert(new Sensor(302, 3, 65.0)));
        keep_Hot_Pass.add(new SensorAlert(new Sensor(302, 3, 67.1)));
        keep_Hot_Pass.add(new SensorAlert(new Sensor(302, 3, 70.7)));
        keep_Hot_Pass.add(new SensorAlert(new Sensor(302, 3, 69.9)));
        keep_Hot_Pass.add(new SensorAlert(new Sensor(302, 3, 67.6)));

        /**
         * heat_Up objects that fail time
         */
        heat_Up_Fail_Time.add(new SensorAlert(new Sensor(200, 2, 4.0)));
        heat_Up_Fail_Time.add(new SensorAlert(new Sensor(200, 2, 25.3)));
        heat_Up_Fail_Time.add(new SensorAlert(new Sensor(200, 2, 41.9)));
        heat_Up_Fail_Time.add(new SensorAlert(new Sensor(200, 2, 55.1)));
        heat_Up_Fail_Time.add(new SensorAlert(new Sensor(200, 2, 74.2)));

        /**
         * heat_Up objects that pass time
         */
        heat_Up_Pass.add(new SensorAlert(new Sensor(201, 2, 5.0)));
        heat_Up_Pass.add(new SensorAlert(new Sensor(201, 2, 20.5)));
        heat_Up_Pass.add(new SensorAlert(new Sensor(201, 2, 42.9)));
        heat_Up_Pass.add(new SensorAlert(new Sensor(201, 2, 59.8)));
        heat_Up_Pass.add(new SensorAlert(new Sensor(201, 2, 75.6)));

        /**
         * cool_Down objects that fail time
         */
        cool_Down_Fail_Time.add(new SensorAlert(new Sensor(100, 1, 65.9)));
        cool_Down_Fail_Time.add(new SensorAlert(new Sensor(100, 1, 52.1)));
        cool_Down_Fail_Time.add(new SensorAlert(new Sensor(100, 1, 35.4)));
        cool_Down_Fail_Time.add(new SensorAlert(new Sensor(100, 1, 18.3)));
        cool_Down_Fail_Time.add(new SensorAlert(new Sensor(100, 1, 11.0)));

        /**
         * cool_Down objects that pass time
         */
        cool_Down_Pass.add(new SensorAlert(new Sensor(101, 1, 65.0)));
        cool_Down_Pass.add(new SensorAlert(new Sensor(101, 1, 48.3)));
        cool_Down_Pass.add(new SensorAlert(new Sensor(101, 1, 32.9)));
        cool_Down_Pass.add(new SensorAlert(new Sensor(101, 1, 15.2)));
        cool_Down_Pass.add(new SensorAlert(new Sensor(101, 1, 3.9)));
    }

    @After
    public void tearDown() throws Exception {

        SensorAlert.removeAllFromArray();

    }

    @Test
    public void addToArray() throws Exception {


        int expected = 0;
        int actual;

        for (int i = 0; i < freezer_Fail_Hot.size(); i++) {
            freezer_Fail_Hot.get(i).addToArray(freezer_Fail_Hot.get(i));
            expected++;
            actual = freezer_Fail_Hot.get(i).getArray().size();
            assertEquals(expected, actual);


        }
    }

    @Test
    public void alarm_Needed() throws Exception {

        SensorAlert sensorAlert;
        String expected;
        String actual;

        /**
         * CoolDown
         */
        for (int i = 0; i < cool_Down_Fail_Time.size(); i++) {
            sensorAlert = cool_Down_Fail_Time.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Fail[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            if (i < 4) {
                assertFalse(sensorAlert.Alarm_Needed());
            } else {
                assertTrue(sensorAlert.Alarm_Needed());
                expected = "Cooldown took to long";
                actual = sensorAlert.getReason();
                assertEquals(actual,expected);
            }
        }

        for (int i = 0; i < cool_Down_Pass.size(); i++) {
            sensorAlert = cool_Down_Pass.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.Alarm_Needed());
        }


        /**
         * Heat_Up
         */
        for (int i = 0; i < heat_Up_Fail_Time.size(); i++) {
            sensorAlert = heat_Up_Fail_Time.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Fail[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            if (i < 4) {
                assertFalse(sensorAlert.Alarm_Needed());
            } else {
                assertTrue(sensorAlert.Alarm_Needed());
                expected = "Reheating took to long";
                actual = sensorAlert.getReason();
                assertEquals(actual,expected);

            }
        }

        for (int i = 0; i < heat_Up_Pass.size(); i++) {
            sensorAlert = heat_Up_Pass.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.Alarm_Needed());
        }

        /**
         * Keep_Hot
         */
        for (int i = 0; i < keep_Hot_Fail_Time.size(); i++) {
            sensorAlert = keep_Hot_Fail_Time.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Fail[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            if (i < 4) {
                assertFalse(sensorAlert.Alarm_Needed());
            } else {
                assertTrue(sensorAlert.Alarm_Needed());
                expected = "Product have been out too long";
                actual = sensorAlert.getReason();
                assertEquals(actual,expected);

            }
        }

        for (int i = 0; i < keep_Hot_Fail_Cold.size(); i++) {
            sensorAlert = keep_Hot_Fail_Cold.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            if (i < 2) {
                assertFalse(sensorAlert.Alarm_Needed());
            } else {
                assertTrue(sensorAlert.Alarm_Needed());
                expected = "Product too cold";
                actual = sensorAlert.getReason();
                assertEquals(actual,expected);

            }
        }

        for (int i = 0; i < keep_Hot_Pass.size(); i++) {
            sensorAlert = keep_Hot_Pass.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.Alarm_Needed());
        }

        for (int i = 0; i < fridge_Fail_Hot.size(); i++) {
            sensorAlert = fridge_Fail_Hot.get(i);
            SensorAlert.addToArray(sensorAlert);
            if (i < 2) {
                assertFalse(sensorAlert.Alarm_Needed());
            } else {
                assertTrue(sensorAlert.Alarm_Needed());
                expected = "Fridge temperature to high";
                actual = sensorAlert.getReason();
                assertEquals(actual,expected);

            }
        }

        for (int i = 0; i < fridge_Pass.size(); i++) {
            sensorAlert = fridge_Pass.get(i);
            SensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.Alarm_Needed());
        }

        for (int i = 0; i < freezer_Fail_Hot.size(); i++) {
            sensorAlert = freezer_Fail_Hot.get(i);
            SensorAlert.addToArray(sensorAlert);
            if (i < 2) {
                assertFalse(sensorAlert.Alarm_Needed());
            } else {
                assertTrue(sensorAlert.Alarm_Needed());
                expected = "Freezer temperature too high";
                actual = sensorAlert.getReason();
                assertEquals(actual,expected);

            }
        }

        for (int i = 0; i < freezer_Pass.size(); i++) {
            sensorAlert = freezer_Pass.get(i);
            SensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.Alarm_Needed());
        }
    }


    @Test
    public void timer() throws Exception {


        SensorAlert sensorAlert;
        for (int i = 0; i < keep_Hot_Fail_Time.size(); i++) {
            sensorAlert = keep_Hot_Fail_Time.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Fail[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            if (i < 4) {
                assertFalse(sensorAlert.timer());
            } else {
                assertTrue(sensorAlert.timer());
            }
        }

        for (int i = 0; i < cool_Down_Fail_Time.size(); i++) {
            sensorAlert = cool_Down_Fail_Time.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Fail[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            if (i < 4) {
                assertFalse(sensorAlert.timer());
            } else {
                assertTrue(sensorAlert.timer());
            }
        }

        for (int i = 0; i < heat_Up_Fail_Time.size(); i++) {
            sensorAlert = heat_Up_Fail_Time.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Fail[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            if (i < 4) {
                assertFalse(sensorAlert.timer());
            } else {
                assertTrue(sensorAlert.timer());
            }
        }

        for (int i = 0; i < keep_Hot_Pass.size(); i++) {
            sensorAlert = keep_Hot_Pass.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.timer());
        }

        for (int i = 0; i < cool_Down_Pass.size(); i++) {
            sensorAlert = cool_Down_Pass.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.timer());
        }

        for (int i = 0; i < heat_Up_Pass.size(); i++) {
            sensorAlert = heat_Up_Pass.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            SensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.timer());
        }


    }


    @Test
    public void fails_3() throws Exception {


        for (int i = 0; i < freezer_Fail_Hot.size(); i++) {

            SensorAlert sensorAlert = freezer_Fail_Hot.get(i);
            SensorAlert.addToArray(sensorAlert);
            System.out.println(sensorAlert.getSensor().getValue());
            if (i < 2) {
                //System.out.println(sensorAlert.fails_3());
                assertFalse(sensorAlert.fails_3());
            } else {
                //System.out.println(sensorAlert.fails_3());
                assertTrue(sensorAlert.fails_3());
            }
        }

        for (int i = 0; i < fridge_Fail_Cold.size(); i++) {

            SensorAlert sensorAlert = fridge_Fail_Cold.get(i);
            SensorAlert.addToArray(sensorAlert);
            System.out.println(sensorAlert.getSensor().getValue());
            if (i < 2) {
                System.out.println(sensorAlert.fails_3());
                assertFalse(sensorAlert.fails_3());
            } else {
                System.out.println(sensorAlert.fails_3());
                assertTrue(sensorAlert.fails_3());
            }
        }

        for (int i = 0; i < fridge_Fail_Hot.size(); i++) {

            SensorAlert sensorAlert = fridge_Fail_Hot.get(i);
            SensorAlert.addToArray(sensorAlert);
            System.out.println(sensorAlert.getSensor().getValue());
            if (i < 2) {
                System.out.println(sensorAlert.fails_3());
                assertFalse(sensorAlert.fails_3());
            } else {
                System.out.println(sensorAlert.fails_3());
                assertTrue(sensorAlert.fails_3());
            }
        }

    }

    @Test
    public void keepTrack() throws Exception {
        SensorAlert sensorAlert;

        /**
         * Freezer test
         */
        for (int i = 0; i < freezer_Fail_Hot.size(); i++) {
            sensorAlert = freezer_Fail_Hot.get(i);
            sensorAlert.addToArray(sensorAlert);
            assertTrue(sensorAlert.keepTrack());
        }


        for (int i = 0; i < freezer_Pass.size(); i++) {
            sensorAlert = freezer_Pass.get(i);
            sensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.keepTrack());
        }

        /**
         * Fridge test
         */
        for (int i = 0; i < fridge_Fail_Cold.size(); i++) {
            sensorAlert = fridge_Fail_Cold.get(i);
            sensorAlert.addToArray(sensorAlert);
            assertTrue(sensorAlert.keepTrack());
        }


        for (int i = 0; i < fridge_Fail_Hot.size(); i++) {
            sensorAlert = freezer_Fail_Hot.get(i);
            sensorAlert.addToArray(sensorAlert);
            assertTrue(sensorAlert.keepTrack());
        }

        for (int i = 0; i < fridge_Pass.size(); i++) {
            sensorAlert = fridge_Pass.get(i);
            sensorAlert.addToArray(sensorAlert);
            assertFalse(sensorAlert.keepTrack());
        }

        /**
         * Keep_Hot test
         */
        for (int i = 0; i < keep_Hot_Fail_Cold.size(); i++) {
            sensorAlert = keep_Hot_Fail_Cold.get(i);
            sensorAlert.addToArray(sensorAlert);
            assertTrue(sensorAlert.keepTrack());
        }


        for (int i = 0; i < keep_Hot_Fail_Time.size(); i++) {
            sensorAlert = keep_Hot_Fail_Time.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Fail[i]);
            sensorAlert.getSensor().setDate(dateTime);
            sensorAlert.addToArray(sensorAlert);
            if (i < 4) {
                assertTrue(sensorAlert.keepTrack());
            } else {
                assertFalse(sensorAlert.keepTrack());
            }
        }

        for (int i = 0; i < keep_Hot_Pass.size(); i++) {
            sensorAlert = keep_Hot_Pass.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            sensorAlert.addToArray(sensorAlert);
            assertTrue(sensorAlert.keepTrack());

        }

        /**
         * heat_Up
         */
        for (int i = 0; i < heat_Up_Fail_Time.size(); i++) {
            sensorAlert = heat_Up_Fail_Time.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Fail[i]);
            sensorAlert.getSensor().setDate(dateTime);
            sensorAlert.addToArray(sensorAlert);
            assertTrue(sensorAlert.keepTrack());
        }

        for (int i = 0; i < heat_Up_Pass.size(); i++) {
            sensorAlert = heat_Up_Pass.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            sensorAlert.addToArray(sensorAlert);
            if (i < 4) {
                //System.out.println("timer " + sensorAlert.timer());
                //System.out.println(sensorAlert.keepTrack());
                assertTrue(sensorAlert.keepTrack());
            } else {
                //System.out.println("timer " + sensorAlert.timer());
                //System.out.println(sensorAlert.keepTrack());
                assertFalse(sensorAlert.keepTrack());
            }
        }


        /**
         * cool_Down
         */
        for (int i = 0; i < cool_Down_Fail_Time.size(); i++) {
            sensorAlert = cool_Down_Fail_Time.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Fail[i]);
            sensorAlert.getSensor().setDate(dateTime);
            sensorAlert.addToArray(sensorAlert);
            System.out.println("timer " + sensorAlert.timer());
            System.out.println(sensorAlert.keepTrack());
            assertTrue(sensorAlert.keepTrack());
        }

        for (int i = 0; i < cool_Down_Pass.size(); i++) {
            sensorAlert = cool_Down_Pass.get(i);
            LocalDateTime dateTime = sensorAlert.getSensor().getDate().plusMinutes(time_Pass[i]);
            sensorAlert.getSensor().setDate(dateTime);
            sensorAlert.addToArray(sensorAlert);
            if (i < 4) {
                System.out.println("timer " + sensorAlert.timer());
                System.out.println(sensorAlert.keepTrack());
                assertTrue(sensorAlert.keepTrack());
            } else {
                System.out.println("timer " + sensorAlert.timer());
                System.out.println(sensorAlert.keepTrack());
                assertFalse(sensorAlert.keepTrack());
            }
        }
    }
}

