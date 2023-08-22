package nl.inholland.classes;

import nl.inholland.enums.AccessLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Manager extends Person {

    public Manager(String username, String password, String firstName, String lastName, LocalDate dateOfBirth) {
        super(username, password, firstName, lastName, dateOfBirth);

        this.accessLevel = AccessLevel.ADMIN;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-13s %-12s %-12s %-3s%n",
                this.getId(),
                this.getFirstName(),
                this.getLastName(),
                this.getDateOfBirth().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                this.getAge());
    }
}
