package main;

import com.hexaware.myexceptions.IncidentNumberNotFoundException;
import dao.CrimeAnalysisServiceImpl;
import entity.Incident;
import entity.Reports;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        CrimeAnalysisServiceImpl service = new CrimeAnalysisServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nCrime Analysis System");
            System.out.println("1. Create Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. Get Incidents in Date Range");
            System.out.println("4. Search Incidents by Type");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. Finalize Report");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Incident ID: ");
                        int incidentID = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.print("Enter Incident Type: ");
                        String incidentType = scanner.nextLine();

                        System.out.print("Enter Latitude: ");
                        double latitude = scanner.nextDouble();
                        System.out.print("Enter Longitude: ");
                        double longitude = scanner.nextDouble();
                        scanner.nextLine(); 

                        System.out.print("Enter Description: ");
                        String description = scanner.nextLine();

                        System.out.print("Enter Status: ");
                        String status = scanner.nextLine();

                        System.out.print("Enter Victim ID: ");
                        int victimID = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.print("Enter Suspect ID: ");
                        String suspectID = scanner.nextLine();

                        System.out.print("Enter Officer ID: ");
                        String officerID = scanner.nextLine();

                        Incident incident = new Incident(incidentID, incidentType, new Date(), latitude, longitude, description, status, victimID, suspectID, officerID);
                        boolean created = service.createIncident(incident);
                        System.out.println(created ? "Incident Created Successfully!" : "Failed to Create Incident");

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter Incident ID to Update: ");
                        int updateIncidentID = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.print("Enter New Status: ");
                        String newStatus = scanner.nextLine();

                        boolean updated = service.updateIncidentStatus(updateIncidentID, newStatus);
                        System.out.println(updated ? "Incident Status Updated!" : "Update Failed");

                    } catch (IncidentNumberNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());  
                    } catch (SQLException e) {
                        System.out.println("Database Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        sdf.setLenient(false);

                        System.out.print("Enter Start Date (YYYY-MM-DD): ");
                        String startDateStr = scanner.nextLine();
                        System.out.print("Enter End Date (YYYY-MM-DD): ");
                        String endDateStr = scanner.nextLine();

                        Date startDate = sdf.parse(startDateStr);
                        Date endDate = sdf.parse(endDateStr);

                        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
                        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

                        Collection<Incident> incidents = service.getIncidentsInDateRange(sqlStartDate, sqlEndDate);

                        if (incidents.isEmpty()) {
                            System.out.println("No incidents found in the given date range.");
                        } else {
                            for (Incident inc : incidents) {
                                System.out.println("Incident ID: " + inc.getIncidentID() +
                                                   ", Type: " + inc.getIncidentType() +
                                                   ", Date: " + inc.getIncidentDate() +
                                                   ", Location: (" + inc.getLatitude() + ", " + inc.getLongitude() + ")" +
                                                   ", Status: " + inc.getStatus());
                            }
                        }
                    } catch (ParseException e) {
                        System.out.println("Invalid date format! Please enter the date in YYYY-MM-DD format.");
                    } catch (SQLException e) {
                        System.out.println("Database error: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Incident Type to Search: ");
                        String searchType = scanner.nextLine();
                
                        Collection<Incident> incidents = service.searchIncidents(searchType);
                
                        if (incidents.isEmpty()) {
                            System.out.println("No incidents found for type: " + searchType);
                        } else {
                            System.out.println("Incidents found:");
                            for (Incident incident : incidents) {
                                System.out.println(incident);
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Database Error: " + e.getMessage());
                    }
                    break;
                
                case 5:
                    try {
                        System.out.print("Enter Report ID: ");
                        int reportID = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.print("Enter Incident ID for Report: ");
                        int reportIncidentID = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.print("Enter Officer ID: ");
                        String reportOfficerID = scanner.nextLine();

                        System.out.print("Enter Report Details: ");
                        String reportDetails = scanner.nextLine();

                        // Create an Incident object with necessary details
                        Incident reportIncident = new Incident(reportIncidentID, "", new Date(), 0, 0, "", "", 0, "", reportOfficerID);

                        // Call the updated generateIncidentReport method
                        Reports report = service.generateIncidentReport(reportID, reportIncident, reportDetails);

                        if (report != null) {
                            System.out.println("Report Generated: " + report);
                        } else {
                            System.out.println("Failed to Generate Report");
                        }

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        System.out.print("Enter Report ID to Finalize: ");
                        int finalizeReportID = scanner.nextInt();
                        scanner.nextLine(); 

                        boolean finalized = service.finalizeReport(finalizeReportID);
                        System.out.println(finalized ? "Report Finalized Successfully!" : "Failed to Finalize Report");

                    } catch (SQLException e) {
                        System.out.println("Database Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    exit = true;
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
