package nl.inholland.classes;

import nl.inholland.enums.AccessLevel;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    private int id;
    private static int nextId = 1; // this is a placeholder so that new id gets created after each object initialization
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    protected AccessLevel accessLevel; // important to be protected

    public Person(String username, String password, String firstName, String lastName, LocalDate dateOfBirth) {

        this.id = nextId;
        nextId++;

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

        this.accessLevel = AccessLevel.BASIC; // we do not want the user to set it manually, but rather pass it via constructor when creating an object...
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) throws Exception {

        if (dateOfBirth.getDayOfYear() <= LocalDate.now().getDayOfYear())
            throw new Exception("Age has to be in the past");

        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears();
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    @Override
    public String toString() {
        return String.format("%d     %s     %s     %s     %d",
                this.getId(),
                this.getFirstName(),
                this.getLastName(),
                this.getDateOfBirth().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")),
                this.getAge());
    }
}
