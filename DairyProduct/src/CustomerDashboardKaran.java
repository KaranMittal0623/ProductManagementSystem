import java.security.spec.ECField;
import java.sql.ResultSet;
import java.util.Scanner;

public class CustomerDashboardKaran {
    boolean run;
    public CustomerDashboardKaran(String userName) {
        run = true;
        while(run) {
            displayMenu();
            userChoice(userName);
        }
    }

    private void displayMenu() {
        System.out.println("\n========== Customer Dashboard ==========");
        System.out.println("Press 1 to see your profile");
        System.out.println("Press 2 to edit your profile");
        System.out.println("Press 3 to check monthly details");
        System.out.println("Press 4 to see all orders");
        System.out.println("Press 5 to order products");
        System.out.println("Press 6 to get new Milkmen");
//        System.out.println("Press 7 to change milkmen");
        System.out.println("Press 8 to filter the Products");
        System.out.println("Press 9 to Logout");
        System.out.println("Press 0 to Exit!!!!!!");
        System.out.print("Enter your choice: ");
    }

    public void userChoice(String userName) {
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                seeProfile(userName);
                break;
            case 2:
                editProfile(userName);
                break;
            case 3:
                checkMonthlyDetails(userName);
                break;
            case 4:
                seeAllOrders(userName);
                break;
            case 5:
                orderProducts(userName);
                break;
            case 6:
                getNewMilkmen(userName);
                break;
            case 7:
                changeMilkmen(userName);
                break;
            case 8:
//                deleteAccount(userName);
                  new ProductsKaran();
                break;
            case 9:
                logout();
                break;
            case 0:
                run = false;

                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    public void seeProfile(String userName) {
        ConnKaran conn = new ConnKaran();
        String query = "SELECT * FROM Customer WHERE UserName = '" + userName + "'";
        try {
            ResultSet userDetails = conn.s.executeQuery(query);
            if (userDetails.next()) {
                System.out.println("\n========== Your Profile ==========");
                System.out.println("Name : " + userDetails.getString("Name"));
                System.out.println("UserName : " + userDetails.getString("UserName"));
                System.out.println("Number : " + userDetails.getString("Number"));
                System.out.println("Address : " + userDetails.getString("Address"));
                System.out.println("E-Mail : " + userDetails.getString("Email"));
                System.out.println("Password : " + userDetails.getString("Password"));
                System.out.println("Balance : " + userDetails.getFloat("Balance"));
                System.out.println("TotalOrders : " + userDetails.getInt("Orders"));
                System.out.println("Milkmen : " + userDetails.getString("MilkMen"));
            } else {
                System.out.println("User not found!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
//        new CustomerDashboardKaran(userName); // Return to menu
    }

    public void editProfile(String userName) {
        ConnKaran conn = new ConnKaran();
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("\n========== Edit Profile ==========");
            System.out.print("Enter new Name (leave blank to keep current): ");
            String name = input.nextLine();
            System.out.print("Enter new Number (leave blank to keep current): ");
            String number = input.nextLine();
            System.out.print("Enter new Address (leave blank to keep current): ");
            String address = input.nextLine();
            System.out.print("Enter new Email (leave blank to keep current): ");
            String email = input.nextLine();
            System.out.print("Enter new Password (leave blank to keep current): ");
            String password = input.nextLine();

            // Build the update query dynamically based on what fields were provided
            StringBuilder queryBuilder = new StringBuilder("UPDATE Customer SET ");
            boolean needsComma = false;

            if (!name.isEmpty()) {
                queryBuilder.append("Name = '").append(name).append("'");
                needsComma = true;
            }
            if (!number.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("Number = '").append(number).append("'");
                needsComma = true;
            }
            if (!address.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("Address = '").append(address).append("'");
                needsComma = true;
            }
            if (!email.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("Email = '").append(email).append("'");
                needsComma = true;
            }
            if (!password.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("Password = '").append(password).append("'");
            }

            queryBuilder.append(" WHERE UserName = '").append(userName).append("'");

            if (needsComma || !password.isEmpty()) {
                int rowsAffected = conn.s.executeUpdate(queryBuilder.toString());
                if (rowsAffected > 0) {
                    System.out.println("Profile updated successfully!");
                } else {
                    System.out.println("Failed to update profile.");
                }
            } else {
                System.out.println("No changes made.");
            }
        } catch (Exception e) {
            System.out.println("Error updating profile: " + e);
        }
        new CustomerDashboardKaran(userName); // Return to menu
    }

    public void checkMonthlyDetails(String userName) {
        ConnKaran conn = new ConnKaran();
        // Implementation for monthly details
        System.out.println("\n========== Monthly Details ==========");
        // Add your database query and logic here
        String query = "select * from Customer where UserName = '"+userName+"'";
        try{
            ResultSet user = conn.s.executeQuery(query);
            if(user.next()){
                System.out.println("Balance: "+user.getInt("Balance"));
                System.out.println("Total Orders: "+user.getInt("Orders"));
                System.out.println("Milkmen : "+user.getString("MilkMen"));
            }
            else{
                System.out.println("User Not found");
            }
        }
        catch(Exception e){
            System.out.println("Error"+e);
        }
        System.out.println("Monthly details functionality to be implemented");
        new CustomerDashboardKaran(userName); // Return to menu
    }

    public void seeAllOrders(String userName) {
        // Implementation to view all orders
        ConnKaran conn = new ConnKaran();
        System.out.println("\n========== Your Orders ==========");
        // Add your database query and logic here
        String query = "select * from Orders as o join Customer as c on o.UserName = c.UserName where o.UserName = '"+userName+"'";
        try {
            ResultSet order = conn.s.executeQuery(query);
            while(order.next()){
                System.out.println("Order No: "+order.getInt("OrderId"));
                System.out.println("Order No: "+order.getString("OrderName"));
                System.out.println("Order No: "+order.getFloat("OrderPrice"));
            }

        }
        catch(Exception e) {
            System.out.println("Error while fetching the order details"+e);
        }
//        new CustomerDashboardKaran(userName); // Return to menu
    }

    public void orderProducts(String userName) {
        // Implementation for ordering products
        ConnKaran conn = new ConnKaran();
        int[] orders = {1,2,3,4};
        String[] name = {"Cow Milk","Buffalo Milk","Butter","Curd"};
        float[] price = {55,70,200,80};
        Scanner input = new Scanner(System.in);
        System.out.println("\n========== Order Products ==========");
        System.out.println("1. Cow Milk Rs. 55 Per Kg");
        System.out.println("2. Buffalo Milk Rs. 70 Per Kg");
        System.out.println("3. Butter Rs. 200 Per Kg");
        System.out.println("4. Curd Rs. 80 Per Kg");
        System.out.println("Press order no. to add to cart: ");
        int orderNum = input.nextInt();
        if(orderNum<5 && orderNum>0){
            int orderID=orders[orderNum];
            String orderName = name[orderNum];
            float orderPrice = price[orderNum];
            String query = "insert into Orders values('"+userName+"', '"+orderID+"', '"+orderName+"','"+orderPrice+"')";
            try {
                conn.s.executeUpdate(query);
                System.out.println("Product Added");
            }
            catch(Exception e){
                System.out.println("Error while adding to database"+e);
            }
        }
        new CustomerDashboardKaran(userName); // Return to menu
    }

    public void getNewMilkmen(String userName) {
        // Implementation to find new milkmen
        ConnKaran conn = new ConnKaran();
        Scanner input = new Scanner(System.in);
        System.out.println("\n========== Available Milkmen ==========");
        String query = "select * from Milkmen";
        try{
            ResultSet milkmen = conn.s.executeQuery(query);
            while(milkmen.next()){
                System.out.println("Name: "+milkmen.getString("Name"));
                System.out.println("Phone Number"+milkmen.getString("PhoneNumber"));
                System.out.println("Area"+milkmen.getString("Area"));
                System.out.println("------------------------------------------------------\n");
            }
            System.out.println("Press the serial number of the Milkmen u want to assign: ");
            String milkmenChoice = input.nextLine().trim();
            String query1 = "select UserName from Milkmen where UserName = '"+milkmenChoice+"'";
            ResultSet milkmenAssign = conn.s.executeQuery(query1);
            if(milkmenAssign.next()){
                String updateQuery  = "update Customer set MilkMen = '"+milkmenAssign.getString("UserName")+"' where UserName = '"+userName+"'";
                conn.s.executeUpdate(updateQuery);
                System.out.println("Milk Man Assigned");
            }

        }
        catch(Exception e){
            System.out.println("Error"+e);
        }
    }

    public void changeMilkmen(String userName) {
        // Implementation to change milkmen
        System.out.println("\n========== Change Milkman ==========");
        // Add your database query and logic here
        System.out.println("Change milkman functionality to be implemented");
        new CustomerDashboardKaran(userName); // Return to menu
    }

    public void deleteAccount(String userName) {
        System.out.println("\n========== Delete Account ==========");
        Scanner input = new Scanner(System.in);
        System.out.print("Are you sure you want to delete your account? (yes/no): ");
        String confirmation = input.nextLine().toLowerCase();

        if (confirmation.equals("yes")) {
            ConnKaran conn = new ConnKaran();
            try {
                String query = "DELETE FROM Customer WHERE UserName = '" + userName + "'";
                int rowsAffected = conn.s.executeUpdate(query);
                if (rowsAffected > 0) {
                    System.out.println("Account deleted successfully!");
                    logout();
                } else {
                    System.out.println("Failed to delete account.");
                    new CustomerDashboardKaran(userName);
                }
            } catch (Exception e) {
                System.out.println("Error deleting account: " + e);
                new CustomerDashboardKaran(userName);
            }
        } else {
            System.out.println("Account deletion cancelled.");
            new CustomerDashboardKaran(userName);
        }
    }

    public void logout() {
        System.out.println("\nLogging out... Thank you for using our service!");
        // You might want to redirect to the login page here
        System.exit(0); // For now, just exit the application
    }
}