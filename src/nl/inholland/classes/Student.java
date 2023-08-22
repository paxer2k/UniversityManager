package nl.inholland.classes;

import nl.inholland.enums.AccessLevel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Student extends Person {
    private List<Report> reports;
    private String group;

    public Student(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String group, List<Report> reports) {
        super(username, password, firstName, lastName, dateOfBirth);

        this.accessLevel = AccessLevel.BASIC; // kinda useless but oh well

        this.reports = reports;

        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public List<Report> getReports() {
        return reports;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-13s %-12s %-12s %-6s %-7s%n",
                this.getId(),
                this.getFirstName(),
                this.getLastName(),
                this.getDateOfBirth().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")),
                this.getAge(),
                this.getGroup());
    }
}
