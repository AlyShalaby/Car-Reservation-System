/**
 * The ReservationSystem class manages a list of vehicles.
 * It can read data from a file, store the data,
 * and show details of all vehicles. and makes random number for
 * the customers id.
 * @author (Aly Shalaby)
 * @version (16/04/2025)
 */

//youtube helped me a bit
import java.io.*;
import java.util.*;
import java.awt.*;

public class ReservationSystem {
    // private ArrayList<Vehicle> vehicleList;
    // private ArrayList<Customer> customerList;
    private Map<String, Vehicle> vehicleMap;  // Key: vehID
    private Map<String, Customer> customerMap;  // Key: customerID
    private String currentSection = ""; // Track [Car data], [van data], etc.
    private Random randomGenerator = new Random();
    private int lastID = 0;
    private Map<String, VehicleReservation> vehicleReservationMap;
    private Diary diary;
    private int lastReservationNo = 0;

   public ReservationSystem() {
        //vehicleList = new ArrayList<>();
        //customerList = new ArrayList<>();
        vehicleMap = new HashMap<>();
        customerMap = new HashMap<>();
        vehicleReservationMap = new HashMap<>();
        diary = new Diary();
    }

   public void readVehicleData() {
        FileDialog fileDialog = new FileDialog((Frame)null, "Select File", FileDialog.LOAD);
        fileDialog.setDirectory(".");
        fileDialog.setVisible(true);
        String filePath = fileDialog.getDirectory() + fileDialog.getFile();
        if (filePath == null) return;
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            fileScanner.useDelimiter("\\s*,\\s*");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.startsWith("//") || line.isEmpty()) continue;
                if (line.startsWith("[")) {
                    currentSection = line.replace("[", "").replace("]", "").trim().toLowerCase();
                    continue;
                }
                processLine(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

   private void processLine(String line) {
        try (Scanner lineScanner = new Scanner(line)) {
            lineScanner.useDelimiter("\\s*,\\s*");
            Vehicle vehicle = null;
            
            // Only process if we have a valid section
            if (currentSection.isEmpty()) {
                return;
            }
            
            switch (currentSection.toLowerCase()) {
                case "car data":
                    vehicle = new Car();
                    break;
                case "van data":
                    vehicle = new Van();
                    break;
                case "truck data":
                    vehicle = new Truck();
                    break;
                default:
                    return;
            }
            
            if (vehicle != null) {
                vehicle.readData(lineScanner);
                vehicleMap.put(vehicle.getVehID(), vehicle);
            }
        } catch (Exception e) {
            // ignore processing errors
        }
    }

   public void printAllVehicles() {
        for (Vehicle vehicle : vehicleMap.values()) {
            vehicle.printDetails();
            System.out.println("----------------------");
        }
    }

   public String generateCustomerID(String prefix, int digits) {//had to use youtube to help me 
        int min = (int) Math.pow(10, digits - 1);
        int max = (int) Math.pow(10, digits) - 1;
        int randomNum = randomGenerator.nextInt(max - min + 1) + min;
        return prefix + randomNum;
    }

   public void storeCustomer(Customer customer) {
        if (customer.getCustomerID().equals("unknown")) {
            String newID = generateCustomerID("AB-", 6); //6 digits 
            customer.setCustomerID(newID);
        }
        customerMap.put(customer.getCustomerID(), customer);
    }

   public void printAllCustomers(){
        for (Customer customer : customerMap.values()) {
            customer.printDetails();
            System.out.println("----------------------");
        }
    }

    public void readCustomerData() {
        currentSection = ""; // Reset section
        FileDialog fileDialog = new FileDialog((Frame)null, "Select Customer File", FileDialog.LOAD);
        fileDialog.setDirectory(".");
        fileDialog.setVisible(true);
        String filePath = fileDialog.getDirectory() + fileDialog.getFile();
        if (filePath == null) return;

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.startsWith("//") || line.isEmpty()) continue;
                
                try (Scanner lineScanner = new Scanner(line)) {
                    lineScanner.useDelimiter(",\\s*");
                    Customer customer = new Customer();
                    customer.readData(lineScanner);
                    storeCustomer(customer);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Customer file not found: " + e.getMessage());
        }
   }


   public void writeCustomerData(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        for (Customer customer : customerMap.values()) {
            customer.writeData(writer);
        }
        writer.close();
    }

   public Vehicle getVehicle(String vehID) {
        return vehicleMap.get(vehID);
    }

   public Customer getCustomer(String customerID) {
        return customerMap.get(customerID);
    }
    
        public String generateReservationNo() {
        lastReservationNo++;
        return String.format("%06d", lastReservationNo);
   }

    public void storeVehicleReservation(VehicleReservation reservation) {
        vehicleReservationMap.put(reservation.getReservationNo(), reservation);
        diary.addReservation(reservation);
   }

    public VehicleReservation getVehicleReservation(String reservationNo) {
        return vehicleReservationMap.get(reservationNo);
   }

    public boolean makeVehicleReservation(String customerID, String vehID, String startDate, int noOfDays) {
        // Validate customer, vehicle and date and days
        if (!customerMap.containsKey(customerID)) {
            System.out.println("Error: Customer ID " + customerID + " not found");
            return false;
        }
        if (!vehicleMap.containsKey(vehID)) {
            System.out.println("Error: Vehicle ID " + vehID + " not found");
            return false;
        }
        if (!DateUtil.isValidDateString(startDate)) {
            System.out.println("Error: Invalid date format. Use dd-MM-yyyy");
            return false;
        }
        if (noOfDays <= 0) {
            System.out.println("Error: Number of days must be positive");
            return false;
        }
        Date start = DateUtil.convertStringToDate(startDate);
        Date end = DateUtil.incrementDate(start, noOfDays - 1);
        
        for (VehicleReservation existingRes : vehicleReservationMap.values()) {
            if (existingRes.getVehID().equals(vehID)) {
                Date existingStart = existingRes.getStartDate();
                Date existingEnd = existingRes.getEndDate();
                
                if ((start.compareTo(existingStart) >= 0 && start.compareTo(existingEnd) <= 0) ||
                    (end.compareTo(existingStart) >= 0 && end.compareTo(existingEnd) <= 0) ||
                    (start.compareTo(existingStart) <= 0 && end.compareTo(existingEnd) >= 0)) {
                    System.out.println("Error: Vehicle already booked for this period.");
                    return false;
                }
            }
        }

        String reservationNo = generateReservationNo();
        System.out.println("Reservation created: " + reservationNo);
        VehicleReservation reservation = new VehicleReservation(
            reservationNo, vehID, customerID, startDate, noOfDays);
        storeVehicleReservation(reservation);
        return true;
   }

    public void printAllVehicleReservations() {
        for (VehicleReservation reservation : vehicleReservationMap.values()) {
            reservation.printDetails();
            System.out.println("----------------------");
        }
   }

    public void writeVehicleReservationData(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        for (VehicleReservation reservation : vehicleReservationMap.values()) {
            reservation.writeData(writer);
        }
        writer.close();
   }

    public void readVehicleReservationData() {
        FileDialog fileDialog = new FileDialog((Frame)null, "Select Reservation File", FileDialog.LOAD);
        fileDialog.setDirectory(".");
        fileDialog.setVisible(true);
        String filePath = fileDialog.getDirectory() + fileDialog.getFile();
        if (filePath == null) return;

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.startsWith("//") || line.isEmpty()) continue;
                
                try (Scanner lineScanner = new Scanner(line)) {
                    lineScanner.useDelimiter("\\s*,\\s*");
                    VehicleReservation reservation = new VehicleReservation(
                        lineScanner.next().trim(),
                        lineScanner.next().trim(),
                        lineScanner.next().trim(),
                        lineScanner.next().trim(),
                        Integer.parseInt(lineScanner.next().trim())
                    );
                    storeVehicleReservation(reservation);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Reservation file not found: " + e.getMessage());
    }
   }

    public void printDiaryEntries(String startDate, String endDate) {
        try {
            Date start = DateUtil.convertStringToDate(startDate);
            Date end = DateUtil.convertStringToDate(endDate);
            diary.printEntries(start, end);
            System.out.println("(Showing proper reservations)");
        } catch (RuntimeException e) {
            System.out.println("Error: Invalid date format. Use dd-MM-yyyy");
        }
   }

    public void deleteVehicleReservation(String reservationNo) {
        VehicleReservation reservation = vehicleReservationMap.get(reservationNo);
        if (reservation != null) {
            diary.deleteReservation(reservation);
            vehicleReservationMap.remove(reservationNo);
            System.out.println("Reservation " + reservationNo + " deleted successfully");
        } else {
            System.out.println("Error: Reservation " + reservationNo + " not found");
        }
   }

   public Set<String> getCustomerIds() {
        return customerMap.keySet();
   }

   public void runTestReservations() throws FileNotFoundException {
        // Display all data
        printAllVehicles();
        printAllCustomers();
        writeCustomerData("customer_output.txt");
        
        // Find customers
        String newtonId = "";
        String smithId = "";
        
        for (String id : getCustomerIds()) {
            Customer customer = getCustomer(id);
            if (customer.getFirstName().equals("David") && 
                customer.getSurname().equals("Newton")) {
                newtonId = id;
            }
            if (customer.getFirstName().equals("Sara") && 
                customer.getSurname().equals("Smith")) {
                smithId = id;
            }
        }
        
        // Make reservations
        System.out.println("\n--- Testing Basic Reservation ---");
        makeVehicleReservation(newtonId, "TF-63403", "10-04-2025", 3);
        
        System.out.println("\n--- Testing Conflict Detection ---");
        makeVehicleReservation(smithId, "TF-63403", "11-04-2025", 2);
        
        System.out.println("\n--- Testing Valid Second Reservation ---");
        makeVehicleReservation(smithId, "TF-61273", "15-04-2025", 2);
        
        // Show final results
        printAllVehicleReservations();
        printDiaryEntries("10-04-2025", "20-04-2025");
        deleteVehicleReservation("000001");
   }

}