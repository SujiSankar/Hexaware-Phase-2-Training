package dao;

import entity.Course;
import entity.Enrollment;
import entity.Payment;
import entity.Student;
import entity.Teacher;
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ISisdbImpl implements ISisdb {
    
    @Override
    public void addStudent(Student student) {
        String query = "INSERT INTO students (student_id, first_name, last_name, date_of_birth, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getDateOfBirth());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getPhoneNumber());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTeacher(Teacher teacher) {
        String query = "INSERT INTO teacher (teacher_id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, teacher.getTeacherId());
            ps.setString(2, teacher.getFirstName());
            ps.setString(3, teacher.getLastName());
            ps.setString(4, teacher.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCourse(Course course) {
        String query = "INSERT INTO courses (course_id, course_name, credits, teacher_id, course_code) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, course.getCourseId());
            ps.setString(2, course.getCourseName());
            ps.setInt(3, course.getCredits());
            ps.setInt(4, course.getTeacherId()); 
            ps.setString(5, course.getCourseCode());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addEnrollment(Enrollment enrollment) {
        String query = "INSERT INTO enrollments (enrollment_id, student_id, course_id, enrollment_date) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, enrollment.getEnrollmentId());
            ps.setInt(2, enrollment.getStudent().getStudentId());
            ps.setString(3, enrollment.getCourse().getCourseId());
            ps.setDate(4, new java.sql.Date(enrollment.getEnrollmentDate().getTime()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPayment(Payment payment) {
        String query = "INSERT INTO payments (payment_id, student_id, amount, payment_date) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, payment.getPaymentId());
            ps.setInt(2, payment.getStudent().getStudentId());
            ps.setDouble(3, payment.getAmount());
            ps.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String query = "SELECT * FROM enrollments WHERE student_id = ?";
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String enrollmentId = rs.getString("enrollment_id");
                Date enrollmentDate = rs.getDate("enrollment_date");
                Course course = getCourseById(rs.getString("course_id"));
                Student student = getStudentById(studentId);
                enrollments.add(new Enrollment(enrollmentId, student, course, enrollmentDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String courseId = rs.getString("course_id");
                String courseName = rs.getString("course_name");
                int credits = rs.getInt("credits");
                int teacher_id=rs.getInt("teacher_id");
                String courseCode = rs.getString("course_code");

                courses.add(new Course(courseId, courseName, credits,teacher_id, courseCode));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String dateOfBirth = rs.getString("date_of_birth");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");

                students.add(new Student(studentId, firstName, lastName, dateOfBirth, email, phoneNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teacher";
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int teacherId = rs.getInt("teacher_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");

                teachers.add(new Teacher(teacherId, firstName, lastName, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int paymentId = rs.getInt("payment_id");
                int studentId = rs.getInt("student_id");
                double amount = rs.getDouble("amount");
                Date paymentDate = rs.getDate("payment_date");

                Student student = getStudentById(studentId);
                payments.add(new Payment(paymentId, student, amount, paymentDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    private Course getCourseById(String courseId) {
        String query = "SELECT * FROM courses WHERE course_id = ?";
        Course course = null;
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, courseId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String courseName = rs.getString("course_name");
                int credits = rs.getInt("credits");
                int teacher_id=rs.getInt("teacher_id");
                String courseCode = rs.getString("course_code");
                course = new Course(courseId, courseName, credits, teacher_id,courseCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public void assignCourseToTeacher(Course course, Teacher teacher) {
        String query = "UPDATE courses SET teacher_id = ? WHERE course_id = ?";

        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, teacher.getTeacherId());
            ps.setString(2, course.getCourseId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Student getStudentById(int studentId) {
        String query = "SELECT * FROM students WHERE student_id = ?";
        Student student = null;
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String dateOfBirth = rs.getString("date_of_birth");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                student = new Student(studentId, firstName, lastName, dateOfBirth, email, phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        String query = "SELECT * FROM teacher WHERE teacher_id = ?";
        Teacher teacher = null;
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");

                teacher = new Teacher(teacherId, firstName, lastName, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public Course getCourseById(int courseId) {
        String query = "SELECT * FROM courses WHERE course_id = ?";
        Course course = null;
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String courseIdStr = rs.getString("course_id");
                String courseName = rs.getString("course_name");
                int credits = rs.getInt("credits");
                int teacher_id=rs.getInt("teacher_id");
                String courseCode = rs.getString("course_code");

                course = new Course(courseIdStr, courseName, credits, courseCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
    @Override
    public List<Enrollment> generateEnrollmentReport(String courseName ){
        List<Enrollment> enrollments = new ArrayList<>();
        
        
        String courseQuery = "SELECT * FROM courses WHERE course_name = ?";
        String courseId = null;
        
        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(courseQuery)) {
             
            ps.setString(1, courseName);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                courseId = rs.getString("course_id");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
       
        if (courseId != null) {
            String enrollmentQuery = "SELECT * FROM enrollments WHERE course_id = ?";
            
            try (Connection con = DBConnUtil.getDbConnection();
                 PreparedStatement ps = con.prepareStatement(enrollmentQuery)) {
                 
                ps.setString(1, courseId);
                ResultSet rs = ps.executeQuery();
                
                
                while (rs.next()) {
                    String enrollmentId = rs.getString("enrollment_id");
                    Date enrollmentDate = rs.getDate("enrollment_date");
                    int studentId = rs.getInt("student_id");
                    
                  
                    Student student = getStudentById(studentId);
                    
                    
                    Course course = getCourseById(courseId); 
                    enrollments.add(new Enrollment(enrollmentId, student, course, enrollmentDate));
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Course with the name " + courseName + " not found.");
        }
        
        return enrollments;
    }



    @Override
    public List<Course> getCoursesForTeacher(int teacherId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses WHERE teacher_id = ?";

        try (Connection con = DBConnUtil.getDbConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String courseId = rs.getString("course_id");
                String courseName = rs.getString("course_name");
                int credits = rs.getInt("credits");
                String courseCode = rs.getString("course_code");

               
                courses.add(new Course(courseId, courseName, credits, teacherId, courseCode));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

}
