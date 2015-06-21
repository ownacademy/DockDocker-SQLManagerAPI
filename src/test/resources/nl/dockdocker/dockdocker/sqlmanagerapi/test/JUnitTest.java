/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dockdocker.dockdocker.sqlmanagerapi.test;

import nl.dockdocker.dockdocker.sqlmanagerapi.*;
import static org.junit.Assert.*;
import org.junit.*;


/**
 *
 * @author ivan
 */
public class JUnitTest {
    
    @Test
    public void testQuery() {

      // MyClass is tested
      SQLManagerMain SQLmain = new SQLManagerMain();

      // assert statements
      assertEquals("The result have to be: '/'", "/", SQLmain.clearQueryFromASCII("%7Bslash%7D"));
      assertEquals("The result have to be: ', '", ", ", SQLmain.clearQueryFromASCII("%7Bcomma%7D"));
      assertEquals("The result have to be: ' '", " ", SQLmain.clearQueryFromASCII("%20"));
      assertEquals("The result have to be: '\"'", "\"", SQLmain.clearQueryFromASCII("%22"));
      assertEquals("The result have to be: ' ' '", "'", SQLmain.clearQueryFromASCII("%27"));
      assertEquals("The result have to be: '`'", "`", SQLmain.clearQueryFromASCII("%60"));
      
    }
}
