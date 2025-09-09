package com.lenon.tournament.util;

import com.lenon.tournament.model.Athlete;
import com.lenon.tournament.model.Match;
import com.lenon.tournament.model.Team;
import com.lenon.tournament.service.TournamentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final TournamentService service;
    private final Scanner in = new Scanner(System.in);

    public ConsoleMenu(TournamentService service) {
        this.service = service;
    }

    public void run() {
        int op;
        do {
            System.out.println("\n=== Beach Volleyball Tournament ===");
            System.out.println("1) Register athlete");
            System.out.println("2) Create team");
            System.out.println("3) Build schedule");
            System.out.println("4) List matches");
            System.out.println("5) Register score");
            System.out.println("6) Show ranking");
            System.out.println("7) Enqueue substitute");
            System.out.println("8) Call substitute");
            System.out.println("9) List athletes");
            System.out.println("10) List teams");
            System.out.println("0) Exit");
            System.out.print("Option: ");
            op = readInt();

            try {
                switch (op) {
                    case 1 -> registerAthlete();
                    case 2 -> createTeam();
                    case 3 -> buildSchedule();
                    case 4 -> listMatches();
                    case 5 -> registerScore();
                    case 6 -> showRanking();
                    case 7 -> enqueueSubstitute();
                    case 8 -> callSubstitute();
                    case 9 -> listAthletes();
                    case 10 -> listTeams();
                    case 0 -> System.out.println("Bye!");
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (op != 0);
    }

    private void registerAthlete() {
        System.out.print("Athlete ID: ");
        String id = in.nextLine().trim();
        System.out.print("Name: ");
        String name = in.nextLine().trim();
        System.out.print("Level (beginner/intermediate/advanced): ");
        String level = in.nextLine().trim();

        boolean ok = service.registerAthlete(new Athlete(id, name, level));
        System.out.println(ok ? "Athlete registered." : "An athlete with this ID already exists.");
    }

    private void createTeam() {
        System.out.print("Team name: ");
        String name = in.nextLine().trim();
        System.out.print("Athlete IDs (comma-separated): ");
        String[] ids = in.nextLine().trim().split("\\s*,\\s*");
        Team t = service.createTeam(name, ids);
        System.out.println("Team created: " + t);
    }

    private void buildSchedule() {
        service.buildSchedule(LocalDateTime.now());
        System.out.println("Schedule built with " + service.getSchedule().size() + " match(es).");
    }

    private void listMatches() {
        List<Match> matches = service.getSchedule();
        if (matches.isEmpty()) {
            System.out.println("No matches.");
            return;
        }
        for (int i = 0; i < matches.size(); i++) {
            System.out.println(i + ": " + matches.get(i));
        }
    }

    private void registerScore() {
        listMatches();
        System.out.print("Match index: ");
        int idx = readInt();
        List<Match> matches = service.getSchedule();
        if (idx < 0 || idx >= matches.size()) {
            System.out.println("Invalid index.");
            return;
        }
        System.out.print("Points Team A: ");
        int a = readInt();
        System.out.print("Points Team B: ");
        int b = readInt();
        service.registerScore(matches.get(idx), a, b);
        System.out.println("Score registered.");
    }

    private void showRanking() {
        var ranking = service.ranking();
        if (ranking.isEmpty()) {
            System.out.println("No ranking yet.");
            return;
        }
        System.out.println("== Ranking ==");
        int pos = 1;
        for (var e : ranking) {
            System.out.printf("%d) %s - %d pts%n", pos++, e.getKey().getName(), e.getValue());
        }
    }

    private void enqueueSubstitute() {
        System.out.print("Athlete ID to enqueue: ");
        String id = in.nextLine().trim();
        service.findAthlete(id).ifPresentOrElse(
                a -> { service.enqueueSubstitute(a); System.out.println("Enqueued."); },
                () -> System.out.println("Athlete not found.")
        );
    }

    private void callSubstitute() {
        var sub = service.callSubstitute();
        System.out.println(sub.map(a -> "Substitute: " + a).orElse("Waitlist is empty."));
    }

    private void listAthletes() {
        var athletes = service.listAthletes();
        if (athletes.isEmpty()) {
            System.out.println("No athletes registered.");
            return;
        }
        System.out.println("=== Athletes ===");
        athletes.forEach(a -> System.out.println("- " + a));
    }

    private void listTeams() {
        var teams = service.listTeams();
        if (teams.isEmpty()) {
            System.out.println("No teams created.");
            return;
        }
        System.out.println("=== Teams ===");
        teams.forEach(t -> System.out.println("- " + t));
    }

    private int readInt() {
        String s = in.nextLine().trim();
        return Integer.parseInt(s);
    }
}
