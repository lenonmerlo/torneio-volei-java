package com.lenon.tournament;

import com.lenon.tournament.model.Athlete;
import com.lenon.tournament.service.TournamentService;
import com.lenon.tournament.util.ConsoleMenu;

public class Main {
    public static void main(String[] args) {
        TournamentService svc = new TournamentService();

        // Optional seed for quick demo
        svc.registerAthlete(new Athlete("A1","Lenon","advanced"));
        svc.registerAthlete(new Athlete("A2","Fuzatto","advanced"));
        svc.registerAthlete(new Athlete("A3","Sheyla","intermediate"));
        svc.registerAthlete(new Athlete("A4","Lorena","beginner"));

        new ConsoleMenu(svc).run();
    }
}
