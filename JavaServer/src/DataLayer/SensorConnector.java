package DataLayer;

import ConnectionServices.Tcp;
import LogicLayer.Sensor;

import java.io.IOException;
import java.net.Socket;


public class SensorConnector implements Runnable {

    protected Tcp clientSocket = null;
    protected String serverText = null;

    public SensorConnector(Socket clientSocket, String serverText) {
        this.clientSocket = new Tcp(clientSocket);
        this.serverText = serverText;
    }

    public void run() {
        try {


            String data = clientSocket.receive();

            if (data.contains("temp")) {
                int index1 = data.indexOf(':');
                int index2 = data.indexOf('|') + 1;
                int index3 = data.indexOf('%') + 1;
                String id_String = data.substring(index2, index1);
                String type_String = data.substring(index3);
                double value = Double.parseDouble(data.substring(index1 + 1, index3-1).trim());
                int id = Integer.parseInt(id_String);
                int type = Integer.parseInt(type_String);


                //System.out.println("Rec: '" + data + "'");
                //System.out.println("id:" + id);
                //System.out.println("value:" + value);

                Sensor temp = Sensor.findSensor(id);
                if (temp == null) {
                    Sensor.sensorList.add(new Sensor(id, type, value));
                } else {
                    temp.setValue(value);
                }

                clientSocket.send("5");
                clientSocket.disconnect();
            }


        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}