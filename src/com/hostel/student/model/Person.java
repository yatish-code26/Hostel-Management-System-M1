package com.hostel.student.model;

/**
 * Abstract base class representing a Person in the hostel ecosystem.
 * 
 * Demonstrates:
 * 1. ABSTRACTION: Serves as a blueprint for human entities (e.g., Student, Staff).
 *    Cannot be instantiated directly. Declares abstract display contract.
 * 2. ENCAPSULATION: All fields are private with access controlled via getters and setters.
 */
public abstract class Person {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String contactNumber;
    private String email;

    /**
     * Default constructor.
     */
    public Person() {
    }

    /**
     * Parameterized constructor initializing personal attributes.
     * 
     * @param id Unique identifier
     * @param name Full name
     * @param age Age in years
     * @param gender Gender identity
     * @param contactNumber Primary phone/mobile number
     * @param email Contact email address
     */
    public Person(String id, String name, int age, String gender, String contactNumber, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Abstract method defining polymorphic display contract
    public abstract void displayDetails();

    // Getters and Setters demonstrating encapsulation
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + 
               ", Gender: " + gender + ", Phone: " + contactNumber + ", Email: " + email;
    }
}
