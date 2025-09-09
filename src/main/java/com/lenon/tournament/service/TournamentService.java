package com.lenon.tournament.service;

import com.lenon.tournament.model.Athlete;
import com.lenon.tournament.model.Match;
import com.lenon.tournament.model.Team;

import java.time.LocalDateTime;
import java.util.*;

public class TournamentService {

    // Set: uniqueness of athletes
    private final Set<Athlete> athletes = new HashSet<>();
    // Map: quick index by ID
    private final Map<String, Athlete> athleteById = new HashMap<>();

    // List: ordered collections for teams and matches
    private final List<Team> teams = new ArrayList<>();
    private final List<Match> schedule = new ArrayList<>();

    // LinkedList: waitlist for substitutes (enqueue/dequeue efficient)
    private final LinkedList<Athlete> waitlist = new LinkedList<>();

    // LinkedHashMap: points table keeping stable insertion order
    private final Map<Team, Integer> points = new LinkedHashMap<>();

    // === Athletes ===
    public boolean registerAthlete(Athlete a) {
        if (athletes.add(a)) { // HashSet prevents duplicates
            athleteById.put(a.getId(), a);
            return true;
        }
        return false;
    }

    public Optional<Athlete> findAthlete(String id) {
        return Optional.ofNullable(athleteById.get(id));
    }

    public Set<Athlete> listAthletes() {
        return Collections.unmodifiableSet(athletes);
    }

    // === Teams ===
    public Team createTeam(String name, String... athleteIds) {
        Team t = new Team(name);
        for (String id : athleteIds) {
            findAthlete(id).ifPresent(t::addAthlete);
        }
        teams.add(t);
        points.put(t, 0);
        return t;
    }

    public List<Team> listTeams() {
        return Collections.unmodifiableList(teams);
    }

    // === Round-robin schedule ===
    public void buildSchedule(LocalDateTime start) {
        schedule.clear();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                schedule.add(new Match(teams.get(i), teams.get(j), start.plusHours(schedule.size())));
            }
        }
    }

    public List<Match> getSchedule() {
        return Collections.unmodifiableList(schedule);
    }

    // === Scores / Ranking ===
    public void registerScore(Match m, int a, int b) {
        m.registerScore(a, b);
        if (a > b) points.put(m.getTeamA(), points.get(m.getTeamA()) + 3);
        else if (b > a) points.put(m.getTeamB(), points.get(m.getTeamB()) + 3);
        else { // draw (if applicable)
            points.put(m.getTeamA(), points.get(m.getTeamA()) + 1);
            points.put(m.getTeamB(), points.get(m.getTeamB()) + 1);
        }
    }

    public List<Map.Entry<Team, Integer>> ranking() {
        List<Map.Entry<Team, Integer>> r = new ArrayList<>(points.entrySet());
        r.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue())); // desc
        return r;
    }

    // === Waitlist (LinkedList) ===
    public void enqueueSubstitute(Athlete a) { waitlist.addLast(a); }
    public Optional<Athlete> callSubstitute() {
        return waitlist.isEmpty() ? Optional.empty() : Optional.of(waitlist.removeFirst());
    }
    public List<Athlete> viewWaitlist() { return Collections.unmodifiableList(waitlist); }
}
