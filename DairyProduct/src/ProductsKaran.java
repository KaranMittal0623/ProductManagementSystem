import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;

public class ProductsKaran {
    private String filter;
    private List<Product> products; // Changed class name to follow conventions
    private ConnKaran conn = new ConnKaran();
    private Scanner input = new Scanner(System.in);

    // Changed class name to follow Java naming conventions (PascalCase)
    public class Product {
        int productId;
        String productName;
        double productPrice;
        int productRating;
        int productSales;

        Product(int productId, String productName, double productPrice, int productRating) {
            this.productId = productId;
            this.productName = productName;
            this.productPrice = productPrice; // Fixed order to match parameters
            this.productRating = productRating;
            this.productSales = 0;
        }
    }

    public ProductsKaran() {
        System.out.println("Press 1 to filter by price");
        System.out.println("Press 2 to filter by best");
        System.out.println("Press 3 to filter by rating");
        System.out.println("Press 4 to filter by sale");
        int choice = input.nextInt();

        // Added breaks to switch case
        switch (choice) {
            case 1:
                filterProducts("price");
                break;
            case 2:
                filterProducts("best");
                break;
            case 3:
                filterProducts("rating");
                break;
            case 4:
                filterProducts("sales");
                break;
            default:
                System.out.println("Invalid choice");
                filterProducts("best"); // Default filter
        }
    }

    public void filterProducts(String filter) {
        this.filter = filter;
        this.products = new ArrayList<>();
        loadProductFromDB();
//        loadSalesData();
        List<Product> filtered = filterProducts();
        displayProducts(filtered); // Added method to display results
    }

    private void loadProductFromDB() {
        String query = "SELECT * FROM Products";
        try {
            ResultSet data = conn.s.executeQuery(query);
            while (data.next()) {
                Product newProduct = new Product(
                        data.getInt("ProductId"),
                        data.getString("ProductName"),
                        data.getDouble("ProductPrice"),
                        data.getInt("ProductRating")
                );
                products.add(newProduct);
            }
        } catch (Exception e) {
            System.err.println("Database load error: " + e.getMessage());
        }
    }

//    private void loadSalesData() {
//                    String query = "SELECT ProductId, SalesCount FROM ProductSales";
//                    try {
//                        ResultSet data = conn.s.executeQuery(query);
//                        while (data.next()) {
//                            int productId = data.getInt("ProductId");
//                            int salesCount = data.getInt("SalesCount");
//
//                for (Product p : products) {
//                    if (p.productId == productId) {
//                        p.productSales = salesCount;
//                        break;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Sales data load error: " + e.getMessage());
//        }
//    }

    public List<Product> filterProducts() {
        switch (filter.toLowerCase()) {
            case "price":
                return sortByPrice();
            case "best":
                return sortByBest();
            case "rating":
                return sortByRating();
            case "sales":
                return sortBySales();
            default:
                return new ArrayList<>(products);
        }
    }

    private List<Product> sortByPrice() {
        List<Product> sorted = new ArrayList<>(products);
        mergeSort(sorted, Comparator.comparingDouble(p -> p.productPrice));
        return sorted;
    }

    private List<Product> sortByRating() {
        List<Product> sorted = new ArrayList<>(products);
        mergeSort(sorted, Comparator.comparingInt((Product p) -> p.productRating).reversed());
        return sorted;
    }

    private List<Product> sortBySales() {
        List<Product> sorted = new ArrayList<>(products);
        quickSort(sorted, 0, sorted.size() - 1, (p1, p2) -> Integer.compare(p2.productSales, p1.productSales));
        return sorted;
    }

    private List<Product> sortByBest() {
        List<Product> sorted = new ArrayList<>(products);
        quickSort(sorted, 0, sorted.size() - 1, (p1, p2) -> {
            double score1 = (p1.productRating * 0.7) + (1 / p1.productPrice * 0.3);
            double score2 = (p2.productRating * 0.7) + (1 / p2.productPrice * 0.3);
            return Double.compare(score2, score1);
        });
        return sorted;
    }

    private void mergeSort(List<Product> list, Comparator<Product> comp) {
        if (list.size() <= 1) return;

        int mid = list.size() / 2;
        List<Product> left = new ArrayList<>(list.subList(0, mid));
        List<Product> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left, comp);
        mergeSort(right, comp);

        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (comp.compare(left.get(i), right.get(j)) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }

    private void quickSort(List<Product> list, int low, int high, Comparator<Product> comp) {
        if (low < high) {
            int pi = partition(list, low, high, comp);
            quickSort(list, low, pi - 1, comp);
            quickSort(list, pi + 1, high, comp);
        }
    }

    private int partition(List<Product> list, int low, int high, Comparator<Product> comp) {
        Product pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comp.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<Product> list, int i, int j) {
        Product temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // Added method to display filtered products
    private void displayProducts(List<Product> products) {
        System.out.println("\nFiltered Products (" + filter + "):");
        products.forEach(p -> System.out.printf(
                "ID: %d, Name: %-20s, Price: %8.2f, Rating: %2d, Sales: %4d\n",
                p.productId, p.productName, p.productPrice, p.productRating, p.productSales
        ));
    }

    // Main method for testing
    public static void main(String[] args) {
        new ProductsKaran();
    }
}