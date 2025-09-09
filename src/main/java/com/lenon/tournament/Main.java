package com.lenon.tournament;

import com.lenon.tournament.model.Athlete;
import com.lenon.tournament.service.TournamentService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        TournamentService svc = new TournamentService();
        svc.registerAthlete(new Athlete("A1","Lenon","advanced"));
        svc.registerAthlete(new Athlete("A2","Fuzatto","advanced"));
        svc.registerAthlete(new Athlete("A3","Sheyla","intermediate"));
        svc.registerAthlete(new Athlete("A4","Lorena","beginner"));

        svc.createTeam("Camburi", "A1","A3");
        svc.createTeam("Praia", "A2","A4");

        svc.buildSchedule(LocalDateTime.now());
        System.out.println("Schedule size: " + svc.getSchedule().size());
        System.out.println("Athletes: " + svc.listAthletes().size());
    }
}
