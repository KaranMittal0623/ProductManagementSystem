import java.sql.*;
import java.util.Scanner;

public class MilkmanDashboardKaran {
    private String username;
    private ConnKaran conn = new ConnKaran();
    private Scanner input = new Scanner(System.in);

    public MilkmanDashboardKaran(String username) {
        this.username = username;
        displayMenu();
    }

    private void displayMenu() {
        while (true) {
            System.out.println("\nMilkman Dashboard");
            System.out.println("1. See your Profile");
            System.out.println("2. Edit your profile");
            System.out.println("3. See your customer list");
            System.out.println("4. Check monthly details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    viewProfile();
                    break;
                case 2:
                    editProfile();
                    break;
                case 3:
                    viewCustomerList();
                    break;
                case 4:
                    viewMonthlyDetails();
                    break;
                case 5:
                    System.out.println("Exiting...");
//
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void viewProfile() {
        String query = "SELECT Name, UserName, PhoneNumber, Area FROM Milkmen WHERE UserName = '" + username + "'";
        try {
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                System.out.println("\nYour Profile Details:");
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Username: " + rs.getString("UserName"));
                System.out.println("Phone Number: " + rs.getString("PhoneNumber"));
                System.out.println("Area: " + rs.getString("Area"));
            } else {
                System.out.println("Profile not found!");
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error viewing profile: " + e.getMessage());
        }
    }

    private void editProfile() {
        viewProfile();
        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Area");
        System.out.println("4. Password");
        System.out.print("Enter your choice: ");

        int choice = input.nextInt();
        input.nextLine();

        String columnToUpdate;
        String newValue;
        String confirmation;

        switch (choice) {
            case 1:
                columnToUpdate = "Name";
                System.out.print("Enter new name: ");
                newValue = input.nextLine();
                break;
            case 2:
                columnToUpdate = "PhoneNumber";
                System.out.print("Enter new phone number: ");
                newValue = input.nextLine();
                break;
            case 3:
                columnToUpdate = "Area";
                System.out.print("Enter new area: ");
                newValue = input.nextLine();
                break;
            case 4:
                columnToUpdate = "Password";
                System.out.print("Enter new password: ");
                newValue = input.nextLine();
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        System.out.print("Are you sure you want to update? (yes/no): ");
        confirmation = input.nextLine().toLowerCase();

        if (confirmation.equals("yes")) {
            String updateQuery = "UPDATE Milkmen SET " + columnToUpdate + " = '" + newValue +
                    "' WHERE UserName = '" + username + "'";
            try {
                int rowsAffected = conn.s.executeUpdate(updateQuery);

                if (rowsAffected > 0) {
                    System.out.println("Profile updated successfully!");
                } else {
                    System.out.println("Failed to update profile.");
                }
            } catch (SQLException e) {
                System.err.println("Error updating profile: " + e.getMessage());
            }
        } else {
            System.out.println("Update cancelled.");
        }
    }

    private void viewCustomerList() {
        String query = "SELECT CustomerName, Address, PhoneNumber, DeliveryTime FROM Customers " +
                "WHERE MilkmanUsername = '" + username + "'";
        try {
            ResultSet rs = conn.s.executeQuery(query);

            System.out.println("\nYour Customer List:");
            System.out.printf("%-20s %-30s %-15s %-10s\n", "Name", "Address", "Phone", "Delivery Time");
            System.out.println("------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-20s %-30s %-15s %-10s\n",
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("DeliveryTime"));
            }

            if (!rs.isBeforeFirst() && rs.getRow() == 0) {
                System.out.println("No customers found.");
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error viewing customer list: " + e.getMessage());
        }
    }

    private void viewMonthlyDetails() {
        System.out.print("Enter month (1-12): ");
        int month = input.nextInt();
        System.out.print("Enter year: ");
        int year = input.nextInt();
        input.nextLine();

        String query = "SELECT SUM(Quantity) AS TotalQuantity, SUM(Amount) AS TotalAmount " +
                "FROM Orders WHERE MilkmanUsername = '" + username + "' " +
                "AND MONTH(OrderDate) = " + month + " AND YEAR(OrderDate) = " + year;

        try {
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                System.out.println("\nMonthly Details for " + month + "/" + year);
                System.out.println("Total Quantity Delivered: " + rs.getInt("TotalQuantity"));
                System.out.println("Total Amount Earned: " + rs.getDouble("TotalAmount"));
            } else {
                System.out.println("No orders found for the selected month/year.");
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error viewing monthly details: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        new MilkmanDashboardKaran("milkman123");
    }
}