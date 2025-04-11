package entity;

public class JunctionIS {
    private int incidentID;
    private String suspectID;

    // Default constructor
    public JunctionIS() {}

    // Parameterized constructor
    public JunctionIS(int incidentID, String suspectID) {
        this.incidentID = incidentID;
        this.suspectID = suspectID;
    }

    // Getters and Setters
    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public String getSuspectID() {
        return suspectID;
    }

    public void setSuspectID(String suspectID) {
        this.suspectID = suspectID;
    }

    // toString method
    @Override
    public String toString() {
        return "JunctionIS{" +
                "incidentID=" + incidentID + 
                ", suspectID='" + suspectID + '\'' +
                '}';
    }
}
