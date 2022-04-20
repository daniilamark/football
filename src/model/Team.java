package model;

public class Team {
    private int team_id;
    private int staduim_id;
    private int trainer_id;
    private int city_id;
    private String team_name;
    private int last_year_rating;

    public int getTeam_id() {
        return team_id;
    }

    public int getStaduim_id() {
        return staduim_id;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public int getLast_year_rating() {
        return last_year_rating;
    }

    public Team(int team_id, int staduim_id, int trainer_id, int city_id, String team_name, int last_year_rating) {
        this.team_id = team_id;
        this.staduim_id = staduim_id;
        this.trainer_id = trainer_id;
        this.city_id = city_id;
        this.team_name = team_name;
        this.last_year_rating = last_year_rating;
    }
}
