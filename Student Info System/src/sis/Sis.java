package sis;

import dao.ISisdb;
import dao.ISisdbImpl;
import entity.Course;
import entity.Enrollment;
import entity.Payment;
import entity.Student;
import entity.Teacher;

import java.util.Date;
import java.util.List;
import exception.*;

public class Sis {

    private ISisdb sisDb;

    public Sis() {
        sisDb = new ISisdbImpl();  
    }

    // Add Teacher
    public void addTeacher(Teacher teacher) {
        try {
            sisDb.addTeacher(teacher);
        } catch (InvalidTeacherDataException e) {
            System.err.println("Error adding teacher: " + e.getMessage());
        }
    }

    // Add Course
    public void addCourse(Course course) {
        try {
            sisDb.addCourse(course);
        } catch (InvalidCourseDataException e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }

    // Add Student
    public void addStudent(Student student) {
        try {
            sisDb.addStudent(student);
        } catch (InvalidStudentDataException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    // Add Payment
    public void addPayment(Student student, int paymentId, double amount, Date paymentDate) {
        try {
            Payment payment = new Payment(paymentId, student, amount, paymentDate);
            sisDb.addPayment(payment);
        } catch (PaymentValidationException e) {
            System.err.println("Error adding payment: " + e.getMessage());
        }
    }

    // Add Enrollment
    public void addEnrollment(Student student, Course course, String enrollmentId, Date enrollmentDate) {
        try {
            Enrollment enrollment = new Enrollment(enrollmentId, student, course, enrollmentDate);
            sisDb.addEnrollment(enrollment);
        } catch (DuplicateEnrollmentException e) {
            System.err.println("Error enrolling student: " + e.getMessage());
        } catch (CourseNotFoundException e) {
            System.err.println("Course not found: " + e.getMessage());
        } catch (StudentNotFoundException e) {
            System.err.println("Student not found: " + e.getMessage());
        }
    }

    // Assign Teacher to a Course
    public void assignCourseToTeacher(Course course, Teacher teacher) {
        try {
            sisDb.assignCourseToTeacher(course, teacher);
        } catch (CourseNotFoundException e) {
            System.err.println("Course not found: " + e.getMessage());
        } catch (TeacherNotFoundException e) {
            System.err.println("Teacher not found: " + e.getMessage());
        }
    }

    // Get Enrollments for a Student
    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        try {
            return sisDb.getEnrollmentsForStudent(student.getStudentId());
        } catch (StudentNotFoundException e) {
            System.err.println("Error retrieving enrollments for student: " + e.getMessage());
            return null; // Return null or an empty list, depending on your preference
        }
    }

    // Get Courses for a Teacher
    public List<Course> getCoursesForTeacher(Teacher teacher) {
        try {
            return sisDb.getCoursesForTeacher(teacher.getTeacherId());
        } catch (TeacherNotFoundException e) {
            System.err.println("Error retrieving courses for teacher: " + e.getMessage());
            return null; // Return null or an empty list
        }
    }

    // Retrieve all Students
    public List<Student> getAllStudents() {
        return sisDb.getAllStudents();
    }

    // Retrieve all Teachers
    public List<Teacher> getAllTeachers() {
        return sisDb.getAllTeachers();
    }

    // Retrieve all Courses
    public List<Course> getAllCourses() {
        return sisDb.getAllCourses();
    }

    // Retrieve all Payments
    public List<Payment> getAllPayments() {
        return sisDb.getAllPayments();
    }
}
