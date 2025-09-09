package com.lenon.tournament.service;

import com.lenon.tournament.model.Athlete;
import com.lenon.tournament.model.Match;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TournamentServiceTest {

    @Test
    void shouldRegisterAthleteWithoutDuplicates() {
        TournamentService s = new TournamentService();
        assertTrue(s.registerAthlete(new Athlete("A1","Lenon","intermediate")));
        assertFalse(s.registerAthlete(new Athlete("A1","Another","beginner"))); // same id
        assertEquals(1, s.listAthletes().size());
    }

    @Test
    void shouldBuildScheduleAndRegisterScore() {
        TournamentService s = new TournamentService();
        s.registerAthlete(new Athlete("A1","L1","i"));
        s.registerAthlete(new Athlete("A2","L2","i"));
        s.registerAthlete(new Athlete("A3","L3","i"));
        s.registerAthlete(new Athlete("A4","L4","i"));

        s.createTeam("T1", "A1", "A2");
        s.createTeam("T2", "A3", "A4");

        s.buildSchedule(LocalDateTime.now());
        assertEquals(1, s.getSchedule().size());

        Match m = s.getSchedule().get(0);
        s.registerScore(m, 21, 18);
        assertFalse(s.ranking().isEmpty());
        assertTrue(s.ranking().get(0).getValue() >= 0);
    }
}
