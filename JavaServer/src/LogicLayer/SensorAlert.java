package LogicLayer;

import java.util.ArrayList;

/**
 * Classname: SensorAlert
 * <p>
 * Bugs: none known
 *
 * @author Casper Bodskov
 * @version v.0.1
 */
public class SensorAlert {

    private static ArrayList<SensorAlert> CriticalMeasurements = new ArrayList<SensorAlert>();
    private Sensor sensor;
    private int numOfFails;
    private String reason;
    private Sensor currentSensor;
    private boolean trigger;


    public SensorAlert(Sensor sensor) {
        this.sensor = sensor;

    }

    public static ArrayList getArray() {
        return CriticalMeasurements;
    }

    public static void addToArray(SensorAlert sensorAlert) {
        CriticalMeasurements.add(sensorAlert);
    }

    public static void removeAllFromArray() {
        CriticalMeasurements.clear();

    }


    public boolean Alarm_Needed() {

        currentSensor = getSensor();

        switch (currentSensor.getType()) {
            case 1://cooldown
                if (timer() && currentSensor.getValue() > 5.0) {
                    trigger = true;
                    reason = "Cooldown took to long";
                    setReason(reason);
                } else {
                    trigger = false;
                }
                break;
            case 2:             //reheating
                if (timer() && currentSensor.getValue() < 75.0) {
                    trigger = true;
                    reason = "Reheating took to long";
                    setReason(reason);
                } else {
                    trigger = false;
                }
                break;
            case 3:     //keep hot
                if (fails_3()) {
                    int lowTemp=0;
                    for (int i =0; i<CriticalMeasurements.size();i++){
                        SensorAlert sensorAlert = CriticalMeasurements.get(i);
                        if (getSensor().getID() == sensorAlert.getSensor().getID() && sensorAlert.getSensor().getValue()<65.0){
                            lowTemp++;
                        }
                    }
                    if (lowTemp>2){
                        trigger = true;
                        reason = "Product too cold";
                        setReason(reason);
                    }else if (timer()){
                        trigger = true;
                        reason = "Product have been out too long";
                        setReason(reason);
                    }
                } else {
                    trigger = false;
                }
                break;
            case 4:     //fridge
                if (keepTrack() && fails_3()) {
                    trigger = true;
                    reason = "Fridge temperature to high";
                    setReason(reason);
                } else {
                    trigger = false;
                }
                break;
            case 5:     //freezer
                if (keepTrack() && fails_3()) {
                    trigger = true;
                    reason = "Freezer temperature too high";
                    setReason(reason);
                } else {
                    trigger = false;
                }
                break;
        }

        if (keepTrack() == false) {
            for (int j = 0; j < CriticalMeasurements.size(); j++) {
                if (currentSensor.getID() == CriticalMeasurements.get(j).getSensor().getID())
                    CriticalMeasurements.remove(j);
            }
        }
        return trigger;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    /**
     * @return true if more than 3 hours have passed since first measurement
     */
    public boolean timer() {

        long startTime = 0;
        long endTime = getAlarmDateLong();
        SensorAlert oldSensorAlert = this;
        for (int i = 0; i < CriticalMeasurements.size(); i++) {
            if (getSensor().getID() == CriticalMeasurements.get(i).getID()) {
                oldSensorAlert = CriticalMeasurements.get(i);

                if (startTime == 0) {
                    startTime = endTime;
                }
                if (oldSensorAlert.getAlarmDateLong() < startTime) {
                    startTime = oldSensorAlert.getAlarmDateLong();
                }
            }
        }
        long diff = endTime - startTime;
        //3h = 10800
        if (diff > 60) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return true if measurement failed 3 times
     */
    public boolean fails_3() {
        SensorAlert oldSensorAlert;
        numOfFails = 0;
        for (int i = 0; i < CriticalMeasurements.size(); i++) {
            oldSensorAlert = CriticalMeasurements.get(i);
            if (getID() == oldSensorAlert.getID()) {
                numOfFails++;
            }
        }
        if (numOfFails > 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return true if the mesurement does not mach the parameters
     */
    public boolean keepTrack() {

        switch (getSensor().getType()) {
            case 1:     //Cool_Down
                if (!timer() && getSensor().getValue() > 5.0) {
                    return true;
                } else if (timer() && getSensor().getValue() > 5.0) {
                    return true;
                } else
                    return false;

            case 2:     //Heat_Up
                if (!timer() && getSensor().getValue() < 75.0) {
                    return true;
                } else if (timer() && getSensor().getValue() < 75.0) {
                    return true;
                } else {
                    return false;
                }
            case 3:     //Keep_Hot
                if (!timer() || getSensor().getValue() < 65.0) {
                    //System.out.println(!timer());
                    return true;
                } else {
                    return false;
                }
            case 4:     //Fridge
                if (getSensor().getValue() > 5.0 || getSensor().getValue() < 0.5) {
                    return true;
                } else {
                    return false;
                }
            case 5:     //Freezer
                if (getSensor().getValue() > -18.0) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    public int getID() {
        return getSensor().getID();
    }

    public long getAlarmDateLong() {
        return getSensor().getDateLong();
    }
}
