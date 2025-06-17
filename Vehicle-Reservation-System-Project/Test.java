/**
 * The Test class is used to test the program.
 * @author (Aly Shalaby)
 * @version (06/04/2025)
 */
public class Test {
    public static void main(String[] args) throws java.io.FileNotFoundException {
        System.out.println("\n=================================");
        System.out.println("The Program is starting Bismillah");
        System.out.println("=================================\n");
        
        ReservationSystem resSystem = new ReservationSystem();
        
        // Load all data
        System.out.println("Please select the vehicle data file...");
        resSystem.readVehicleData();
        System.out.println("Please select the customer data file...");
        resSystem.readCustomerData();
        
        // Run all tests
        resSystem.runTestReservations();
    }
}