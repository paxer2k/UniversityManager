package nl.inholland.classes;

import nl.inholland.enums.Subject;

public class Report {
    private Subject subject;
    private int score;

    public Report(Subject subject, int grade) {
        this.subject = subject;
        this.score = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
