package entity;

import java.time.LocalDateTime;

public class Reports {
    private int reportID;
    private int incidentID;
    private String reportingOfficer;
    private LocalDateTime reportDate;
    private String reportDetails;
    private String status;

    // Default constructor
    public Reports() {}

    // Parameterized constructor
    public Reports(int reportID, int incidentID, String reportingOfficer, LocalDateTime reportDate, String reportDetails, String status) {
        this.reportID = reportID;
        this.incidentID = incidentID;
        this.reportingOfficer = reportingOfficer;
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
        this.status = status;
    }

    // Getters and Setters
    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public String getReportingOfficer() {
        return reportingOfficer;
    }

    public void setReportingOfficer(String reportingOfficer) {
        this.reportingOfficer = reportingOfficer;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    // toString method
    @Override
    public String toString() {
        return "Report{" +
                "reportID=" + reportID +
                ", incidentID=" + incidentID +
                ", reportingOfficer='" + reportingOfficer + '\'' +
                ", reportDate=" + reportDate +
                ", reportDetails='" + reportDetails + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}