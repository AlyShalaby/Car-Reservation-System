/**
 * Represents a customer with details like 
 * name and ID, and includes methods 
 * for data management and display.
 *
 * @author (Aly Shalaby)
 * @version (05/04/2025)
 */
// used youtube to help me
import java.util.Scanner;
import java.io.PrintWriter;

public class Customer {
    private String customerID;
    private String surname;
    private String firstName;
    private String otherInitials;
    private String title;

    // Constructor with parameters 
    public Customer(String surname, String firstName, String otherInitials, String title) {
        this.customerID = "unknown";
        this.surname = surname;
        this.firstName = firstName;
        this.otherInitials = otherInitials;
        this.title = title;
    }

    // No-parameter
    public Customer() {
        this.customerID = "unknown";
        this.surname = "";
        this.firstName = "";
        this.otherInitials = "";
        this.title = "";
    }

    public void readData(Scanner scanner) {
        customerID = scanner.hasNext() ? scanner.next().trim() : "";
        surname = scanner.hasNext() ? scanner.next().trim() : "";
        firstName = scanner.hasNext() ? scanner.next().trim() : "";
        otherInitials = scanner.hasNext() ? scanner.next().trim() : "";
        title = scanner.hasNext() ? scanner.next().trim() : "";
    }

    public void printDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append(" ");
        sb.append(firstName).append(" ");
        if (!otherInitials.isEmpty()) {
            sb.append(otherInitials).append(" ");
        }
        sb.append(surname);
        System.out.println(sb.toString());
        System.out.println("Customer ID: " + customerID);
    }

    public String getCustomerID() { return customerID; }
    public String getSurname() { return surname; }
    public String getFirstName() { return firstName; }
    public String getOtherInitials() { return otherInitials; }
    public String getTitle() { return title; }

    public void setCustomerID(String id) { customerID = id; }
    public void setSurname(String s) { surname = s; }
    public void setFirstName(String f) { firstName = f; }
    public void setOtherInitials(String o) { otherInitials = o; }
    public void setTitle(String t) { title = t; }
    
    public void writeData(PrintWriter writer) {
        writer.println(customerID + ", " + surname + ", " + firstName + ", " + 
                      otherInitials + ", " + title);
    }
}
