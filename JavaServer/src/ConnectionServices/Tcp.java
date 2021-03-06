package ConnectionServices;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Classname: Tcp
 * <p>
 * Bugs: none known
 *
 * @author Casper Bodskov & Rasmus Kirkeskov
 * @version v.0.2
 */
public class Tcp implements INet {
    private String IP;
    private int port;
    private Socket clientSocket;

    //For Sensor Connection

    public Tcp(String IP, int port) {

        this.IP = IP;
        this.port = port;
    }

    //For MysqlDatabase Connection

    public Tcp(Socket in) {
        this.clientSocket = in;
        this.IP = in.getInetAddress().toString();
        this.port = in.getPort();
    }

    @Override
    public void connect() throws IOException {
        clientSocket = new Socket(IP, port);

    }

    public void disconnect() throws IOException {
        clientSocket.close();
    }

    public void send(String data) throws IOException {
        if (clientSocket == null || !clientSocket.isConnected()){
            connect();
        }

        //System.out.println("Sending: " + data);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(data + '\n');

    }

    public String receive() throws IOException {

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String data = inFromServer.readLine();

        //System.out.println("Received: " + data);

        return data;

    }


    @Override
    public boolean equals(Object obj) {
        if (IP.equals(((Tcp) obj).IP) && port == ((Tcp) obj).port) {
            return true;
        }
        return false;
    }
}
