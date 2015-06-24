/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dockdocker.dockdocker.sqlmanagerapi;

import java.sql.*;

/**
 *
 * @author Ivan Ivanov
 */
public class DatabaseConnection {
    private static DatabaseConnection INSTANCE = null;
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost/db_servers";

    //  Database credentials
    private static final String USER = "root";
    private  static final String PASS = "64Bxx8";
    //TO DO: Encrypt password.

    private Connection Connection = null;
    private Statement Statement = null;
    
    private DatabaseConnection() throws Exception{

        Class.forName("com.mysql.jdbc.Driver");
            
        System.out.println("Connecting to a selected database...");
        Connection = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");

        System.out.println("Creating statement...");
        Statement = Connection.createStatement();
    }
    
    /**
     * Gets the current instance of the database connection.
     * @return DatabaseConnection object.
     */
    public static DatabaseConnection getInstance() throws Exception {
        if(INSTANCE == null) {
            INSTANCE = new DatabaseConnection();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
    
    /**
     * Executes select SQL query.
     * @param query SQL query that well be executed.
     * @return ResultSet object.
     */
    public ResultSet executeSelectQuery(String query){
        try {
            return Statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Executes update SQL query.
     * @param query SQL query that well be executed.
     * @return true if the operation is successful.
     */
    public boolean executeUpdateQuery(String query){
        try {
            Statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    } 
}
