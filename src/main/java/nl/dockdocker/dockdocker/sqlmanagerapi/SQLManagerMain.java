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
import static spark.Spark.post;
import static spark.SparkBase.port;

/**
 *
 * @author ivan
 */
public class SQLManagerMain {
    private static DatabaseConnection database = null;
    
    public static void main(String[] args) {

        port(5000); //<- Uncomment this if you want spark to listen on a port different than 4567
        try {
            database = DatabaseConnection.getInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        post("/select/", (request, response) ->  {
            String query = request.body();
            ResultSet results = database.executeSelectQuery(query);
            if(results != null) {
                ArrayList<serverData> serverData = new ArrayList<>();
                while(results.next()){
                   int id  = results.getInt("id");
                   String user = results.getString("user");
                   String password = results.getString("password");
                   String name = results.getString("server_name");
                   String ip = results.getString("server_ip");
                   String status = results.getString("docker_status");

                   System.out.println(name);
                   serverData.add(new serverData(id, user, password, name, ip, status));
                }
                response.status(200);
                return new Gson().toJson(serverData);
            } else {
                response.status(400);
                return "Select failed";
            }
        });
        
        post("/update/", (request, response) ->  {
                String query = request.body();
                if(database.executeUpdateQuery(query) == true) {
                    response.status(200);
                    return "Successfully updated";
                } else {
                    response.status(400);
                    return "Update failed";
                }
        });
    }
    
}
