import java.net.ConnectException;
import java.sql.*;

public class ConnKaran {
    Connection c;
    Statement s;
    ConnKaran(){

//        In try block it is a connection of java with the database MySql
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagementsystem","root","Karan@123786");
            s = c.createStatement();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
