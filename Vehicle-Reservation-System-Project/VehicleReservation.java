/**
 * Represents a vehicle reservation in the system.
 * Contains details about the reservation including dates and IDs.
 *
 * @author (Aly Shalaby)
 * @version (16/04/2025)
 */
import java.util.Date;
import java.util.*;
import java.io.*;

public class VehicleReservation {
    private String reservationNo;
    private String vehID;
    private String customerID;
    private Date startDate;
    private int noOfDays;
    
    public VehicleReservation(String reservationNo, String vehID, String customerID, 
                             String startDate, int noOfDays) {
        this.reservationNo = reservationNo;
        this.vehID = vehID;
        this.customerID = customerID;
        this.startDate = DateUtil.convertStringToDate(startDate);
        this.noOfDays = noOfDays;
    }
    
    public void readData(Scanner scanner) {
        reservationNo = scanner.next().trim();
        vehID = scanner.next().trim();
        customerID = scanner.next().trim();
        startDate = DateUtil.convertStringToDate(scanner.next().trim());
        noOfDays = Integer.parseInt(scanner.next().trim());
    }
    
    public void writeData(PrintWriter writer) {
        writer.println(reservationNo + ", " + vehID + ", " + customerID + ", " + 
                      DateUtil.convertDateToShortString(startDate) + ", " + noOfDays);
    }
    
    public void printDetails() {
        System.out.println("Reservation No: " + reservationNo);
        System.out.println("Vehicle ID: " + vehID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Start Date: " + DateUtil.convertDateToLongString(startDate));
        Date endDate = DateUtil.incrementDate(startDate, noOfDays - 1);
        System.out.println("End Date: " + DateUtil.convertDateToLongString(endDate));
        System.out.println("Number of Days: " + noOfDays);
        System.out.println();
    }
    
    @Override
    public String toString() {
        return "Reservation " + reservationNo + " (Vehicle: " + vehID + 
               ", Customer: " + customerID + ")";
    }
    
    // Getters
    public String getReservationNo() { return reservationNo; }
    public String getVehID() { return vehID; }
    public String getCustomerID() { return customerID; }
    public Date getStartDate() { return startDate; }
    public int getNoOfDays() { return noOfDays; }
    
    // end date
    public Date getEndDate() {
        return DateUtil.incrementDate(startDate, noOfDays - 1);
    }
}