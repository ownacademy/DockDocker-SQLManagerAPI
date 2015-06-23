import nl.dockdocker.dockdocker.sqlmanagerapi.SQLManagerMain;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ivan
 */
public class JUnitTests {
    
    @Test
    public void testQuery() {

      // MyClass is tested
      SQLManagerMain SQLmain = new SQLManagerMain();

      // assert statements
//      assertEquals("The result have to be: '/'", "/", SQLmain.clearQueryFromASCII("%7Bslash%7D"));
//      assertEquals("The result have to be: ', '", ", ", SQLmain.clearQueryFromASCII("%7Bcomma%7D"));
//      assertEquals("The result have to be: ' '", " ", SQLmain.clearQueryFromASCII("%20"));
//      assertEquals("The result have to be: '\"'", "\"", SQLmain.clearQueryFromASCII("%22"));
//      assertEquals("The result have to be: ' ' '", "'", SQLmain.clearQueryFromASCII("%27"));
//      assertEquals("The result have to be: '`'", "`", SQLmain.clearQueryFromASCII("%60"));
    }
}
