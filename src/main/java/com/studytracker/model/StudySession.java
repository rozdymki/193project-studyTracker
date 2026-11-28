package com.studytracker.model;

import java.time.LocalDate;

/**
 * one logged study session.
 *
 *  record is immutable and the compact constructor below enforces the
 * rules every session must follow so an invalid session cant exist
 */
public record StudySession(int id, String course, int minutes, LocalDate date, String note) {

    public StudySession {
        if (course == null || course.isBlank()) {
            throw new IllegalArgumentException("Course must not be blank.");
        }
        if (minutes <= 0) {
            throw new IllegalArgumentException("Minutes must be greater than zero.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null.");
        }
        course = course.strip();
        note = (note == null) ? "" : note.strip();
    }

    /** length of the session in hours used by the weekly stats */
    public double hours() {
        return minutes / 60.0;
    }
}
