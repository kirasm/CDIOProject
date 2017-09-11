import DataLayer.MysqlDatabase;
import DataLayer.IDatabase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


/**
 * Created by ldylab on 20-04-2017.
 */

public class MySQLTest {
    static IDatabase SQL = null;

    private static ArrayList<String> depsToDelete = new ArrayList<>();
    private static ArrayList<String> addToDelete = new ArrayList<>();
    private static ArrayList<String> usersToDelete = new ArrayList<>();
    private static int x = 0;
    private static ArrayList<String> measurementsToDelete = new ArrayList<>();
    private static ArrayList<String> sensorToDelete = new ArrayList<>();


    @BeforeClass
    public static void setUp() {
        SQL = new MysqlDatabase("dyrsted.duckdns.org", "cdio_d2", "api", "", 3306);
    }

    @AfterClass
    public static void takeDown() throws IDatabase.DataException, SQLException {


        //SQL.runQuery2("START TRANSACTION;");

        for (String s : depsToDelete) {
            SQL.runQuery2(String.format("DELETE FROM department WHERE Department_ID = '%s';", s));
            SQL.commit();
        }

        for (String s : addToDelete) {
            SQL.runQuery2(String.format("DELETE FROM address WHERE Address_ID = '%s';", s));
            SQL.commit();
        }

        for (String s : usersToDelete) {
            SQL.runQuery2(String.format("DELETE FROM user WHERE User_Name = '%s';", s));
            SQL.commit();
        }
        for (String s : measurementsToDelete) {
            SQL.runQuery2(String.format("DELETE FROM measurements WHERE Sensor_ID = '%s';", s));
            SQL.commit();
        }
        for (String s : sensorToDelete) {
            SQL.runQuery2(String.format("DELETE FROM sensor WHERE Sensor_ID = '%s';", s));
            SQL.commit();
        }

        //SQL.runQuery2("COMMIT;");

        SQL.commit();
        SQL.dbDisconnect();

    }

    @Test
    public void Create_Department_no_owner() throws IDatabase.DataException, SQLException {

        String At_Street = "Karls gade";
        int At_House_No = 4;
        String At_Country = "Danmark";
        String At_City = "Roskilde";
        int At_Zip = 2690;
        String At_State = "NULL";
        String At_Name = "McDonalds";
        int At_Type = 1;
        String At_Owner_Department_ID = "NULL";

        String a = String.format("CALL Create_Department('%s', %s, '%s','%s',%s, %s,'%s',%s, %s )", At_Street, At_House_No, At_Country, At_City, At_Zip, At_State, At_Name, At_Type, At_Owner_Department_ID);
        System.out.println(a);

        SQL.runQuery(a);
        SQL.commit();

        ResultSet rs = SQL.runQuery(String.format("SELECT * FROM  department WHERE Name = '%s'", At_Name));
        String departmentID = "";
        String adress_ID = "";
        String Name = "";
        int Type = 0;
        String Owner_department_ID = "";

        while (rs.next()) {
            departmentID = rs.getString(1);
            adress_ID = rs.getString(2);
            Name = rs.getString(3);
            Type = rs.getInt(4);
            Owner_department_ID = rs.getString(5);
            System.out.println(departmentID + "|" + adress_ID);
        }

        assertTrue(At_Type == Type);
        assertTrue(At_Name.equals(Name));
        assertTrue(departmentID.equals(Owner_department_ID));


        rs = SQL.runQuery(String.format("SELECT * FROM  address WHERE Address_ID = '%s'", adress_ID));

        String Street = "";
        int House_No = 0;
        String Country = "";
        int Zip = 0;
        String State = "";
        String City = "";

        while (rs.next()) {
            Street = rs.getString(2);
            House_No = rs.getInt(3);
            Country = rs.getString(4);
            Zip = rs.getInt(5);
            State = rs.getString(6);
            City = rs.getString(7);
        }

        assertTrue(At_Street.equals(Street));
        assertTrue(At_House_No == House_No);
        assertTrue(At_Country.equals(Country));
        assertTrue(At_Zip == Zip);
        assertTrue(State == null || At_State.equals(State));
        assertTrue(At_City.equals(City));

        depsToDelete.add(departmentID);
        addToDelete.add(adress_ID);

        SQL.dbDisconnect();


    }

    @Test
    public void Create_Department_owner() throws IDatabase.DataException, SQLException {
        String At_Street = "Karlsdsadgf";
        int At_House_No = 4;
        String At_Country = "Danmark";
        String At_City = "Roskilde";
        int At_Zip = 2670;
        String At_State = "NULL";
        String At_Name = "McDonalds2";
        int At_Type = 1;
        String At_Owner_Department_ID = "NULL";

        String a = String.format("CALL Create_Department('%s', %s, '%s','%s',%s, %s,'%s',%s, %s )", At_Street, At_House_No, At_Country, At_City, At_Zip, At_State, At_Name, At_Type, At_Owner_Department_ID);
        System.out.println(a);

        SQL.runQuery(a);
        SQL.commit();


        ResultSet rs = SQL.runQuery(String.format("SELECT * FROM  department WHERE Name = '%s'", At_Name));
        String departmentID = "";
        String departmentID_delete = "";

        String adress_ID = "";
        String adress_ID_delete = adress_ID;
        String Name = "";
        int Type = 0;
        String Owner_department_ID = "";

        while (rs.next()) {
            departmentID = rs.getString(1);
            departmentID_delete = departmentID;
            adress_ID = rs.getString(2);
            adress_ID_delete = adress_ID;
            Name = rs.getString(3);
            Type = rs.getInt(4);
            Owner_department_ID = rs.getString(5);
            System.out.println(departmentID + "|" + adress_ID);
        }

        assertTrue(At_Type == Type);
        assertTrue(At_Name.equals(Name));
        assertTrue(departmentID.equals(Owner_department_ID));


        rs = SQL.runQuery(String.format("SELECT * FROM  address WHERE Address_ID = '%s'", adress_ID));

        String Street = "";
        int House_No = 0;
        String Country = "";
        int Zip = 0;
        String State = "";
        String City = "";

        while (rs.next()) {
            Street = rs.getString(2);
            House_No = rs.getInt(3);
            Country = rs.getString(4);
            Zip = rs.getInt(5);
            State = rs.getString(6);
            City = rs.getString(7);
        }

        assertTrue(At_Street.equals(Street));
        assertTrue(At_House_No == House_No);
        assertTrue(At_Country.equals(Country));
        assertTrue(At_Zip == Zip);
        assertTrue(State == null || At_State.equals(State));
        assertTrue(At_City.equals(City));


        At_Street = "wnrarnarn";
        At_House_No = 4;
        At_Country = "UK";
        At_City = "Roskilde";
        At_Zip = 2670;
        At_State = "NULL";
        At_Name = "McDonalds2";
        At_Type = 2;
        At_Owner_Department_ID = departmentID;

        a = String.format("CALL Create_Department('%s', %s, '%s','%s',%s, %s,'%s',%s, %s )", At_Street, At_House_No, At_Country, At_City, At_Zip, At_State, At_Name, At_Type, At_Owner_Department_ID);
        SQL.runQuery(a);
        SQL.commit();


        rs = SQL.runQuery(String.format("SELECT * FROM  department WHERE Name = '%s'", At_Name));
        departmentID = "";
        adress_ID = "";
        Name = "";
        Type = 0;
        Owner_department_ID = "";

        while (rs.next()) {
            departmentID = rs.getString(1);
            adress_ID = rs.getString(2);
            Name = rs.getString(3);
            Type = rs.getInt(4);
            Owner_department_ID = rs.getString(5);
            System.out.println(departmentID + "|" + adress_ID);
        }

        assertTrue(At_Type == Type);
        assertTrue(At_Name.equals(Name));
        assertTrue(At_Owner_Department_ID.equals(Owner_department_ID));


        rs = SQL.runQuery(String.format("SELECT * FROM  address WHERE Address_ID = '%s'", adress_ID));

        Street = "";
        House_No = 0;
        Country = "";
        Zip = 0;
        State = "";
        City = "";

        while (rs.next()) {
            Street = rs.getString(2);
            House_No = rs.getInt(3);
            Country = rs.getString(4);
            Zip = rs.getInt(5);
            State = rs.getString(6);
            City = rs.getString(7);
        }

        assertTrue(At_Street.equals(Street));
        assertTrue(At_House_No == House_No);
        assertTrue(At_Country.equals(Country));
        assertTrue(At_Zip == Zip);
        assertTrue(State == null || At_State.equals(State));
        assertTrue(At_City.equals(City));


        depsToDelete.add(departmentID);
        addToDelete.add(adress_ID);
        depsToDelete.add(departmentID_delete);
        addToDelete.add(adress_ID_delete);


        SQL.dbDisconnect();


    }

    @Test
    public void Create_User() throws IDatabase.DataException, SQLException {

        Create_Department_owner();

        String At_User_Name = "gfdgfd" + x;
        x++;
        String At_Name = "Peter";
        String At_Password = "esrfbwarqrb32rb34b3b";
        String At_Email = "edfwf2@egage.dk";
        String At_Phone = "645645645";
        String At_Role = "1";
        String At_Department_ID = depsToDelete.get(0);


        SQL.runQuery(String.format("CALL Create_User('%s','%s','%s','%s',%s,%s,%s)", At_User_Name, At_Name, At_Password, At_Email, At_Phone, At_Role, At_Department_ID));
        SQL.commit();


        ResultSet rs = SQL.runQuery(String.format("SELECT * FROM user WHERE User_Name = '%s'", At_User_Name));
        String Name = "";
        String Email = "";
        String Phone = "";
        String Role = "";
        String Password = "";
        while (rs.next()) {
            Name = rs.getString(2);
            Email = rs.getString(3);
            Phone = rs.getString(4);
            Role = rs.getString(5);
            Password = rs.getString(6);
        }
        assertTrue(At_Password.equals(Password));
        assertTrue(At_Name.equals(Name));
        assertTrue(At_Email.equals(Email));
        assertTrue(At_Phone.equals(Phone));
        assertTrue(At_Role.equals(Role));

        rs = SQL.runQuery(String.format("SELECT * FROM employed WHERE User_Name = '%s'", At_User_Name));

        String Department_ID = "";
        while (rs.next()) {
            Department_ID = rs.getString(2);

        }
        assertTrue(At_Department_ID.equals(Department_ID));

        usersToDelete.add(At_User_Name);
    }

    @Test
    public void Get_All_Users_In_Dep() throws IDatabase.DataException, SQLException {

        Create_User();

        String At_Department_ID = depsToDelete.get(0);
        String At_User_Name = usersToDelete.get(0);

        ResultSet rs = SQL.runQuery(String.format("SELECT * FROM user WHERE User_Name = '%s';", At_User_Name));
        String At_Name = "";
        while (rs.next()) {
            At_Name = rs.getString(2);
        }


        rs = SQL.runQuery(String.format("CALL Get_All_Users_In_Dep(%s);", At_Department_ID));

        String Name = "";

        while (rs.next()) {
            Name = rs.getString(1);
        }
        assertTrue(At_Name.equals(Name));

    }

    @Test
    public void Create_Measurement() throws IDatabase.DataException, SQLException {
        String id = depsToDelete.get(0);

        String value = "1.21";
        SQL.runQuery2(String.format("INSERT INTO sensor (Type, Department_ID, Appliance_ID) VALUES (1,%s,'dfsdfs');", id));


        ResultSet rs = SQL.runQuery(String.format("SELECT * FROM sensor WHERE Department_ID = %s;", id));

        id = "";

        while (rs.next()) {
            id = rs.getString(1);
        }

        sensorToDelete.add(id);

        SQL.runQuery(String.format("CALL Create_Measurement(%s,%s);", id, value));

        rs = SQL.runQuery(String.format("SELECT * FROM measurements WHERE Sensor_ID = %s;", id));

        String Value = "";

        while (rs.next()) {
            Value = rs.getString(3);
        }
        assertTrue(value.equals(Value));

        measurementsToDelete.add(id);

    }
}
