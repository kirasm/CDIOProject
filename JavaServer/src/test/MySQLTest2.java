import DataLayer.IDatabase;
import DataLayer.MysqlDatabase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;


/**
 * Created by ldylab on 20-04-2017.
 */

public class MySQLTest2 {
    static IDatabase SQL = null;


    @BeforeClass
    public static void setUp() throws IDatabase.DataException {
        SQL = new MysqlDatabase("dyrsted.duckdns.org", "cdio_d2", "api", "", 3306);

        SQL.runQuery2("SET FOREIGN_KEY_CHECKS = 0;");
        SQL.runQuery2("TRUNCATE TABLE employed;");
        SQL.runQuery2("TRUNCATE TABLE department;");
        SQL.runQuery2("TRUNCATE TABLE sensor;");
        SQL.runQuery2("TRUNCATE TABLE address;");
        SQL.runQuery2("TRUNCATE TABLE alarm;");
        SQL.runQuery2("TRUNCATE TABLE USER;");
        SQL.runQuery2("TRUNCATE TABLE measurements;");


        SQL.runQuery2("INSERT IGNORE INTO address VALUES ('232', 'Pølsevej', '1', 'Denmark', '132', '2', 'Køge')");
        SQL.runQuery2("INSERT IGNORE INTO department VALUES ('232', '232', 'MC', '1', '232')");
        SQL.runQuery2("INSERT IGNORE INTO employed VALUES ('22rv', '232')");
        SQL.runQuery2("INSERT IGNORE INTO employed VALUES ('wwr3rb3bq', '232')");
        SQL.runQuery2("INSERT IGNORE INTO measurements VALUES ('10', '2017-05-02 13:07:55', '1.3')");
        SQL.runQuery2("INSERT IGNORE INTO measurements VALUES ('10', '2017-05-02 13:08:55', '1.2')");
        SQL.runQuery2("INSERT IGNORE INTO measurements VALUES ('10', '2017-05-02 13:09:55', '1.4')");
        SQL.runQuery2("INSERT IGNORE INTO sensor VALUES ('10', '1', '232', 'k')");
        SQL.runQuery2("INSERT IGNORE INTO user VALUES ('22rv', 've', NULL, NULL, NULL, NULL)");
        SQL.runQuery2("INSERT IGNORE INTO user VALUES ('epokmwvoke9i', '09i', '9', '9', '9', '09')");
        SQL.runQuery2("INSERT IGNORE INTO user VALUES ('wwr3rb3bq', 'ole', NULL, NULL, NULL, NULL)");
        SQL.runQuery2("INSERT IGNORE INTO alarm (Alarm_ID, DataTime, Sensor_ID, Reason) VALUES (NULL, '2017-05-02 13:59:33', '231', 'sdcasas');");
        SQL.runQuery2("INSERT IGNORE INTO alarm (Alarm_ID, DataTime, Sensor_ID, Reason) VALUES (NULL, '2017-05-02 13:59:32', '231', 'lijdfjlk');");
        SQL.runQuery2("INSERT IGNORE INTO alarm (Alarm_ID, DataTime, Sensor_ID, Reason) VALUES (NULL, '2017-05-02 13:59:35', '231', ',jlndljgk');");
        SQL.runQuery2("INSERT IGNORE INTO alarm (Alarm_ID, DataTime, Sensor_ID, Reason) VALUES (NULL, '2017-05-02 13:59:37', '231', ',jlljgk');");
        SQL.runQuery2("INSERT IGNORE INTO alarm (Alarm_ID, DataTime, Sensor_ID, Reason) VALUES (NULL, '2017-05-02 13:59:39', '231', ',dwdljgk');");
        SQL.runQuery2("SET FOREIGN_KEY_CHECKS = 1");


    }

    @AfterClass
    public static void takeDown() throws IDatabase.DataException, SQLException {

        SQL.dbDisconnect();

    }

    @Test
    public void Get_All_Users_In_Dep() throws IDatabase.DataException, SQLException {


        ResultSet rs = SQL.runQuery(String.format("CALL Get_All_Users_In_Dep(232);"));

        rs.next();
        assertTrue(rs.getString(1).equals("ve"));
        rs.next();
        assertTrue(rs.getString(1).equals("ole"));

    }

    @Test
    public void Get_All_Sensors_By_Dep() throws IDatabase.DataException, SQLException {
        ResultSet rs = SQL.runQuery(String.format("CALL Get_All_Sensors_By_Dep(232);"));

        rs.next();
        assertTrue(rs.getString(1).equals("10"));
        assertTrue(rs.getString(2).equals("1"));
        assertTrue(rs.getString(3).equals("232"));
        assertTrue(rs.getString(4).equals("k"));
    }

    @Test
    public void Get_Sensor_Value_By_Date() throws IDatabase.DataException, SQLException {
        ResultSet rs = SQL.runQuery(String.format("CALL Get_Sensor_Value_By_Date(10,'2017-05-02 10:00:00','2017-05-02 19:07:59');"));

        rs.next();
        assertTrue(rs.getString(1).equals("1.3"));
        rs.next();
        assertTrue(rs.getString(1).equals("1.2"));
        rs.next();
        assertTrue(rs.getString(1).equals("1.4"));

    }

    @Test
    public void Avrage_Sensors() throws IDatabase.DataException, SQLException {
        ResultSet rs = SQL.runQuery(String.format("SELECT Avrage_Sensors();"));

        rs.next();
        assertTrue(rs.getString(1).equals("1.0"));
    }

    @Test
    public void Average_Sensor_Num() throws IDatabase.DataException, SQLException {
        ResultSet rs = SQL.runQuery(String.format("SELECT Average_Sensor_Num();"));

        rs.next();
        assertTrue(rs.getString(1).equals("1.0"));
    }

    @Test
    public void More_Than_X_Alarms() throws IDatabase.DataException, SQLException {
        ResultSet rs = SQL.runQuery(String.format("CALL More_Than_X_Alarms(1,2);"));

        rs.next();
        assertTrue(rs.getString(1).equals("231"));
        String a = rs.getString(2);

        assertTrue(rs.getString(2).equals("5"));
    }
}
