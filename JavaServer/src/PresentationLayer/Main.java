package PresentationLayer;

import DataLayer.IDatabase;
import LogicLayer.ApplicationController;
import DataLayer.MysqlDatabase;

/**
 * PresentationLayer.Main does....
 * <p>
 * Bugs: none known
 *
 * @author Casper Bodskov
 * @version v.0.1
 */
public class Main {
    public static ApplicationController applicationController;

    public static void main(String[] args) {
        IDatabase SQL = new MysqlDatabase("dyrsted.duckdns.org", "cdio_d2", "api", "", 3306);


        applicationController = new ApplicationController(SQL);
        applicationController.start();

    }
}
