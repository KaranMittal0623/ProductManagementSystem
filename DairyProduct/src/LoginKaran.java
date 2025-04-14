import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginKaran {
    LoginKaran(){

    }
    Scanner input = new Scanner(System.in);
    private String userName;
    private String password;
    public void login(){
        System.out.print("Enter the User Name: ");
        userName = input.nextLine();
        System.out.print("Enter the Password: ");
        password = input.nextLine();
        loginCheck();
    }

    public void loginCheck(){
        ConnKaran conn = new ConnKaran();
        String query = "select * from Customer where UserName = '"+userName+"' and Password = '"+password+"'";
        try{
            ResultSet rs = conn.s.executeQuery(query);
            if(rs.next()){
                System.out.println("Login Successful");
            }
            else{
                System.out.println("Login Failed");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
