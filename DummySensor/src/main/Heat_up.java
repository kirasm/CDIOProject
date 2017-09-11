package main;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Matt_Lab on 02/06/2017.
 */
public class Heat_up extends Sensor{

    protected LocalDateTime date;
    protected int ID;
    protected double start_Value = 5.0;
    protected double value = start_Value;
    protected int type;

    public Heat_up(int ID, String IP, int port, int type){
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
            value = value + (Math.random() * ((5 - 1) + 1));
    }
}
