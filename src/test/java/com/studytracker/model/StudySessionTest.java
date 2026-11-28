package com.studytracker.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class StudySessionTest {

    private static final LocalDate DAY = LocalDate.of(2026, 11, 28);

    @Test
    void storesAllFields() {
        var s = new StudySession(1, "Calc 2", 90, DAY, "series review");
        assertEquals(1, s.id());
        assertEquals("Calc 2", s.course());
        assertEquals(90, s.minutes());
        assertEquals(DAY, s.date());
        assertEquals("series review", s.note());
    }

    @Test
    void hoursConvertsMinutes() {
        assertEquals(1.5, new StudySession(1, "Calc 2", 90, DAY, "").hours(), 1e-9);
    }

    @Test
    void nullNoteBecomesEmpty() {
        assertEquals("", new StudySession(1, "Calc 2", 30, DAY, null).note());
    }

    @Test
    void courseIsStripped() {
        assertEquals("Calc 2", new StudySession(1, "  Calc 2  ", 30, DAY, "").course());
    }

    @Test
    void rejectsBlankCourse() {
        assertThrows(IllegalArgumentException.class, () -> new StudySession(1, "  ", 30, DAY, ""));
        assertThrows(IllegalArgumentException.class, () -> new StudySession(1, null, 30, DAY, ""));
    }

    @Test
    void rejectsNonPositiveMinutes() {
        assertThrows(IllegalArgumentException.class, () -> new StudySession(1, "Calc 2", 0, DAY, ""));
        assertThrows(IllegalArgumentException.class, () -> new StudySession(1, "Calc 2", -5, DAY, ""));
    }

    @Test
    void rejectsNullDate() {
        assertThrows(IllegalArgumentException.class, () -> new StudySession(1, "Calc 2", 30, null, ""));
    }
}
