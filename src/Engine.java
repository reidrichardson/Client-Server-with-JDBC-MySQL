/*
    Name: Reid Richardson
    Course: CNT 4714 Fall 2017
    Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
    Date: October 15, 2017
 */

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Engine {
    private String url;
    private String user;
    private String password;
    private Connection connector;
    /*
        constructor
        sets the url, username and password
        params ur, us, pw
     */
    public Engine(String ur, String us, String pw) {
        this.url = ur;
        this.user = us;
        this.password = pw;
    }

    /*
        opens the connection with SQL database
     */
    public void initConnection() throws SQLException {
        connector = DriverManager.getConnection(this.url, this.user, this.password);

    }

    /*
        closes the connection with the SQL database
     */
    public void endConnection() throws SQLException {
        connector.close();
    }

    /*
        returns the connection
     */
    public Connection getConnector() {
        return this.connector;
    }


}
