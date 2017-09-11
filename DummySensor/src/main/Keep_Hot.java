package main;

import java.time.LocalDateTime;

/**
 * Created by Matt_Lab on 02/06/2017.
 */
public class Keep_Hot extends Sensor {

    protected LocalDateTime date;
    protected int ID;
    protected double value;
    protected double start_Value = 72.0;
    protected int type;


    public Keep_Hot(int ID, String IP, int port, int type){
        this.ID = ID;
        Conn = new TCP(IP, port);
        this.type = type;
    }


    @Override
    public int getType(){
        return type;
    }

    @Override
    public void Set_Date(){
        date = LocalDateTime.now();
    }

    @Override
    public LocalDateTime Get_Date() {
        return date;
    }

    @Override
    public int Get_ID() {
        return ID;
    }

    @Override
    public double Get_Value() {
        return value;
    }

    @Override
    public void Set_Value() {
        value = start_Value + (Math.random()*10-10);
    }
}
