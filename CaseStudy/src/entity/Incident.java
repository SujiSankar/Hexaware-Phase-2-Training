package entity;

import java.util.Date;

public class Incident {
    private int incidentID;
    private String incidentType;
    private Date incidentDate;
    private double latitude;
    private double longitude;
    private String description;
    private String status;
    private int victimID;
    private String suspectID;
    private String officerID;

    // Default Constructor
    public Incident() {}

    // Parameterized Constructor
    public Incident(int incidentID, String incidentType, Date incidentDate, double latitude, double longitude, String description, String status, int victimID, String suspectID, String officerID) {
        this.incidentID = incidentID;
        this.incidentType = incidentType;
        this.incidentDate = incidentDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.status = status;
        this.victimID = victimID;
        this.suspectID = suspectID;
        this.officerID = officerID;
    }

    // Getters and Setters
    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVictimID() {
        return victimID;
    }

    public void setVictimID(int victimID) {
        this.victimID = victimID;
    }

    public String getSuspectID() {
        return suspectID;
    }

    public void setSuspectID(String suspectID) {
        this.suspectID = suspectID;
    }

    public String getOfficerID() {
        return officerID;
    }

    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    
    }
    @Override
    public String toString() {
        return "Incident ID: " + incidentID + 
               ", Type: " + incidentType + 
               ", Date: " + incidentDate + 
               ", Location: (" + latitude + ", " + longitude + ")" + 
               ", Description: " + description + 
               ", Status: " + status + 
               ", Victim ID: " + victimID + 
               ", Suspect ID: " + suspectID + 
               ", Officer ID: " + officerID;
    }
    

}
