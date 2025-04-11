package test; 
import dao.CrimeAnalysisServiceImpl; 
import entity.Incident; 
import org.junit.jupiter.api.*; 
import 
com.hexaware.myexceptions.IncidentNumberNotFoundException; 
import java.sql.SQLException; 
import java.util.Date; 
import static org.junit.jupiter.api.Assertions.*; 
@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
class CrimeAnalysisSystemTest { 
    private CrimeAnalysisServiceImpl db; 
    @BeforeAll 
    void setup() { 
        db = new CrimeAnalysisServiceImpl(); 
    } 
 
    
     //Test Case 1: Incident Creation 
     // Ensures an incident is created successfully with the correct  
    @Test 
    @DisplayName("Test: Incident is created successfully") 
    void testIncidentCreatedSuccessfully() throws SQLException { 
        Incident incident = new Incident( 
                101, "Burglary", new Date(), 12.98, 77.59, 
                "Jewelry stolen", "Open", 1, "SUS001", "OF123" 
        ); 
 
        boolean result = db.createIncident(incident); 
        assertTrue(result, "Incident creation should return true"); 
 
        Incident retrievedIncident = db.findIncidentById(101); 
        assertNotNull(retrievedIncident, "Incident should exist in the 
database"); 
        assertAll("Incident attributes should match", 
                () -> assertEquals(101, retrievedIncident.getIncidentID(), 
"Incident ID should match"), 
                () -> assertEquals("Burglary", 
retrievedIncident.getIncidentType(), "Incident type should match"), 
                () -> assertEquals("Open", retrievedIncident.getStatus(), 
"Incident status should match") 
        ); 
    } 
 
     
     // Test Case 2: Incident Status Update 
     // Ensures an incident's status is updated correctly. 
    @Test 
    @DisplayName("Test: Incident status is updated successfully") 
    void testUpdateIncidentStatusSuccessfully() throws SQLException, 
IncidentNumberNotFoundException { 
        db.createIncident(new Incident(102, "Robbery", new Date(), 15.50, 
78.40, 
                "Bank heist", "Open", 2, "SUS002", "OF456")); 
        boolean updateResult = db.updateIncidentStatus(102, "Closed"); 
        assertTrue(updateResult, "Incident status update should return 
true"); 
         
        Incident updatedIncident = db.findIncidentById(102); 
        assertNotNull(updatedIncident, "Incident should exist in the 
database"); 
        assertEquals("Closed", updatedIncident.getStatus(), "Incident 
status should be updated to Closed"); 
    } 
}  
