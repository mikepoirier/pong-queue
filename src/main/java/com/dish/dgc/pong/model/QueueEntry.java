package com.dish.dgc.pong.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueueEntry {
    private String name;
    private String team;

    public QueueEntry(
            @JsonProperty("name") String name,
            @JsonProperty("team") String team
    ) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueueEntry that = (QueueEntry) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return team != null ? team.equals(that.team) : that.team == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QueueEntry{" +
                "name='" + name + '\'' +
                ", team='" + team + '\'' +
                '}';
    }


}
