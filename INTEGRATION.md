# Integration Specification & Teammate Compatibility Guide

> **Module:** Student Management Module  
> **Universal Identifier:** `studentId` (String, uppercase alphanumeric, e.g., `"STU101"`, `"STU1001"`)  
> **Target Audience:** Room Management, Fee Management, and Complaint Management developers.

---

## 1. Student Entity Data Dictionary

When your module references a student, here is the exact data contract defined in `com.hostel.student.model.Student`:

| Field Name | Data Type | Modifiable | Description / Constraints | Example |
|---|---|---|---|---|
| `id` | `String` | No (Immutable Key) | Unique student key (3-15 chars, alphanumeric) | `"STU101"` |
| `name` | `String` | No | Student's full legal name | `"Aarav Sharma"` |
| `age` | `int` | Yes | Resident age (16 - 60) | `20` |
| `gender` | `String` | No | `"Male"`, `"Female"`, or `"Other"` | `"Male"` |
| `contactNumber` | `String` | Yes | 10-digit mobile number | `"9876543210"` |
| `email` | `String` | Yes | Valid email address | `"aarav@hostel.edu"` |
| `course` | `String` | Yes | Enrolled college program | `"B.Tech CSE"` |

---

## 2. Public Service Contract (`StudentService`)

All modules interact with the student module through the `StudentService` interface (`com.hostel.student.service.StudentService`):

```java
package com.hostel.student.service;

import com.hostel.student.model.Student;
import com.hostel.student.exception.*;
import java.util.List;

public interface StudentService {
    // 1. Check if a student ID is registered (Fast O(1) check)
    boolean existsById(String studentId);

    // 2. Fetch full student details (Throws StudentNotFoundException if invalid)
    Student getStudentById(String studentId) throws StudentNotFoundException;

    // 3. Get all registered students
    List<Student> getAllStudents();

    // 4. Get active count of registered students
    int getTotalStudentCount();
}
```

---

## 3. How Teammates Integrate With This Module

### A. Room Management Integration
- **Concept:** A room holds beds allocated to specific students.
- **Contract Rule:** Before allocating a bed, Room Management must verify that the student is valid and registered:
  ```java
  // Example compatible pattern for Room Management:
  if (!studentService.existsById(targetStudentId)) {
      throw new IllegalArgumentException("Cannot allocate room: Student ID " + targetStudentId + " does not exist.");
  }
  // Proceed with bed allocation...
  ```

### B. Fee Management Integration
- **Concept:** Fee transactions and dues are linked to a student.
- **Contract Rule:** When generating fee receipts or recording dues, query the student service to retrieve the student's name, course, and email for the receipt:
  ```java
  // Example compatible pattern for Fee Management:
  Student resident = studentService.getStudentById(targetStudentId);
  System.out.println("Receipt for: " + resident.getName() + " | Course: " + resident.getCourse());
  ```

### C. Complaint Management Integration
- **Concept:** Grievances lodged by hostel residents.
- **Contract Rule:** When filing a complaint, validate the complainant's `studentId` and fetch contact information if urgent contact is needed:
  ```java
  // Example compatible pattern for Complaint Management:
  if (!studentService.existsById(studentId)) {
      System.out.println("Invalid student ID. Complaint cannot be filed by an unregistered resident.");
      return;
  }
  ```

---

## 4. How to Create a Genuine Pull Request (PR) in a Teammate's Repository

To complete the collaborative GitHub requirement without copying or fabricating code:

1. **Step 1: Obtain your teammate's repository URL** (e.g., their Room Management or Fee Management repo).
2. **Step 2: Fork or clone their repository:**
   ```bash
   git clone https://github.com/<teammate-username>/<teammate-repo>.git
   cd <teammate-repo>
   ```
3. **Step 3: Create a dedicated feature branch:**
   ```bash
   git checkout -b feature/integrate-student-id-validation
   ```
4. **Step 4: Add an integration helper or compatibility interface:**
   Add a reference interface or validation check showing how their module connects with your `StudentService.existsById(studentId)`.
5. **Step 5: Commit and push your branch:**
   ```bash
   git add .
   git commit -m "feat(integration): add student ID existence validation hook for hostel residents"
   git push origin feature/integrate-student-id-validation
   ```
6. **Step 6: Open the Pull Request on GitHub:**
   - Title: `feat: Add Student ID validation hook for Room/Fee allocation`
   - Description:
     > *"This PR introduces compatibility with the Student Management Module. It provides a validation hook so your module can verify that a student exists in hostel records before processing transactions or room allocations."*

---

## 5. Demonstration & Viva Guide for Examiners

### Question 1: Where is OOP demonstrated in your module?
- **Abstraction:** 
  - `Person.java` is an abstract base class defining core human attributes and an abstract method `displayDetails()`.
  - `StudentService.java` is an interface providing a clean contract hiding HashMap and custom Linked List implementations.
- **Inheritance:** `Student extends Person` inherits personal fields (`id`, `name`, `age`, `contactNumber`, `email`) and adds academic fields (`course`).
- **Polymorphism:** `Student` overrides `displayDetails()` to print a custom card, and overrides `toString()`. `StudentServiceImpl` implements `StudentService`.
- **Encapsulation:** Private attributes with strict validation in getters/setters and `ValidationUtil`.

### Question 2: What Custom Exceptions did you build?
- `StudentNotFoundException`: Thrown when searching, updating, or deleting a student ID that does not exist.
- `DuplicateStudentException`: Thrown when an ID collision is detected during registration.
- `InvalidStudentDataException`: Thrown when phone number isn't 10 digits, email is malformed, or age is out of bounds (16-60).

### Question 3: What DSA did you implement manually and why?
- **Manual DSA:** Handcrafted Singly Linked List (`StudentLinkedList.java`) using `StudentNode.java`.
- **Mechanics:** 
  - Pointer manipulation using `head`, `tail`, and `next` references.
  - $O(1)$ append insertion to tail.
  - $O(n)$ linear traversal search.
  - $O(n)$ pointer-relinking deletion (`prev.setNext(current.getNext())`).
- **Synergy with Java Collections:**
  - `HashMap<String, Student>` is used for $O(1)$ constant-time lookup indexing.
  - `StudentLinkedList` preserves chronological registration logs and shows low-level memory pointer management.
