package DataLayer;

import LogicLayer.Sensor;
import LogicLayer.SensorAlert;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Matt_Lab on 14/03/2017.
 */
public class MysqlDatabase implements IDatabase {
    private Properties connectProps;
    private String host;
    private String dbName;
    private String user;
    private String pass;
    private int port;
    private Connection connect = null;

    public MysqlDatabase(String host, String db, String user, String pass, int port) {
        this.host = host;
        this.dbName = db;
        this.user = user;
        this.pass = pass;
        this.port = port;

        connectProps = new Properties();
        connectProps.put("user", this.user);
        connectProps.put("password", this.pass);

    }

    public void dbConnect() throws IDatabase.DataException {
        try {
            if (connect != null && !connect.isClosed()) return;
        } catch (SQLException e) {

        }

        try {
            this.connect = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbName + "?verifyServerCerficate=false&useSSL=true&requireSSL=false&autoReconnect=true&failOverReadOnly=false&maxReconnects=10", connectProps);
            //this.connect.setAutoCommit(false);
        } catch (SQLException var3) {
            throw new DataException(var3.getMessage());
        }
    }

    public void dbDisconnect() {
        try {
            this.connect.close();
        } catch (SQLException var2) {
        }
    }

    public void insertMeasurement(Sensor sensor) throws IDatabase.DataException {
        String query = String.format("CALL Create_Measurement('%s', '%s');", sensor.getID(), sensor.getValue());
        runQuery2(query);

    }

    public void insertAlarm(SensorAlert sensorAlert) throws IDatabase.DataException {
        String query = String.format("CALL Create_Alarm('%s', '%s');", sensorAlert.getSensor().getID(), sensorAlert.getReason());
        runQuery2(query);

    }

    public ResultSet runQuery(String query) throws IDatabase.DataException {
        this.dbConnect();

        //System.out.println(query);
        try {
            Statement e = this.connect.createStatement();
            ResultSet a = e.executeQuery(query);
            return a;
        } catch (SQLException var4) {
            throw new DataException(var4.getMessage());
        }
    }

    public boolean runQuery2(String query) throws IDatabase.DataException {
        this.dbConnect();

        //System.out.println(query);
        try {
            Statement e = this.connect.createStatement();
            boolean a = e.execute(query);
            return a;
        } catch (SQLException var4) {
            throw new DataException(var4.getMessage());
        }
    }

    public void commit() throws SQLException {
        //connect.commit();
    }
}
