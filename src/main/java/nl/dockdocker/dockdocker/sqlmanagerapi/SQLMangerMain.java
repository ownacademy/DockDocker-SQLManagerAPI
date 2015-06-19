/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dockdocker.dockdocker.sqlmanagerapi;

import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import static spark.Spark.get;

/**
 *
 * @author ivan
 */
public class SQLMangerMain {
    private static DatabaseConnection database = null;
    
    public static void main(String[] args) {
        
        //  port(5678); <- Uncomment this if you want spark to listen on a port different than 4567
        try {
            database = DatabaseConnection.getInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        get("/select/:query", (request, response) ->  {
            response.status(200);
            return request.params(":query");
        });
        
        get("/insert", (request, response) ->  {
            response.status(200);
            return null;
        });

        get("/update", (request, response) ->  {
            response.status(200);
            return null;
        });
        
        get("/delete", (request, response) ->  {
            response.status(200);
            return null;
        });
        
    }
}
