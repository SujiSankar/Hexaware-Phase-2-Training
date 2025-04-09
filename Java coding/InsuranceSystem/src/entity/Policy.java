package entity;

public class Policy {
    private String policyId;
    private String policyName;
    private double coverageAmount;
    private double premium;

    // Default Constructor
    public Policy() {}

    // Parameterized Constructor
    public Policy(String policyId, String policyName, double coverageAmount, double premium) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
    }

    // Getters and Setters
    public String getPolicyId() { return policyId; }
    public void setPolicyId(String policyId) { this.policyId = policyId; }

    public String getPolicyName() { return policyName; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }

    public double getCoverageAmount() { return coverageAmount; }
    public void setCoverageAmount(double coverageAmount) { this.coverageAmount = coverageAmount; }

    public double getPremium() { return premium; }
    public void setPremium(double premium) { this.premium = premium; }

    // toString() Method
    @Override
    public String toString() {
        return "Policy{" +
                "policyId='" + policyId + '\'' +
                ", policyName='" + policyName + '\'' +
                ", coverageAmount=" + coverageAmount +
                ", premium=" + premium +
                '}';
    }
}
