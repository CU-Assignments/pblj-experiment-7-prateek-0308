import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String DB_URL = "jdbc:sqlite:product.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Class.forName("org.sqlite.JDBC");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Product (" +
                    "ProductID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ProductName TEXT," +
                    "Price REAL," +
                    "Quantity INTEGER)");

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n1. Create Product\n2. Read Products\n3. Update Product\n4. Delete Product\n5. Exit");
                System.out.print("Choose: ");
                int ch = sc.nextInt();
                sc.nextLine();
                if (ch == 1) {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Qty: ");
                    int qty = sc.nextInt();
                    conn.setAutoCommit(false);
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)")) {
                        ps.setString(1, name);
                        ps.setDouble(2, price);
                        ps.setInt(3, qty);
                        ps.executeUpdate();
                        conn.commit();
                        System.out.println("Inserted.");
                    } catch (Exception e) {
                        conn.rollback();
                        System.out.println("Error: " + e.getMessage());
                    }
                    conn.setAutoCommit(true);
                } else if (ch == 2) {
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("ProductID") + ", Name: " + rs.getString("ProductName") + ", Price: " + rs.getDouble("Price") + ", Qty: " + rs.getInt("Quantity"));
                    }
                } else if (ch == 3) {
                    System.out.print("ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: ");
                    String name = sc.nextLine();
                    System.out.print("New Price: ");
                    double price = sc.nextDouble();
                    System.out.print("New Qty: ");
                    int qty = sc.nextInt();
                    conn.setAutoCommit(false);
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?")) {
                        ps.setString(1, name);
                        ps.setDouble(2, price);
                        ps.setInt(3, qty);
                        ps.setInt(4, id);
                        ps.executeUpdate();
                        conn.commit();
                        System.out.println("Updated.");
                    } catch (Exception e) {
                        conn.rollback();
                        System.out.println("Error: " + e.getMessage());
                    }
                    conn.setAutoCommit(true);
                } else if (ch == 4) {
                    System.out.print("ID to delete: ");
                    int id = sc.nextInt();
                    conn.setAutoCommit(false);
                    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Product WHERE ProductID=?")) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                        conn.commit();
                        System.out.println("Deleted.");
                    } catch (Exception e) {
                        conn.rollback();
                        System.out.println("Error: " + e.getMessage());
                    }
                    conn.setAutoCommit(true);
                } else if (ch == 5) break;
            }
        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }
}