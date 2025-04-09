package dao;

import entity.Policy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import myexceptions.PolicyNotFoundException;
import util.DBConnection;

public class InsuranceServiceImpl implements IPolicyService {

    // Create a new policy and insert it into the database
    @Override
    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO policy (policyId, policyName, coverageAmount, premium) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, policy.getPolicyId());
            pstmt.setString(2, policy.getPolicyName());
            pstmt.setDouble(3, policy.getCoverageAmount());
            pstmt.setDouble(4, policy.getPremium());

            return pstmt.executeUpdate() > 0;  // Returns true if insertion is successful

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve a policy by ID
    @Override
    public Policy getPolicy(String policyId) throws PolicyNotFoundException {
        String query = "SELECT * FROM policy WHERE policyId = ?";
        try (Connection conn = DBConnection.getConnection();
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
            e.printStackTrace();
        }
        return null;
    }


    // Retrieve all policies
    @Override
    public Collection<Policy> getAllPolicies() {
        String query = "SELECT * FROM policy";
        Collection<Policy> policies = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                policies.add(new Policy(
                        rs.getString("policyId"),
                        rs.getString("policyName"),
                        rs.getDouble("coverageAmount"),
                        rs.getDouble("premium")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }

    // Update an existing policy
    @Override
    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE policy SET policyName = ?, coverageAmount = ?, premium = ? WHERE policyId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, policy.getPolicyName());
            pstmt.setDouble(2, policy.getCoverageAmount());
            pstmt.setDouble(3, policy.getPremium());
            pstmt.setString(4, policy.getPolicyId());

            return pstmt.executeUpdate() > 0;  // Returns true if update is successful

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a policy by ID
    @Override
    public boolean deletePolicy(String policyId) {
        String query = "DELETE FROM policy WHERE policyId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, policyId);
            return pstmt.executeUpdate() > 0;  // Returns true if deletion is successful

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
