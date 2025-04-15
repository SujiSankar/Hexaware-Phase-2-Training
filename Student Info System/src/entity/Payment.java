package entity;

import java.util.Date;

public class Payment {
    private int paymentId;  
    private Student student; 
    private double amount;
    private Date paymentDate;

    // Default constructor
    public Payment() {}
    
    public Payment(int paymentId, Student student, double amount, Date paymentDate) {
        this.paymentId = paymentId;
        this.student = student;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }


    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", student=" + (student != null ? student.getFirstName() + " " + student.getLastName() : "Unknown") +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
