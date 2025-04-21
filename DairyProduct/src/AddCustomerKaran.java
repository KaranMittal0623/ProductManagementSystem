import java.util.ArrayList;
import java.util.Scanner;

public class AddCustomerKaran {

//    All private variables
    private String name;
    private String userName;
    private String number;
    private String address;
    private String email;
    private String password;
    private float balance;
    private int total_orders;
    private String milkMen;
    ArrayList<String> orders;

    public AddCustomerKaran() {
    }
    public AddCustomerKaran(String name, String userName, String number, String address, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.number = number;
        this.address = address;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.total_orders = 0;
        this.milkMen = "NULL";
        this.orders = new ArrayList<>();

    }

//    Function to inpout details of customer and add to database
    Scanner input = new Scanner(System.in);
    AddCustomerKaran newCustomer;
    public AddCustomerKaran addCustomer() {
        System.out.println("Adding Customer");
        System.out.print("Enter Customer Name");
        String name = input.nextLine();
        System.out.print("Enter Customer userName");
        String userName = input.nextLine();
        System.out.print("Enter Customer Number");
        String number = input.nextLine();
        System.out.print("Enter Customer Address");
        String address = input.nextLine();
        System.out.print("Enter Customer Email");
        String email = input.nextLine();
        System.out.print("Enter Customer Password");
        String password = input.nextLine();
        newCustomer = new AddCustomerKaran(name, userName, number, address, email, password);
        addToDatabase();
        return newCustomer;
    }

//    Adding the data to database
    public void addToDatabase() {
        try {
            ConnKaran conn = new ConnKaran();
            String query = "insert into Customer value('"+newCustomer.name+"', '"+newCustomer.userName+"', '"+newCustomer.number+"', '"+newCustomer.address+"', '"+newCustomer.email+"', '"+newCustomer.password+"', '"+newCustomer.balance+"', '"+newCustomer.total_orders+"', '"+newCustomer.milkMen+"')";
            conn.s.executeUpdate(query);
            System.out.println("Data added successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
