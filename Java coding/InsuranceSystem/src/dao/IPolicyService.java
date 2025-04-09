package dao;

import entity.Policy;
import java.util.Collection;
import myexceptions.PolicyNotFoundException;

public interface IPolicyService {
    
    // Method to create a new policy
    boolean createPolicy(Policy policy);

    // Method to retrieve a policy by its ID
    Policy getPolicy(String searchId) throws PolicyNotFoundException;

    // Method to get all policies
    Collection<Policy> getAllPolicies();

    // Method to update an existing policy
    boolean updatePolicy(Policy policy);

    // Method to delete a policy by its ID
    boolean deletePolicy(String policyId);
} 
