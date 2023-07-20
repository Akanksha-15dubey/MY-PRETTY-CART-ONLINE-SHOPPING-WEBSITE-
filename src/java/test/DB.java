package test;
import java.sql.Connection;
import java.sql.DriverManager;
public class DB 
{
    
    static Connection createConn()
    {
        Connection con=null;
        try
        {
    
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con =DriverManager.getConnection("jdbc:derby://localhost:1527/Shopping","shop","shop");
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return con;
    }
}