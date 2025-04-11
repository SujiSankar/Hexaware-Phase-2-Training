package dao;

import com.hexaware.myexceptions.IncidentNumberNotFoundException;
import entity.Incident;
import entity.Reports;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface ICrimeAnalysisService {

    boolean createIncident(Incident incident) throws SQLException;

    boolean updateIncidentStatus(int incidentID, String status) throws SQLException, IncidentNumberNotFoundException;

    Collection<Incident> getIncidentsInDateRange(Date startDate, Date endDate) throws SQLException;

    Collection<Incident> searchIncidents(String incidentType) throws SQLException;

    Reports generateIncidentReport(int reportID, Incident incident, String reportDetails) throws SQLException;

    boolean reportExists(int reportID) throws SQLException;

    boolean finalizeReport(int reportID) throws SQLException;
}


