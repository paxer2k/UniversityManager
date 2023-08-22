package nl.inholland.database;

import nl.inholland.classes.*;
import nl.inholland.enums.Subject;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private static List<Person> people = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public Database() {

        people.add(new Student("emma", "emma123", "Emma", "Smith", LocalDate.of(1997, 12, 4), "IT-02-A",
                List.of(new Report(Subject.JAVA, 54), new Report(Subject.CSHARP, 50), new Report(Subject.PYTHON, 66), new Report(Subject.PHP, 54))));

        people.add(new Student("jack", "jack123", "Jack", "Brown", LocalDate.of(1993, 8, 7), "IF-02-A",
                List.of(new Report(Subject.JAVA, 72), new Report(Subject.CSHARP, 68), new Report(Subject.PYTHON, 43), new Report(Subject.PHP, 95))));

        people.add(new Student("michael", "michael123", "Michael", "Garcia", LocalDate.of(1999, 11, 1), "IF-02-A",
                List.of(new Report(Subject.JAVA, 45), new Report(Subject.CSHARP, 71), new Report(Subject.PYTHON, 55), new Report(Subject.PHP, 84))));

        people.add(new Student("lisa", "lisa123", "Lisa", "Jones", LocalDate.of(2000, 4, 29), "IF-02-A",
                List.of(new Report(Subject.JAVA, 98), new Report(Subject.CSHARP, 64), new Report(Subject.PYTHON, 81), new Report(Subject.PHP, 72))));

        people.add(new Student("john", "john123", "John", "Miller", LocalDate.of(2001, 10, 27), "IF-02-A",
                List.of(new Report(Subject.JAVA, 100), new Report(Subject.CSHARP, 94), new Report(Subject.PYTHON, 99), new Report(Subject.PHP, 93))));

        people.add(new Student("linda", "linda123", "Linda", "Martinez", LocalDate.of(2002, 1, 17), "IF-02-A",
                List.of(new Report(Subject.JAVA, 55), new Report(Subject.CSHARP, 79), new Report(Subject.PYTHON, 81), new Report(Subject.PHP, 55))));

        people.add(new Student("richard", "richard123", "Richard", "Davis", LocalDate.of(1997, 9, 22), "IF-02-A",
                List.of(new Report(Subject.JAVA, 51), new Report(Subject.CSHARP, 64), new Report(Subject.PYTHON, 39), new Report(Subject.PHP, 59))));

        people.add(new Student("mark", "mark123", "Mark", "Lopez", LocalDate.of(1996, 12, 9), "IF-02-A",
                List.of(new Report(Subject.JAVA, 78), new Report(Subject.CSHARP, 98), new Report(Subject.PYTHON, 89), new Report(Subject.PHP, 99))));

        people.add(new Student("debora", "debora123", "Debora", "Hernandez", LocalDate.of(1995, 2, 25), "IF-02-A",
                List.of(new Report(Subject.JAVA, 59), new Report(Subject.CSHARP, 55), new Report(Subject.PYTHON, 67), new Report(Subject.PHP, 0))));

        people.add(new Student("rick", "rick123", "Rick", "Moore", LocalDate.of(2000, 3, 16), "IF-02-A",
                List.of(new Report(Subject.JAVA, 96), new Report(Subject.CSHARP, 87), new Report(Subject.PYTHON, 55), new Report(Subject.PHP, 82))));

        // teachers
        people.add(new Teacher("david", "david123", "David", "Taylor", LocalDate.of(1965, 6, 15)));
        people.add(new Teacher("sophy", "sophy123", "Sophy", "Anderson", LocalDate.of(1987, 1, 6)));
        people.add(new Teacher("james", "james123", "James", "Jordon", LocalDate.of(1956, 3, 19)));
        people.add(new Teacher("susan", "susan123", "Susan", "Jackson", LocalDate.of(1978, 12, 25)));
        people.add(new Teacher("mary", "mary123", "Mary", "Lee", LocalDate.of(1971, 9, 4)));

        people.add(new Manager("peter", "peter123", "Peter", "Van Diermen", LocalDate.of(1960, 5, 13)));
    }

    public Person getLoggedInUser(String username, String password) {
        for (Person person : people) {
            if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
                return person;
            }
        }
        return null;
    }

    public void signup() {
        String username = getInput("Enter your username: ");
        String password = getInput("Enter your password: ");
        String firstName = getInput("Enter your first name: ");
        String lastName = getInput("Enter your last name: ");
        LocalDate dateOfBirth = parseDateOfBirth();

        Person p = new Person(username, password, firstName, lastName, dateOfBirth);

        people.add(p);
    }

    public List<Person> getPeople() {
        return people;
    }

    public Student getStudentById(int id) {
        for (Person p : people) {
            if (p.getId() == id && p instanceof Student) {
                return (Student) p;
            }
        }
        return null;
    }

    public void displayStudents() {
        System.out.println("");
        System.out.println("LIST OF STUDENTS");
        System.out.println("");

        System.out.println("Id     FirstName     LastName     Birthdate     Age     Group");
        System.out.println("**     *********     ********     *********     ***     *****");

        for (Person p : people) {
            if (p instanceof Student) {
                System.out.println(p);
            }
        }
    }

    public void displayTeachers() {
        System.out.println("");
        System.out.println("LIST OF TEACHERS");
        System.out.println("");

        System.out.println("Id     FirstName     LastName     Birthdate     Age");
        System.out.println("**     *********     ********     *********     ***");

        for (Person p : people) {
            if (p instanceof Teacher) {
                System.out.println(p);
            }
        }
    }

    public void addStudent() {
        System.out.println("");
        System.out.println("ADD STUDENTS");
        System.out.println("");

        String username = getInput("Choose a username: ");
        String password = getInput("Choose a password: ");
        String firstName = getInput("Enter first name: ");
        String lastName = getInput("Enter last name: ");
        LocalDate dateOfBirth = parseDateOfBirth(); // check below for method
        String group = getInput("Enter group: ");

        // creating a list of grades with grade 0
        List<Report> grades = new ArrayList<>();
        grades.add(new Report(Subject.JAVA, 0));
        grades.add(new Report(Subject.CSHARP, 0));
        grades.add(new Report(Subject.PYTHON, 0));
        grades.add(new Report(Subject.PHP, 0));

        Student s = new Student(username, password, firstName, lastName, dateOfBirth, group, grades);

        people.add(s);

        System.out.println("The data was successfully added!");
    }

    public void displayReports() {
        System.out.println("");
        System.out.println("STUDENT RESULTS");
        System.out.println("");

        System.out.printf("%-6s %-13s %-12s %-13s %-7s %-9s", "Id", "FirstName", "LastName", "Birthdate", "Age", "Group");

        for (Subject subject : Subject.values()) {
            System.out.printf("%-10s ", subject);
        }

        System.out.println("\n**     *********     ********     *********     ***     *****     ****     ******     ******     ***");

        for (Person p : people) {
            if (p instanceof Student) {
                Student student = (Student) p;
                List<Report> reports = student.getReports();

                System.out.printf("%-6s %-13s %-12s %-13s %-7d %-9s", student.getId(), student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getAge(), student.getGroup());
                for (Report report : reports)
                    System.out.printf("%-10d", report.getScore());

                System.out.println();
            }
        }
    }

    public void displaySpecificReport() {
        Student s = null;
        while (true) {
            String input = getInput("Enter student ID (Report Details) | Or 0 to go back to the main menu: ");

            if (!isValidInteger(input)) {
                System.out.println("Invalid input. Please enter a valid student ID.");
                continue;
            }

            int value = Integer.parseInt(input);

            if (value == 0) {
                return; // Exit the loop and return to the main menu
            }

            s = getStudentById(value);

            if (s == null)
                System.out.println("The user with this ID does NOT exist. Please try again...");
            else
                break;
        }

        getStudentReportInformation(s);

        System.out.println();

        System.out.println("");

        while (true) {
            System.out.println("A. Add (Update) Report | R. Display Reports | B. Back to menu | X. Exit");
            String choice = getInput("Please enter a choice: ");

            choice = choice.toUpperCase(); // so no case sensitivity

            if (choice.equals("X")) {
                System.out.println("Leaving the program now ...");
                System.exit(120);
            }

            if (choice.equals("B")) {
                return;
            }

            if (choice.equals("R")) {
                displayReports();
                displaySpecificReport();
                return;
            }

            if (choice.equals("A")) {
                updateReport(s);
            }
        }
    }

    public void saveReports() {
        String directoryPath = "C:/test/";
        for (Person p : people) {
            if (p instanceof Student) {
                Student s = (Student)p;
                try {
                    String filename = String.format("%d %s %s.rtf", s.getId(), s.getFirstName(), s.getLastName());
                    String filePath = directoryPath + filename; // the / is already done in directory path

                    PrintStream originalOutput = System.out;
                    PrintStream fileOut = new PrintStream(new FileOutputStream(filePath));
                    System.setOut(fileOut);

                    getStudentReportInformation(s);

                    // reset standard output
                    System.out.flush();
                    System.setOut(originalOutput);

                    System.out.println("Reports saved to " + filePath);
                    System.out.println();
                }
                catch(FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getStudentReportInformation(Student s) {
        System.out.printf("Report of student %s", s.getFullName());

        System.out.println();

        System.out.printf("\nStudent Id   ................   %d", s.getId());
        System.out.printf("\nFirst Name   ................   %s", s.getFirstName());
        System.out.printf("\nLast Name   ................   %s", s.getLastName());
        System.out.printf("\n Birth Date   ................ %s", s.getDateOfBirth().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        System.out.printf("\nAge   ................   %d", s.getAge());
        System.out.printf("\nGroup   ................   %s", s.getGroup());

        System.out.println();
        System.out.println("\nCOURSES");

        for (Report r : s.getReports()) {
            System.out.printf("\n%s   ................   %d", r.getSubject().toString(), r.getScore());
        }

        System.out.println();
        System.out.println("\nCOURSES");

        int nrOfRetakes = 0;
        for (Report r : s.getReports()) {
            if (r.getScore() < 55) {
                nrOfRetakes++;
            }
        }

        boolean hasPassed = nrOfRetakes <= 0;

        System.out.printf("\nResult   ................   %s", hasPassed ? "Passed" : "Not Passed");
        System.out.printf("\nRetakes   ................  %d", nrOfRetakes);
    }


    // internal class functions

    private void updateReport(Student s) {
        int javaGrade = getIntInput("Enter new Java grade: ");
        int csharpGrade = getIntInput("Enter new CSharp grade: ");
        int pythonGrade = getIntInput("Enter new Python grade: ");
        int phpGrade = getIntInput("Enter new PHP: ");

        List<Integer> newGrades = new ArrayList<>();
        newGrades.add((javaGrade));
        newGrades.add(csharpGrade);
        newGrades.add(pythonGrade);
        newGrades.add(phpGrade);

        List<Report> reports = s.getReports();

        // foreach grade entered replace the current grade
        for(int i = 0; i < reports.size(); i++) {
            reports.get(i).setScore(newGrades.get(i));
        }

        System.out.println("The data was successfully updated!");

        System.out.println();
    }

    private boolean isValidInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            System.out.println("This is not a number, try again");
            return false;
        }
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();

        while (input.isBlank() || input.isEmpty()) {
            System.out.println("Value cannot be empty, please try again.");
            System.out.print(prompt);
            input = scanner.nextLine();
        }

        while (!isValidInteger(input)) {
            System.out.print(prompt);

            input = scanner.nextLine();
        }

        return Integer.parseInt(input);
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();

        while (input.isEmpty() || input.isBlank()) {
            System.out.println("Value cannot be empty, please try again.");

            System.out.print(prompt);

            input = scanner.nextLine();
        }

        return input;
    }

    private LocalDate parseDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateOfBirth = null;

        while (dateOfBirth == null) {
            System.out.print("Enter date of birth in MM/DD/YYYY: ");
            String dateOfBirthString = scanner.nextLine();

            try {
                dateOfBirth = LocalDate.parse(dateOfBirthString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing date of birth. Please use the format MM/DD/YYYY.");
            }
        }
        return dateOfBirth;
    }


}
