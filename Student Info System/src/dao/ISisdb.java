package dao;

import entity.Course;
import entity.Enrollment;
import entity.Payment;
import entity.Student;
import entity.Teacher;

import java.util.List;
import exception.*;

public interface ISisdb {
    // Add operations
    void addStudent(Student student) throws InvalidStudentDataException;
    void addTeacher(Teacher teacher) throws InvalidTeacherDataException;
    void addCourse(Course course) throws InvalidCourseDataException;
    void addPayment(Payment payment) throws PaymentValidationException;
    
    // Enrollment operations
    void addEnrollment(Enrollment enrollment) throws DuplicateEnrollmentException, CourseNotFoundException, StudentNotFoundException;
    
    // Assignment operations
    void assignCourseToTeacher(Course course, Teacher teacher) throws CourseNotFoundException, TeacherNotFoundException;
    
    // Getters
    Student getStudentById(int studentId) throws StudentNotFoundException;
    Teacher getTeacherById(int teacherId) throws TeacherNotFoundException;
    Course getCourseById(int courseId) throws CourseNotFoundException;
    
    // Retrieve lists
    List<Student> getAllStudents();
    List<Teacher> getAllTeachers();
    List<Course> getAllCourses();
    List<Payment> getAllPayments();
    List<Enrollment> getEnrollmentsForStudent(int studentId) throws StudentNotFoundException;
    List<Course> getCoursesForTeacher(int teacherId) throws TeacherNotFoundException;
	List<Enrollment> generateEnrollmentReport(String courseName);
}
