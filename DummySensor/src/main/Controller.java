package main;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Classname: Controller
 * <p>
 * Bugs: none known
 *
 * @author Casper Bodskov
 * @version v.0.1
 */

public class Controller {

    ArrayList<Sensor> sensors = new ArrayList<Sensor>();
    private long current_Time;
    private long next_send = current_Time + 5000;

    int[] id = new int[]{23123, 123123, 123123, 324234, 25235, 536456, 342432, 63456345, 63453, 643645672};

    public void start() {

        InetAddress address = null;
        try {
            //address = InetAddress.getByName("FoodSystem");
            address = InetAddress.getByName("localhost");
            String ip = address.getHostAddress().toString();

                sensors.add(new Cool_Down(100, ip, 2342, 1));
                sensors.add(new Cool_Down(101, ip, 2342, 1));

                sensors.add(new Freezer(500, ip, 2342, 5));
                sensors.add(new Freezer(501, ip, 2342, 5));

                sensors.add(new Heat_up(200, ip, 2342, 2));
                sensors.add(new Heat_up(201, ip, 2342, 2));

                sensors.add(new Keep_Hot(300, ip, 2342, 3));
                sensors.add(new Keep_Hot(301, ip, 2342, 3));

                sensors.add(new Fridge(400, ip, 2342, 4));
                sensors.add(new Fridge(401, ip, 2342, 4));

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        while (true){
            current_Time = System.currentTimeMillis();
            if (next_send <= current_Time) {
                for(int i = 0; i<sensors.size(); i++){
                    try {
                        sensors.get(i).Set_Value();
                        sensors.get(i).sendTemp();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    next_send = current_Time + 10000;
                }
            }
        }
//
//
//        while (true) {
//            for (Sensor sensor : sensors) {
//                if (System.currentTimeMillis() - sensor.getLastUpdated() > sensor.getTime()) {
//                    try {
//                        //sensor.updateSleepTime();
//                        sensor.sendTemp();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }

    }


}
