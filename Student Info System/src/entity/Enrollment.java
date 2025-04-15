package entity;

import java.util.Date;

public class Enrollment {
    private String enrollmentId;
    private Student student;  
    private Course course;   
    private Date enrollmentDate;

    // Default constructor
    public Enrollment() {}

    // Parameterized constructor
    public Enrollment(String enrollmentId, Student student, Course course, Date enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }
    // Getters and Setters
    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student; 
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course; 
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId='" + enrollmentId + '\'' +
                ", student=" + (student != null ? student.getFirstName() + " " + student.getLastName() : "Unknown") +  
                ", course=" + (course != null ? course.getCourseName() : "Unknown") +  
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}
