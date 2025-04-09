package mainmod;

import dao.IPolicyService;
import dao.InsuranceServiceImpl;
import entity.Policy;
import java.util.Scanner;
import myexceptions.PolicyNotFoundException;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IPolicyService policyService = new InsuranceServiceImpl();

        while (true) {
            System.out.println("\nInsurance Management System");
            System.out.println("1.Create Policy");
            System.out.println("2.Get Policy by ID");
            System.out.println("3.Get All Policies");
            System.out.println("4.Update Policy");
            System.out.println("5.Delete Policy");
            System.out.println("6.Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Policy ID: ");
                    String policyId = scanner.next();
                    scanner.nextLine(); 

                    System.out.print("Enter Policy Name: ");
                    String policyName = scanner.nextLine();

                    System.out.print("Enter Coverage Amount: ");
                    double coverageAmount = scanner.nextDouble();

                    System.out.print("Enter Premium Amount: ");
                    double premium = scanner.nextDouble();
                    scanner.nextLine(); 

                    Policy policy = new Policy(policyId, policyName, coverageAmount, premium);
                    System.out.println("Trying to insert Policy ID:" + policyId);
                    boolean created = policyService.createPolicy(policy);
                    System.out.println("Policy creation result: " + created);
                    System.out.println(created ? "Policy created successfully..." : "Policy already Exists.");
                    break;
                
                case 2:
                    System.out.print("Enter Policy ID to fetch: ");
                    String searchId = scanner.next();
                    scanner.nextLine(); 

                    try {
                        Policy fetchedPolicy = policyService.getPolicy(searchId);
                        System.out.println("Policy Found: " + fetchedPolicy);
                    } catch (PolicyNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                
                case 3:
                    System.out.println("Displaying all policies from the Database");
                    for (Policy p : policyService.getAllPolicies()) {
                        System.out.println(p);
                    }
                    break;
                
                case 4:
                    System.out.print("Enter Policy ID to update: ");
                    String updateId = scanner.next();
                    scanner.nextLine(); 

                    System.out.print("Enter New Policy Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter New Coverage Amount: ");
                    double newCoverage = scanner.nextDouble();

                    System.out.print("Enter New Premium Amount: ");
                    double newPremium = scanner.nextDouble();
                    scanner.nextLine(); 
                    
                    Policy updatedPolicy = new Policy(updateId, newName, newCoverage, newPremium);
                    boolean updated = policyService.updatePolicy(updatedPolicy);
                    System.out.println(updated ? "Policy updated successfully..." : "Such Policy Not Found.");
                    break;
                
                case 5:
                    System.out.print("Enter Policy ID to delete: ");
                    String deleteId = scanner.next();
                    scanner.nextLine();

                    boolean deleted = policyService.deletePolicy(deleteId);
                    System.out.println(deleted ? "Policy deleted successfully..." : "Such Policy Not Found.");
                    break;
                
                case 6:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;
                
                default:
                    System.out.println("Invalid choice, Please try again.");
            }
        }
    }
}

