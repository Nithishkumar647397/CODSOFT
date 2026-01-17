import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter number of subjects: ");
        int subjects = scanner.nextInt();

        // Defensive check
        if (subjects <= 0) {
            System.out.println("Number of subjects must be greater than zero.");
            scanner.close();
            return;
        }

        int totalMarks = 0;

        for (int i = 1; i <= subjects; i++) {
            System.out.print("Enter marks for subject " + i + " (out of 100): ");
            int marks = scanner.nextInt();

            // Input validation
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks! Please enter between 0 and 100.");
                i--; // repeat this subject
                continue;
            }

            totalMarks += marks;
        }

        double averagePercentage = (double) totalMarks / subjects;

        String grade;
        String performance;

        if (averagePercentage >= 90) {
            grade = "O";
            performance = "Outstanding";
        } else if (averagePercentage >= 80) {
            grade = "A+";
            performance = "Excellent";
        } else if (averagePercentage >= 70) {
            grade = "A";
            performance = "Good";
        } else if (averagePercentage >= 60) {
            grade = "B+";
            performance = "Average";
        } else if (averagePercentage >= 50) {
            grade = "B";
            performance = "Below Average";
        } else {
            grade = "F";
            performance = "Fail";
        }

        System.out.println("\n----- Result -----");
        System.out.println("Student Name: " + studentName);
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
        System.out.println("Performance: " + performance);

        scanner.close();
    }
}
