import java.util.ArrayList;
import java.util.Scanner;

public class AddMilkmenKaran {

//    All variables declaration
    public static int Id=0;
    private int MilkmenId;
    private String Name;
    private String PhoneNumber;
    private String Area;
    private ArrayList<String> DeliveryArea;
    private double rating;
    private ArrayList<String> MilkTypes;
    private int experience;
    private ArrayList<String> CustomerDetails;
    private ArrayList<String> OrderDetails;


//    Adding a new MilkMen
    AddMilkmenKaran(){

    }
    AddMilkmenKaran(String Name, String PhoneNumber, String Area){
        this.MilkmenId = Id++;
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.Area = Area;
        this.DeliveryArea = new ArrayList<>();
        this.rating = 0.0;
        this.MilkTypes = new ArrayList<>();
        this.experience = 0;
        this.CustomerDetails = new ArrayList<>();
        this.OrderDetails = new ArrayList<>();
    }


//    Function to take input from the user of details
    AddMilkmenKaran newMilkmen;
    public AddMilkmenKaran userInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("-------Adding new Milkmen--------");
        System.out.print("Enter the Milkmen name: ");
        String Name = input.nextLine();
        System.out.print("Enter the PhoneNumber: ");
        String PhoneNumber = input.nextLine();
        System.out.print("Enter the Area: ");
        String Area = input.nextLine();

        newMilkmen = new AddMilkmenKaran(Name,PhoneNumber,Area);
        addToDatabase();
        return newMilkmen;

    }


//    Getters for all Variables
    public String getName(){
        System.out.print(this.Name);
        return this.Name;
    }
    public String getPhoneNumber(){
        return this.PhoneNumber;
    }
    public String getArea(){
        return this.Area;
    }

//    Setters for some variable
    public void addDeliveryArea(String Area){
        if(!this.DeliveryArea.contains(Area)){
            DeliveryArea.add(Area);
        }
    }



//    Add data to database
    public void addToDatabase() {
        try {
            ConnKaran conn = new ConnKaran();
            String query = "insert into Milkmen value('"+newMilkmen.MilkmenId+"', '"+newMilkmen.Name+"', '"+newMilkmen.PhoneNumber+"','"+newMilkmen.Area+"')";
            conn.s.executeUpdate(query);
            System.out.println("Data added successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

}
