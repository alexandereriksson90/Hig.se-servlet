package Servlet;

import com.owlike.genson.Genson;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import static org.mockito.Mockito.*;

/**
 *
 * @author thomas
 */
public class SimpleServletTest {
    SimpleServlet instance;
    HttpServletRequest request;
    HttpServletResponse response;
    StringWriter resultingWriter = new StringWriter();
    

    
    @Before
    public void setupTest() {
        instance = new SimpleServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        try {
            when(response.getWriter()).thenReturn(new PrintWriter(resultingWriter));
        } catch (IOException ex) {
            fail("Unexpected Exception thrown");
        }
    }

    @Test
    public void testCommonCase() {
        String expResult = "FootballSineWave".toString();
        when(request.getParameter("datasource1")).thenReturn("Football");
        when(request.getParameter("datasource2")).thenReturn("SineWave");
        try {
            instance.processRequest(request, response);
        } catch (IOException ex) {
            fail("Unexpected Exception thrown");
        }
        String stringResult = resultingWriter.toString();
        String result= stringResult.substring(0, 16);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testMissingParameter() {
        when(request.getParameter("datasource1")).thenReturn("Football");
        try {
            instance.doGet(request, response);
        } catch (ServletException | IOException ex) {
            fail("Unexpected Exception thrown");
        } 
        
    }
}
