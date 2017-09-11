package LogicLayer;

import PresentationLayer.Main;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Classname: Sensor
 * <p>
 * Bugs: none known
 *
 * @author Mathias & Rasmus Kirkeskov
 * @version v.0.2
 */
public class Sensor extends Observable implements Comparable<Sensor>{

    public static ArrayList<Sensor> sensorList = new ArrayList<Sensor>();

    private int type;
    private LocalDateTime date;
    private double value;
    private int ID;
    private long dateLong;

    public Sensor(int ID, int type, double value) {
        this.ID = ID;
        this.type = type;
        this.value = value;
        this.date = LocalDateTime.now();
        if (Main.applicationController != null){
            this.addObserver(Main.applicationController);
        }

        if (Main.applicationController != null) {

            this.addObserver(Main.applicationController);

        }

    }

    public int getID() {
        return ID;
    }

    public double getValue() {
        return value;
    }

    public int getType(){
        return type;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public long getDateLong(){
        dateLong = date.getLong(ChronoField.SECOND_OF_DAY);
        return dateLong;
    }


    public void setValue(double value) {
        //System.out.println("Setting val " + value);
        this.value = value;
        setChanged();
        notifyObservers(this);
    }

    public void setDate(LocalDateTime date){
        this.date = date;
    }

    public static Sensor findSensor(int id) {
        for (Sensor sensor : sensorList) {
            if (sensor.getID() == id) {
                return sensor;
            }
        }
        return null;
    }


    @Override
    public int compareTo(Sensor o) {
        return 0;
    }
}
