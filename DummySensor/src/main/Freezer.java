package main;

import java.time.LocalDateTime;


public class Freezer extends Sensor {

    protected int ID;
    protected double Value;
    protected LocalDateTime Date;
    protected int type;

    public Freezer(int ID, String IP, int port, int type) {

        this.ID = ID;
        Conn = new TCP(IP, port);
        this.type = type;

    }

    //Get Freezer ID

    public int Get_ID() {

        return this.ID;

    }

    @Override
    public int getType(){
        return type;
    }

    //Get Temperature

    public double Get_Value() {

        return this.Value;

    }

    //Get the local date & time

    public LocalDateTime Get_Date() {

        return this.Date;

    }


    //Set an random value for the measurement between -1 to 5

    public void Set_Value() {

        this.Value = (Math.random()*(5)-21);

    }

    //Set the date and time

    public void Set_Date() {
        this.Date = LocalDateTime.now();

    }

}
