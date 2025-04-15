package entity;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private int teacherId;  
    private String firstName;
    private String lastName;
    private String email;

    private List<Course> assignedCourses;  

    // Default constructor
    public Teacher() {
        this.assignedCourses = new ArrayList<>();
    }

    // Parameterized constructor
    public Teacher(int teacherId, String firstName, String lastName, String email) {
        this.teacherId = teacherId;  
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.assignedCourses = new ArrayList<>();
    }

    // Getters and Setters
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public void setAssignedCourses(List<Course> assignedCourses) {
        this.assignedCourses = assignedCourses;
    }

    public void assignCourse(Course course) {
        if (!assignedCourses.contains(course)) {
            assignedCourses.add(course);
        }
    }

    public void removeCourse(Course course) {
        assignedCourses.remove(course);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", assignedCourses=" + (assignedCourses != null ? assignedCourses.size() : 0) +
                '}';
    }
}
