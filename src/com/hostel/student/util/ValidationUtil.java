package com.hostel.student.util;

import com.hostel.student.exception.InvalidStudentDataException;
import java.util.regex.Pattern;

/**
 * Utility class providing robust validation methods for student records.
 * Ensures data integrity before any entity creation or update.
 */
public final class ValidationUtil {

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^[0-9]{10}$");

    private static final Pattern NAME_PATTERN = 
        Pattern.compile("^[A-Za-z\\s.]{2,50}$");

    private static final Pattern ID_PATTERN = 
        Pattern.compile("^[A-Za-z0-9_-]{3,15}$");

    private ValidationUtil() {
        // Private constructor to prevent instantiation of utility class
    }

    /**
     * Validates student ID format.
     */
    public static void validateStudentId(String id) throws InvalidStudentDataException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidStudentDataException("Student ID cannot be empty.");
        }
        if (!ID_PATTERN.matcher(id.trim()).matches()) {
            throw new InvalidStudentDataException("Invalid Student ID format. Use 3-15 alphanumeric characters (e.g., STU101).");
        }
    }

    /**
     * Validates student name.
     */
    public static void validateName(String name) throws InvalidStudentDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidStudentDataException("Student name cannot be empty.");
        }
        if (!NAME_PATTERN.matcher(name.trim()).matches()) {
            throw new InvalidStudentDataException("Invalid name. Name must contain only alphabets and spaces (2-50 characters).");
        }
    }

    /**
     * Validates student age.
     */
    public static void validateAge(int age) throws InvalidStudentDataException {
        if (age < 16 || age > 60) {
            throw new InvalidStudentDataException("Invalid age: " + age + ". Age must be between 16 and 60 for college hostel admission.");
        }
    }

    /**
     * Validates student gender.
     */
    public static void validateGender(String gender) throws InvalidStudentDataException {
        if (gender == null || gender.trim().isEmpty()) {
            throw new InvalidStudentDataException("Gender cannot be empty.");
        }
        String g = gender.trim().toUpperCase();
        if (!g.equals("MALE") && !g.equals("FEMALE") && !g.equals("OTHER")) {
            throw new InvalidStudentDataException("Invalid gender. Please specify Male, Female, or Other.");
        }
    }

    /**
     * Validates student contact number (10 digits).
     */
    public static void validateContactNumber(String contactNumber) throws InvalidStudentDataException {
        if (contactNumber == null || contactNumber.trim().isEmpty()) {
            throw new InvalidStudentDataException("Contact number cannot be empty.");
        }
        String cleanNumber = contactNumber.trim().replaceAll("[\\s-]", "");
        if (!PHONE_PATTERN.matcher(cleanNumber).matches()) {
            throw new InvalidStudentDataException("Invalid contact number: '" + contactNumber + "'. Must be exactly 10 digits.");
        }
    }

    /**
     * Validates student email address.
     */
    public static void validateEmail(String email) throws InvalidStudentDataException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidStudentDataException("Email address cannot be empty.");
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new InvalidStudentDataException("Invalid email format: '" + email + "'. Example: student@college.edu");
        }
    }

    /**
     * Validates student academic course.
     */
    public static void validateCourse(String course) throws InvalidStudentDataException {
        if (course == null || course.trim().isEmpty()) {
            throw new InvalidStudentDataException("Course name cannot be empty.");
        }
        if (course.trim().length() < 2 || course.trim().length() > 50) {
            throw new InvalidStudentDataException("Course name must be between 2 and 50 characters.");
        }
    }

    /**
     * Validates all student fields at once.
     */
    public static void validateAll(String id, String name, int age, String gender, 
                                  String contactNumber, String email, String course) 
            throws InvalidStudentDataException {
        validateStudentId(id);
        validateName(name);
        validateAge(age);
        validateGender(gender);
        validateContactNumber(contactNumber);
        validateEmail(email);
        validateCourse(course);
    }
}
