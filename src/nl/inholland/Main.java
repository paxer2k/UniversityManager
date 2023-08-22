package nl.inholland;

import nl.inholland.classes.Person;
import nl.inholland.enums.AccessLevel;
import nl.inholland.services.PersonService;

import java.util.*;

public class Main {

    Scanner scanner = new Scanner(System.in);
    PersonService personService = new PersonService(); // globally called only ONCE

    public static void main(String[] args) {
        Main myMain = new Main();
        myMain.start();
    }

    void start() {

        Person person = handleLoginAndSignUp();

        if (person.getAccessLevel() == AccessLevel.BASIC) {
            performStudentTasks();
        }

        if (person.getAccessLevel() == AccessLevel.EDITOR) {
            performTeacherTasks();
        }

        if (person.getAccessLevel() == AccessLevel.ADMIN) {
            performManagerTasks();
        }
    }

    private void performTasks(List<String> validOptions, AccessLevel accessLevel) {
        while (true) {
            System.out.println(getMenuOptions(accessLevel));
            System.out.print("Please enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            if (!validOptions.contains(choice)) {
                System.out.println("Invalid choice, please try again.\n");
                continue;
            }

            if (choice.equals("X")) {
                System.out.println("Leaving the program now ...");
                break;
            }

            if (accessLevel == AccessLevel.BASIC || accessLevel == AccessLevel.EDITOR || accessLevel == AccessLevel.ADMIN) {
                if (choice.equals("S"))
                    personService.displayStudents(); // for everyone

                if (choice.equals("T"))
                    personService.displayTeachers(); // for everyone
            }

            if (accessLevel == AccessLevel.EDITOR || accessLevel == AccessLevel.ADMIN) { // for teachers+
                if (choice.equals("A"))
                    personService.addStudent();

                if (choice.equals("R")) {
                    personService.displayReports();
                    System.out.println();
                    personService.displaySpecificReport();
                }
            }

            if (accessLevel == AccessLevel.ADMIN) { // for managers+
                if (choice.equals("V")) {
                    personService.saveReports();
                }
            }

            System.out.println();
        }
    }

    private void performStudentTasks() {
        List<String> validOptions = new ArrayList<>(Arrays.asList("S", "T", "X"));
        performTasks(validOptions, AccessLevel.BASIC);
    }

    private void performTeacherTasks() {
        List<String> validOptions = new ArrayList<>(Arrays.asList("S", "T", "A", "R", "X"));
        performTasks(validOptions, AccessLevel.EDITOR);
    }

    private void performManagerTasks() {
        List<String> validOptions = new ArrayList<>(Arrays.asList("S", "T", "A", "R", "V", "X"));
        performTasks(validOptions, AccessLevel.ADMIN);
    }

    private String getMenuOptions(AccessLevel accessLevel) {
        if (accessLevel == AccessLevel.BASIC)
            return "S. Display Students | T. Display Teachers | X. Exit |\n";
        if (accessLevel == AccessLevel.EDITOR)
            return "S. Display Students | T. Display Teachers | A. Add Students | R. Display Reports | X. Exit |\n";
        if (accessLevel == AccessLevel.ADMIN)
            return "S. Display Students | T. Display Teachers | A. Add Students | R. Display Reports | V. Save all reports | X. Exit |\n";

        return "";
    }


    // sign-up and login related things

    private Person handleLoginAndSignUp() {
        Person person = null;
        while (true) {
            System.out.print("Would you like to login or sign-up? (login/signup): ");
            String choice = scanner.nextLine().toLowerCase();

            if (!choice.equals("login") && !choice.equals("signup")) {
                System.out.println("Please enter a proper option..");
                continue;
            }

            if (choice.equals("login")) {
                person = getUserFromLogin();
                break;
            }

            if (choice.equals("signup")) {
                personService.signup();
                System.out.println("You have successfully signed up!");
            }
        }
        return person;
    }


    private Person getUserFromLogin() {
        Person person = null;
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            person = personService.getLoggedInUser(username, password); // fill object here (so it can be used later)

            if (person != null)
                break;

            System.out.println("Wrong credentials, please try again!");
            System.out.println("");
        } // login process (end)
        return person;
    }
}