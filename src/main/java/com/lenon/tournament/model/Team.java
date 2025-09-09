package com.lenon.tournament.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final List<Athlete> athletes = new ArrayList<>(); // ArrayList: ordered access

    public Team(String name) { this.name = name; }

    public String getName() { return name; }
    public List<Athlete> getAthletes() { return athletes; }

    public void addAthlete(Athlete a) { athletes.add(a); }

    @Override public String toString() { return name + " " + athletes; }
}
