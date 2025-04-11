package entity;

public class Evidence {
    private String evidenceID;
    private String description;
    private String locationFound;
    private int incidentID;

    // Default constructor
    public Evidence() {}

    // Parameterized constructor
    public Evidence(String evidenceID, String description, String locationFound, int incidentID) {
        this.evidenceID = evidenceID;
        this.description = description;
        this.locationFound = locationFound;
        this.incidentID = incidentID;
    }

    // Getters and Setters
    public String getEvidenceID() {
        return evidenceID;
    }

    public void setEvidenceID(String evidenceID) {
        this.evidenceID = evidenceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public void setLocationFound(String locationFound) {
        this.locationFound = locationFound;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    // toString method
    @Override
    public String toString() {
        return "Evidence{" +
                "evidenceID='" + evidenceID + '\'' +
                ", description='" + description + '\'' +
                ", locationFound='" + locationFound + '\'' +
                ", incidentID=" + incidentID +
                '}';
    }
}