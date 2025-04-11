package entity;

public class JunctionIV {
    private int incidentID;
    private String victimID;

    // Default constructor
    public JunctionIV() {}

    // Parameterized constructor
    public JunctionIV(int incidentID, String victimID) {
        this.incidentID = incidentID;
        this.victimID = victimID;
    }

    // Getters and Setters
    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public String getVictimID() {
        return victimID;
    }

    public void setVictimID(String victimID) {
        this.victimID = victimID;
    }

    // toString method

    @Override
    public String toString() {
        return "JunctionIV{" +
                "incidentID=" + incidentID +  // Corrected: Removed extra quotes
                ", victimID='" + victimID + '\'' +
                '}';
    }
    

}

