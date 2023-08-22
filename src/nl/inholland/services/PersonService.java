package nl.inholland.services;

import nl.inholland.classes.Person;
import nl.inholland.database.Database;

import java.util.List;

public class PersonService {
    private Database db;

    public PersonService() {
        db = new Database();
    }

    public List<Person> getPeople() {
        return db.getPeople();
    }

    public Person getLoggedInUser(String username, String password) {
        return db.getLoggedInUser(username, password);
    }

    public void signup() {
        db.signup();
    }

    public void displayStudents() {
        db.displayStudents();
    }

    public void displayTeachers() {
        db.displayTeachers();
    }

    public void addStudent() {
        db.addStudent();
    }

    public void displayReports() {
        db.displayReports();
    }

    public void displaySpecificReport() {
        db.displaySpecificReport();
    }

    public void saveReports() {
        db.saveReports();
    }
}
