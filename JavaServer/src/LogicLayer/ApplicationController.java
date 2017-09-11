package LogicLayer;

import DataLayer.IDatabase;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;


/**
 * Created by Matt_Lab on 14/03/2017.
 */
public class ApplicationController implements Observer {

    public PriorityQueue unhandledMeasurements = new PriorityQueue<Sensor>();

    private IDatabase SQL;


    public ApplicationController(IDatabase SQL) {
        this.SQL = SQL;
        //sensorList.add(new Sensor("0000000001", "192.168.43.98", 806));
    }

    public void start() {
        ThreadPoolController server = new ThreadPoolController(2342);
        Thread a = new Thread(server);
        a.start();

        System.out.println("Server Started!");


        JmDNS jmdns = null;
        try {
            jmdns = JmDNS.create(InetAddress.getLocalHost());


            // Register a service
            ServiceInfo serviceInfo = ServiceInfo.create("_sensorserver._tcp.local.", "FoodServer", 2342, "");
            jmdns.registerService(serviceInfo);

            System.out.println("DNS ready.");


        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            while (true) {
                Thread.sleep(5000);
                while (!unhandledMeasurements.isEmpty()) {
                    System.out.println(unhandledMeasurements.size());
                    Sensor sensor = (Sensor) unhandledMeasurements.remove();
                    SensorAlert sensorAlert = new SensorAlert(sensor);
                    SensorAlert.addToArray(sensorAlert);
                    if (sensorAlert.Alarm_Needed()) {
                        try {
                            SQL.insertAlarm(sensorAlert);
                            System.out.println("SensorAlert send");
                        } catch (IDatabase.DataException e) {
                            e.printStackTrace();
                        }
                    }

                }

//                if (System.in.read() == 'q') {
//                    break;
//                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
        jmdns.unregisterAllServices();


    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Updated: " + ((Sensor) arg).getID());
        unhandledMeasurements.add(((Sensor) arg));
        try {
            SQL.insertMeasurement(((Sensor) arg));
        } catch (IDatabase.DataException e) {
            e.printStackTrace();
        }

//MysqlDatabase stuff
    }
}
