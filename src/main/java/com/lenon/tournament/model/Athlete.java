package com.lenon.tournament.model;

import java.util.Objects;

public class Athlete {
    private final String id;     // e.g., CPF/UUID/email
    private final String name;
    private final String level;  // beginner/intermediate/advanced

    public Athlete(String id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public String getId()    { return id; }
    public String getName()  { return name; }
    public String getLevel() { return level; }

    // uniqueness by id (for Set/Map)
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Athlete)) return false;
        Athlete a = (Athlete) o;
        return Objects.equals(id, a.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }

    @Override public String toString() {
        return name + " (" + level + ")";
    }
}
