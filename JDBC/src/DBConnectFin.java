import java.sql.*;
import java.util.Scanner;

public class DBConnectFin {

    public static void main(String[] args) {
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            System.out.println("Driver Loaded");

            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "SYSTEM";
            String pwd = "pritam";

            Connection con = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connection is ready");

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Insert");
                System.out.println("2. Display");
                System.out.println("3. Delete");
                System.out.println("4. Deposit");
                System.out.println("5. Withdraw");
                System.out.println("6. Quit");
                System.out.println("Enter your choice: ");
                int ch = sc.nextInt();

                if (ch == 1) {
                    insertAccount(con, sc);
                } else if (ch == 2) {
                    displayAccount(con, sc);
                } else if (ch == 3) {
                    deleteAccount(con, sc);
                } else if (ch == 4) {
                    deposit(con, sc);
                } else if (ch == 5) {
                    withdraw(con, sc);
                } else if (ch == 6) {
                    System.out.println("Exiting...");
                    con.close();
                    break;
                } else {
                    System.out.println("Invalid choice! Please enter a valid option.");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Exception: Oracle JDBC driver not found");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static void insertAccount(Connection con, Scanner sc) throws Exception {
        System.out.println("Enter account details");
        int num = sc.nextInt();
        String name = sc.next();
        int balance = sc.nextInt();

        String query = "INSERT INTO account VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, num);
        ps.setString(2, name);
        ps.setInt(3, balance);
        ps.executeUpdate();
        System.out.println("Record Inserted");
    }

    private static void deleteAccount(Connection con, Scanner sc) throws Exception {
        System.out.println("Enter account number to delete the account:");
        int num = sc.nextInt();

        String query = "DELETE FROM account WHERE num=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, num);
        int count = ps.executeUpdate();
        if (count > 0)
            System.out.println("Record deleted");
        else
            System.out.println("Error: Invalid account number");
    }

    private static void displayAccount(Connection con, Scanner sc) throws Exception {
        System.out.println("Enter account number:");
        int num = sc.nextInt();

        String query = "SELECT * FROM account WHERE num=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, num);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Record found");
            System.out.println("Details: " + rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getInt(3));
        } else {
            System.out.println("Error: Record not found");
        }
    }

    private static void deposit(Connection con, Scanner sc) throws Exception {
        System.out.println("Enter account number:");
        int num = sc.nextInt();
        System.out.println("Enter amount to deposit:");
        int amt = sc.nextInt();

        String query = "UPDATE account SET balance=balance+? WHERE num=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, amt);
        ps.setInt(2, num);
        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Deposit successful");
        } else {
            System.out.println("Error: Account not found");
        }
    }

    private static void withdraw(Connection con, Scanner sc) throws Exception {
        System.out.println("Enter account number:");
        int num = sc.nextInt();
        System.out.println("Enter amount to withdraw:");
        int amt = sc.nextInt();

        String query = "SELECT balance FROM account WHERE num=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, num);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int balance = rs.getInt("balance");
            if (amt <= balance) {
                String query1 = "UPDATE account SET balance=balance-? WHERE num=?";
                PreparedStatement ps1 = con.prepareStatement(query1);
                ps1.setInt(1, amt);
                ps1.setInt(2, num);
                ps1.executeUpdate();
                System.out.println("Withdraw successful");
            } else {
                System.out.println("Error: Low balance");
            }
        } else {
            System.out.println("Error: Account not found");
        }
    }
}