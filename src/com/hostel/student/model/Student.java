package com.hostel.student.model;

/**
 * Student model class representing a registered college hostel resident.
 * 
 * Demonstrates:
 * 1. INHERITANCE: Extends Person class, inheriting personal fields and methods.
 * 2. POLYMORPHISM: Overrides displayDetails() to provide student-specific formatting.
 * 3. ENCAPSULATION: Encapsulates course information with getters and setters.
 */
public class Student extends Person {
    private String course;

    /**
     * Default constructor.
     */
    public Student() {
        super();
    }

    /**
     * Parameterized constructor for Student.
     * 
     * @param id Unique Student ID (e.g., STU101)
     * @param name Student's full name
     * @param age Student's age (must be positive, e.g., 16-60)
     * @param gender Student's gender
     * @param contactNumber 10-digit mobile number
     * @param email Valid email address
     * @param course Enrolled academic course (e.g., B.Tech CSE, MBA, MCA)
     */
    public Student(String id, String name, int age, String gender, String contactNumber, String email, String course) {
        super(id, name, age, gender, contactNumber, email);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Polymorphic implementation of displayDetails() from Person.
     */
    @Override
    public void displayDetails() {
        System.out.println("==================================================");
        System.out.println("               STUDENT DETAILS CARD               ");
        System.out.println("==================================================");
        System.out.printf(" %-16s : %s%n", "Student ID", getId());
        System.out.printf(" %-16s : %s%n", "Full Name", getName());
        System.out.printf(" %-16s : %d%n", "Age", getAge());
        System.out.printf(" %-16s : %s%n", "Gender", getGender());
        System.out.printf(" %-16s : %s%n", "Contact Number", getContactNumber());
        System.out.printf(" %-16s : %s%n", "Email Address", getEmail());
        System.out.printf(" %-16s : %s%n", "Academic Course", getCourse());
        System.out.println("==================================================");
    }

    /**
     * Helper method to render a standardized tabular row for multi-student views.
     */
    public String toTableRow() {
        return String.format("| %-10s | %-20s | %-4d | %-8s | %-12s | %-25s | %-15s |",
                getId(),
                truncate(getName(), 20),
                getAge(),
                truncate(getGender(), 8),
                truncate(getContactNumber(), 12),
                truncate(getEmail(), 25),
                truncate(getCourse(), 15));
    }

    private String truncate(String value, int maxLength) {
        if (value == null) return "-";
        if (value.length() <= maxLength) return value;
        return value.substring(0, maxLength - 2) + "..";
    }

    @Override
    public String toString() {
        return super.toString() + ", Course: " + course;
    }
}
