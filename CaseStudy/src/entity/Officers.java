package entity;

public class Officers {
    private String officerID;
    private String firstName;
    private String lastName;
    private String badgeNumber;
    private String rank;
    private String contactInfo;
    private String agencyID;
    //Default Constructor
    public Officers(){}
    // Constructor
    public Officers(String officerID, String firstName, String lastName, String badgeNumber, String rank, String contactInfo, String agencyID) {
        this.officerID = officerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.contactInfo = contactInfo;
        this.agencyID = agencyID;
    }

    // Getters and Setters
    public String getOfficerID() {
        return officerID;
    }

    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(String agencyID) {
        this.agencyID = agencyID;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Officer{" +
                "officerID='" + officerID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", rank='" + rank + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", agencyID='" + agencyID + '\'' +
                '}';
    }
}
