import java.sql.ResultSet;
import java.util.Scanner;

public class CustomerDashboardKaran {

    CustomerDashboardKaran(String userName){
        System.out.println("Press 1 to see your profile");
        System.out.println("Press 2 to edit your profile");
        System.out.println("Press 3 to check monthly details");
        System.out.println("Press 4 to see all orders");
        System.out.println("Press 5 to order products");
        System.out.println("Press 6 to get new Milkmen");
        System.out.println("Press 7 to change milkmen");
        System.out.println("Press 8 to delete your account");
        System.out.println("Press 9 to Logout");
//        System.out.println(userName);
        userChoice(userName);

    }

//    User choices
    public void userChoice(String userName){
        Scanner input =new Scanner(System.in);
        int in = input.nextInt();
        if(in==1){
//            System.out.println(userName);
            seeProfile(userName);
        }
        else{
            System.out.println("Not found");
        }
    }

//    Functions
    public void seeProfile(String userName){
        ConnKaran conn = new ConnKaran();
        String query = "select * from Customer where UserName = '"+userName+"'";
        try {
            ResultSet userDetails = conn.s.executeQuery(query);
            if(userDetails.next()) {
                System.out.println("Name : "+userDetails.getString("Name"));
                System.out.println("UserName : "+userDetails.getString("UserName"));
                System.out.println("Number : "+userDetails.getString("Number"));
                System.out.println("Address : "+userDetails.getString("Address"));
                System.out.println("E-Mail : "+userDetails.getString("Email"));
                System.out.println("Password : "+userDetails.getString("Password"));
                System.out.println("Balance : "+userDetails.getFloat("Balance"));
                System.out.println("TotalOrders : "+userDetails.getInt("Orders"));
                System.out.println("Milkmen : "+userDetails.getString("MilkMen"));

            }
        }
        catch (Exception e){
            System.out.println("Error"+e);
        }
    }

}
