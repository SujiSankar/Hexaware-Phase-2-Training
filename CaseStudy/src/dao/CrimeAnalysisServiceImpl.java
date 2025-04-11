package dao;

import com.hexaware.myexceptions.IncidentNumberNotFoundException;
import entity.Incident;
import entity.Reports;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import util.DBConnection;
import util.DBPropertyUtil;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

    private static final String PROPERTIES_FILE = "dbconfig.properties";
    private static Connection connection;  

    // Constructor to initialize the connection
    public CrimeAnalysisServiceImpl() {
        if (connection == null) {
            String connectionString = DBPropertyUtil.getConnectionString(PROPERTIES_FILE);
            connection = DBConnection.getConnection(connectionString);
        }
    }

    // Create a new incident
    @Override
    public boolean createIncident(Incident incident) throws SQLException {
        String sql = "INSERT INTO Incidents (IncidentID, IncidentType, IncidentDate, Latitude, Longitude, Description, Status, VictimID, SuspectID, OfficerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, incident.getIncidentID());
            ps.setString(2, incident.getIncidentType());
            ps.setDate(3, new java.sql.Date(incident.getIncidentDate().getTime()));
            ps.setDouble(4, incident.getLatitude());
            ps.setDouble(5, incident.getLongitude());
            ps.setString(6, incident.getDescription());
            ps.setString(7, incident.getStatus());
            ps.setInt(8, incident.getVictimID());
            ps.setString(9, incident.getSuspectID());  // Ensure consistency in data type
            ps.setString(10, incident.getOfficerID());

            return ps.executeUpdate() > 0;
        }
    }

     // Update the status of an incident
    @Override
    public boolean updateIncidentStatus(int incidentID, String status) throws SQLException, IncidentNumberNotFoundException {
        String sql = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, incidentID);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new IncidentNumberNotFoundException("Incident ID " + incidentID + " not found in the database.");
            }
            return true;
        }
    }

    public Collection<Incident> getIncidentsInDateRange(Date startDate, Date endDate) throws SQLException {
        Collection<Incident> incidentList = new ArrayList<>();
        String sql = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Incident incident = new Incident(
                    rs.getInt("IncidentID"),
                    rs.getString("IncidentType"),
                    rs.getTimestamp("IncidentDate"),
                    rs.getDouble("Latitude"),
                    rs.getDouble("Longitude"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getInt("VictimID"),
                    rs.getString("SuspectID"),
                    rs.getString("OfficerID")
                );
                incidentList.add(incident);
            }
        }
        return incidentList;
    }
    

    // Search for incidents based on incident type
    @Override
    public Collection<Incident> searchIncidents(String incidentType) throws SQLException {
        String sql = "SELECT * FROM Incidents WHERE IncidentType = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, incidentType);

            try (ResultSet rs = ps.executeQuery()) {
                Collection<Incident> incidents = new ArrayList<>();
                while (rs.next()) {
                    incidents.add(new Incident(
                        rs.getInt("IncidentID"),
                        rs.getString("IncidentType"),
                        rs.getDate("IncidentDate"),
                        rs.getDouble("Latitude"),   
                        rs.getDouble("Longitude"),  
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getInt("VictimID"),
                        rs.getString("SuspectID"),  // Ensure correct type
                        rs.getString("OfficerID")
                    ));
                }
                return incidents;
            }
        }
    }
    @Override
    public boolean reportExists(int reportID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Reports WHERE ReportID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reportID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    
    @Override
    public Reports generateIncidentReport(int reportID, Incident incident, String reportDetails) throws SQLException {
        if (reportExists(reportID)) {
            System.out.println("Error: Report ID already exists. Please use a new ID.");
            return null;
        }

        String sql = "INSERT INTO Reports (ReportID, IncidentID, ReportingOfficer, ReportDetails, Status, ReportDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reportID);
            ps.setInt(2, incident.getIncidentID());
            ps.setString(3, incident.getOfficerID());
            ps.setString(4, reportDetails);
            ps.setString(5, "Draft");
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return new Reports(reportID, incident.getIncidentID(), incident.getOfficerID(), 
                    LocalDateTime.now(), reportDetails, "Draft");
            }
        }
        return null;
    }

    public boolean finalizeReport(int reportID) throws SQLException {
        String sql = "UPDATE Reports SET Status = 'Finalized' WHERE ReportID = ? AND Status = 'Draft'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reportID);
            return ps.executeUpdate() > 0;
        }
    }
}
