package main;

import java.time.LocalDateTime;

/**
 * Created by Matt_Lab on 02/06/2017.
 */
public class Cool_Down extends Sensor {

    protected LocalDateTime date;
    protected int id;
    protected double start_Value = 65.0;
    protected double value = start_Value;
    protected int type;


    public Cool_Down(int id, String IP, int port, int type){
        this.id = id;
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
        return id;
    }

    @Override
    public double Get_Value() {
        return value;
    }


    @Override
    public void Set_Value() {
            value = value - (Math.random() * ((5 - 1) + 1));
    }
}
