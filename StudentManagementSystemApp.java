import java.util.*;
import java.io.*;

/* =========================
   Student Class
   ========================= */
class Student {
    private String name;
    private String rollNumber;
    private String grade;
    private String department;

    public Student(String name, String rollNumber, String grade, String department) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.department = department;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String toFileString() {
        return name + "," + rollNumber + "," + grade + "," + department;
    }

    @Override
    public String toString() {
        return "Name: " + name +
               " | Roll No: " + rollNumber +
               " | Grade: " + grade +
               " | Department: " + department;
    }
}

/* =========================
   Student Management System
   ========================= */
class StudentManagementSystem {
    private ArrayList<Student> students;
    private final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    /* Add Student */
    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getRollNumber().equals(student.getRollNumber())) {
                System.out.println("Student with this roll number already exists.");
                return;
            }
        }
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully.");
    }

    /* Remove Student */
    public void removeStudent(String rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber().equals(rollNumber)) {
                iterator.remove();
                saveToFile();
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    /* Search Student */
    public Student searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    /* Display All Students */
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /* Save to File */
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }

    /* Load from File */
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    students.add(new Student(
                        data[0], data[1], data[2], data[3]
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data.");
        }
    }
}

/* =========================
   Main Class (UI)
   ========================= */
public class StudentManagementSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Roll Number: ");
                    String roll = scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();

                    if (name.isEmpty() || roll.isEmpty() || grade.isEmpty() || dept.isEmpty()) {
                        System.out.println("All fields are required.");
                    } else {
                        sms.addStudent(new Student(name, roll, grade, dept));
                    }
                    break;

                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    sms.removeStudent(scanner.nextLine());
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    Student s = sms.searchStudent(scanner.nextLine());
                    if (s != null) {
                        System.out.println(s);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter Roll Number to edit: ");
                    String r = scanner.nextLine();
                    Student st = sms.searchStudent(r);
                    if (st == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("Enter New Name: ");
                    st.setName(scanner.nextLine());
                    System.out.print("Enter New Grade: ");
                    st.setGrade(scanner.nextLine());
                    System.out.print("Enter New Department: ");
                    st.setDepartment(scanner.nextLine());

                    System.out.println("Student updated successfully.");
                    break;

                case 6:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
