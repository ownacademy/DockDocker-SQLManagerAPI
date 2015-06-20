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
        
        get("/select/:query", (request, response) ->  {
            String query = clearQuery(request.params(":query"));
            ResultSet results = database.executeSelectQuery(query);
            if(results != null) {
                ArrayList<serverData> serverData = new ArrayList<>();
                while(results.next()){
                   int id  = results.getInt("id");
                   String name = results.getString("server_name");
                   String ip = results.getString("server_ip");
                   String status = results.getString("docker_status");

                   System.out.println(name);
                   serverData.add(new serverData(id, name, ip, status));
                }
                response.status(200);
                return new Gson().toJson(serverData);
            } else {
                response.status(400);
                return "Select failed";
            }
        });
        
        
        get("/insert/:query", (request, response) ->  {
                String query = clearQuery(request.params(":query"));
                if(database.executeInsertQuery(query) == true) {
                    response.status(200);
                    return "Successfully inserted";
                } else {
                    response.status(400);
                    return "Insert failed";
                }
        });

        get("/update/:query", (request, response) ->  {
            String query = clearQuery(request.params(":query"));
            if(database.executeUpdateQuery(query)) {
                response.status(200);
                return "Successfully updated";
            } else {
                response.status(400);
                return "Update failed!";
            }
        });
        
        get("/delete/:query", (request, response) ->  {
            String query = clearQuery(request.params(":query"));
            if(database.executeDeleteQuery(query)) {
                response.status(200);
                return "Successfully deleted";
            } else {
                response.status(400);
                return "Delete failed!" + query;
            }
        });
        
    }
    
    private static String clearQuery(String query) {
        return query.replace("{", "").
                replace("%20", " ").
                replace("/", "{slash}").
                replace("}", "").
                replace("%27", "'").
                replace("%60", "`").
                replace("%22", "\"");
    }
}
