package com.lenon.tournament.model;

import java.time.LocalDateTime;

public class Match {
    private final Team teamA;
    private final Team teamB;
    private final LocalDateTime when;
    private Integer pointsA; // null = not played
    private Integer pointsB;

    public Match(Team teamA, Team teamB, LocalDateTime when) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.when = when;
    }

    public Team getTeamA() { return teamA; }
    public Team getTeamB() { return teamB; }
    public LocalDateTime getWhen() { return when; }
    public Integer getPointsA() { return pointsA; }
    public Integer getPointsB() { return pointsB; }
    public boolean finished() { return pointsA != null && pointsB != null; }

    public void registerScore(int a, int b) {
        this.pointsA = a; this.pointsB = b;
    }

    @Override public String toString() {
        String score = finished() ? (pointsA + " x " + pointsB) : "scheduled";
        return when + " | " + teamA.getName() + " vs " + teamB.getName() + " | " + score;
    }
}
