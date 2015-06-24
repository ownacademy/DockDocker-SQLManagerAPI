/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import nl.dockdocker.dockdocker.sqlmanagerapi.DatabaseConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ivan
 */
public class JUnitTestQueries {
    
    public JUnitTestQueries() {
    }

    private static DatabaseConnection database = null;
    
    @Test
    public void testSelectQuery() {
        
        try {
            database = DatabaseConnection.getInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        String selectQuery = "SELECT user FROM list_test WHERE id = 53";

        assertTrue(database.executeSelectQuery(selectQuery) instanceof ResultSet);
    }
    
    @Test
    public void testInsertQuery() {
        
        try {
            database = DatabaseConnection.getInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String intsertQuery = "INSERT "
                + "INTO `db_servers`.`list_test` (`id`, `user`, `password`, `server_name`, `server_ip`, `docker_status`) "
                + "VALUES (NULL, 'junit_user', 'junit_pass', 'junit_sername', 'junit_ip', 'doc_status')";

        assertTrue("The result have to be: true", database.executeUpdateQuery(intsertQuery));
    }
}
