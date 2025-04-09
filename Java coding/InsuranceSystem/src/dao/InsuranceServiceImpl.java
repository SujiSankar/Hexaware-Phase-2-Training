package dao;

import entity.Policy;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import myexceptions.PolicyNotFoundException;
import util.DBConnection;
import util.Propertyutil;

public class InsuranceServiceImpl implements IPolicyService {

    private static final String PROPERTIES_FILE = "dbconfig.properties"; // Properties file name

    // Create a new policy and insert it into the database
    @Override
    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO policy (policyId, policyName, coverageAmount, premium) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, policy.getPolicyId());
            pstmt.setString(2, policy.getPolicyName());
            pstmt.setDouble(3, policy.getCoverageAmount());
            pstmt.setDouble(4, policy.getPremium());

            return pstmt.executeUpdate() > 0;  

        } catch (SQLException e) {
            System.err.println("Failed to insert policy.");
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve a policy by ID
    @Override
    public Policy getPolicy(String policyId) throws PolicyNotFoundException {
        String query = "SELECT * FROM policy WHERE policyId = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, policyId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Policy(
                            rs.getString("policyId"),
                            rs.getString("policyName"),
                            rs.getDouble("coverageAmount"),
                            rs.getDouble("premium")
                    );
                } else {
                    throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
                }
            }
        } catch (SQLException e) {
            System.err.println(" Database error while fetching policy.");
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all policies
    @Override
    public Collection<Policy> getAllPolicies() {
        String query = "SELECT * FROM policy";
        Collection<Policy> policies = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Displaying all policies from the Database:");
            while (rs.next()) {
                policies.add(new Policy(
                        rs.getString("policyId"),
                        rs.getString("policyName"),
                        rs.getDouble("coverageAmount"),
                        rs.getDouble("premium")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Failed to retrieve policies.");
            e.printStackTrace();
        }
        return policies;
    }

    // Update an existing policy
    @Override
    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE policy SET policyName = ?, coverageAmount = ?, premium = ? WHERE policyId = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, policy.getPolicyName());
            pstmt.setDouble(2, policy.getCoverageAmount());
            pstmt.setDouble(3, policy.getPremium());
            pstmt.setString(4, policy.getPolicyId());

            return pstmt.executeUpdate() > 0;  

        } catch (SQLException e) {
            System.err.println("Failed to update policy.");
            e.printStackTrace();
        }
        return false;
    }

    // Delete a policy by ID
    @Override
    public boolean deletePolicy(String policyId) {
        String query = "DELETE FROM policy WHERE policyId = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, policyId);
            return pstmt.executeUpdate() > 0;  

        } catch (SQLException e) {
            System.err.println(" Failed to delete policy.");
            e.printStackTrace();
        }
        return false;
    }

    private Connection getConnection() {
        String connectionString = Propertyutil.getConnectionString(PROPERTIES_FILE);
        return DBConnection.getConnection(connectionString);
    }
}

