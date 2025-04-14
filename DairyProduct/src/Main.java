import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Proper Designing of the project console
        Scanner input = new Scanner(System.in);
//        User choice for login or register
        System.out.println("\n**********************Welcome to MilkConnect**********************\n");
        System.out.println("Press 1. to Login" + "\nPress 2. to Register");
        int userChoice = input.nextInt();
        if(userChoice == 1){
            System.out.println("**********************Welcome to Login Page**********************");
            new LoginKaran().login();
        }
        else if(userChoice == 2){
            System.out.println("**********************Welcome to Register Page**********************");
            System.out.println("Press 1. to register as a New Customer" + "\nPress 2. to register as a New Milkmen");
            int userChoice2 = input.nextInt();
//            Customer or Milkmen
            if(userChoice2 == 1){
                System.out.println("\nWelcome to New Customer Registration Page");
                AddCustomerKaran cc = new AddCustomerKaran().addCustomer();
            }
            else if(userChoice2 == 2){
//              If Milkmen than call functions from the class AddMilkmenKaran
                System.out.println("\nWelcome to New Milkmen Registration Page\n");
                AddMilkmenKaran kk = new AddMilkmenKaran().userInput();
            }
        }
    }
}