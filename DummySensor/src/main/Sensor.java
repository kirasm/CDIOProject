package main;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Classname: Sensor
 * <p>
 * Bugs: none known
 *
 * @author Casper Bodskov
 * @version v.0.1
 */
public abstract class Sensor {
//    private int ID;
//    private INet connection;
//    private int time = 5000;
//    private long lastUpdated;

    protected int ID;
    protected double Value;
    protected LocalDateTime Date;
    protected int type;
    protected INet Conn;



    abstract int Get_ID();
    abstract double Get_Value();
    abstract LocalDateTime Get_Date();
    abstract void Set_Value();
    abstract void Set_Date();
    abstract int getType();


//    public Sensor(int ID, String IP, int port) {
//        this.ID = ID;
//        connection = new Tcp(IP, port);
//        for (int i = 0; i < 5; i++) {
//            if (identify()) {
//                break;
//            }
//        }
//
//    }

//    public boolean identify() {
//
//        try {
//            connection.send("identify:" + ID);
//            if (connection.receive().contains("ok")) {
//                //connection.disconnect();
//                return true;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//

    public void sendTemp() throws IOException {

        Conn.send("temp|" + Get_ID() + ":" + Get_Value() + "%" + getType());
        String data = Conn.receive().trim();
        Conn.disconnect();
    }
//
//    public long getLastUpdated() {
//        return lastUpdated;
//    }
//
//    public int getTime() {
//        return time;
//    }

//    public void updateSleepTime() throws IOException {
//
//        connection.send("get time");
//        String data = connection.receive().trim();
//        time = Integer.parseInt(data);
//
//    }
}
