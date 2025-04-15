package main;

import dao.ISisdbImpl;
import entity.Course;
import entity.Enrollment;
import entity.Payment;
import entity.Student;
import entity.Teacher;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {
        ISisdbImpl sisdb = new ISisdbImpl();
        Scanner sc = new Scanner(System.in);


    while (true) {
        System.out.println("\nChoose a task to perform:");
        System.out.println("1 - Add Student, Course, Enrollment");
        System.out.println("2 - Add Teacher and Assign Course");
        System.out.println("3 - Record Payment for Student");
        System.out.println("4 - Generate Enrollment Report");
        System.out.println("5 - Exit");
        System.out.print("Eneter you choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
        case 1:
            System.out.println("Enter Student ID:");
            int studentId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter First Name:");
            String fname = sc.nextLine();
            System.out.println("Enter Last Name:");
            String lname = sc.nextLine();
            System.out.println("Enter DOB (yyyy-mm-dd):");
            String dob = sc.nextLine();
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Phone:");
            String phone = sc.nextLine();

            Student student = new Student(studentId, fname, lname, dob, email, phone);
            sisdb.addStudent(student);

            // Loop to add two courses
            for (int i = 1; i <= 2; i++) {
                System.out.println("Enter Course ID " + i + ":");
                String cid = sc.nextLine();
                System.out.println("Enter Course Name " + i + ":");
                String cname = sc.nextLine();
                System.out.println("Enter Credits " + i + ":");
                int credits = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Teacher ID " + i + ":");
                int tid = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Course Code " + i + ":");
                String code = sc.nextLine();

                Course course = new Course(cid, cname, credits, tid, code);
                sisdb.addCourse(course);

                System.out.println("Enter Enrollment ID for course " + i + ":");
                String eid = sc.nextLine();
                System.out.println("Enter Enrollment Date for course " + i + " (yyyy-mm-dd):");
                Date edate = Date.valueOf(sc.nextLine());

                Enrollment enrollment = new Enrollment(eid, student, course, edate);
                sisdb.addEnrollment(enrollment);
            }

            System.out.println("Task 8 completed successfully...");
            break;


            case 2:
                System.out.println("Enter Teacher ID:");
                int tid = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter First Name:");
                String tfname = sc.nextLine();
                System.out.println("Enter Last Name:");
                String tlname = sc.nextLine();
                System.out.println("Enter Email:");
                String temail = sc.nextLine();

                Teacher teacher = new Teacher(tid, tfname, tlname, temail);
                sisdb.addTeacher(teacher);

                System.out.println("Enter New Course ID:");
                String cid2 = sc.nextLine();
                System.out.println("Enter Course Name:");
                String cname2 = sc.nextLine();
                System.out.println("Enter Credits:");
                int credits2 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Teacher ID:");
                int tid2 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Course Code:");
                String code2 = sc.nextLine();

                Course course2 = new Course(cid2, cname2, credits2, tid2, code2);
                sisdb.addCourse(course2);
                sisdb.assignCourseToTeacher(course2, teacher);

                System.out.println(teacher.getFirstName() + " assigned to " + course2.getCourseName() + " successfully.");
                System.out.println("Task 9 completed successfully...");
                break;

            case 3:
                System.out.println("Enter Student ID:");
                int pid = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter First Name:");
                String pfname = sc.nextLine();
                System.out.println("Enter Last Name:");
                String plname = sc.nextLine();
                System.out.println("Enter DOB (yyyy-mm-dd):");
                String pdob = sc.nextLine();
                System.out.println("Enter Email:");
                String pemail = sc.nextLine();
                System.out.println("Enter Phone:");
                String pphone = sc.nextLine();

                Student pstudent = new Student(pid, pfname, plname, pdob, pemail, pphone);
                sisdb.addStudent(pstudent);

                System.out.println("Enter Payment ID:");
                int payId = sc.nextInt();
                System.out.println("Enter Amount:");
                double amount = sc.nextDouble();
                sc.nextLine();
                System.out.println("Enter Payment Date (yyyy-mm-dd):");
                Date payDate = Date.valueOf(sc.nextLine());

                Payment payment = new Payment(payId, pstudent, amount, payDate);
                sisdb.addPayment(payment);

                System.out.println("Payment of $" + amount + " recorded for " + pfname + " " + plname + " successfully...");
                System.out.println("Task 10 completed successfully...");
                break;

            case 4:
                System.out.println("Enter Course Name for Enrollment Report:");
                String targetCourse = sc.nextLine();
                List<Enrollment> csEnrollments = sisdb.generateEnrollmentReport(targetCourse);

                System.out.println("\nEnrollment Report for: " + targetCourse);
                for (Enrollment enrollment : csEnrollments) {
                    Student s = enrollment.getStudent();
                    System.out.println("Student: " + s.getFirstName() + " " + s.getLastName() +
                            "--Enrollment Date: " + enrollment.getEnrollmentDate());
                }

                System.out.println("Task 11 completed successfully...");
                break;
            case 5:
                System.out.println("Exiting program..");
                System.exit(0);

            default:
                System.out.println("Invalid choice. Please select a valid task number.");
        }
       
    }
    }
}

