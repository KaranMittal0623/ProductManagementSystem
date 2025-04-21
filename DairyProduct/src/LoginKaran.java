import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Stack;

public class LoginKaran {
    LoginKaran(){
    }
//    Stack to tracklogged user
    public Stack<String> loggedInUsers= new Stack<>();


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
        String query2 = "select * from Milkmen where UserName = '"+userName+"' and Password = '"+password+"'";
        try{
           ResultSet customer = conn.s.executeQuery(query);
           if(customer.next()){
               System.out.println("Welcome to Customers Page");
               loggedInUsers.push(userName);
               CustomerDashboardKaran customerProfile = new CustomerDashboardKaran(loggedInUsers.peek());
           }
           else{
               ResultSet milkmen = conn.s.executeQuery(query2);
               if(milkmen.next()){
                   System.out.println("Welcome to Milkmen Page");
                   loggedInUsers.push(userName);
               }
               else{
                   System.out.println("Login Failed");
               }
           }
        } catch (Exception e) {
            throw new RuntimeException("Error while checking the user"+e);
        }
    }
}
