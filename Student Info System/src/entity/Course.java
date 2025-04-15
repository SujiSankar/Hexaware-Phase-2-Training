package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course {
    private String courseId;  
    private String courseName;
    private int credits;
    private int teacherId; // Matches DB
    private String courseCode;

    private Teacher teacher;  // Optional - for full object mapping
    private List<Enrollment> enrollments;

    public Course() {
        this.enrollments = new ArrayList<>();
    }

    public Course(String courseId, String courseName, int credits, int teacherId, String courseCode) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.teacherId = teacherId;
        this.courseCode = courseCode;
        this.enrollments = new ArrayList<>();
    }

    // Overloaded constructor without teacherId (in case it's not yet assigned)
    public Course(String courseId, String courseName, int credits, String courseCode) {
        this(courseId, courseName, credits, 0, courseCode); // teacherId = 0 => unassigned
    }

    // Getters and setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;
        this.teacherId = teacher.getTeacherId(); // Keep both updated
    }

    public void enrollStudent(String enrollmentId, Student student, Date enrollmentDate) {
        Enrollment enrollment = new Enrollment(enrollmentId, student, this, enrollmentDate);
        enrollments.add(enrollment);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", teacherId=" + teacherId +
                ", courseCode='" + courseCode + '\'' +
                ", teacher=" + (teacher != null ? teacher.getFirstName() + " " + teacher.getLastName() : "None") +
                '}';
    }
}
