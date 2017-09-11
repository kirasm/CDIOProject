package DataLayer;

import LogicLayer.SensorAlert;
import LogicLayer.Sensor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Matt_Lab on 14/03/2017.
 */
public interface IDatabase {
    void dbConnect() throws IDatabase.DataException;

    void dbDisconnect() throws IDatabase.DataException;

    void insertMeasurement(Sensor sensor) throws IDatabase.DataException;
    void insertAlarm(SensorAlert sensorAlert) throws IDatabase.DataException;
    ResultSet runQuery(String query) throws IDatabase.DataException;
    boolean runQuery2(String query) throws IDatabase.DataException;
    void commit() throws SQLException;


    class DataException extends Exception {
        private static final long serialVersionUID = 7355418276854569229L;

        public DataException(String msg, Throwable e) {
            super(msg, e);
        }

        public DataException(String msg) {
            super(msg);
        }
    }
}
