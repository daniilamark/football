package model;

public class Team {
    private int team_id;
    private int stadium_id;
    private String stadium_name;
    private int trainer_id;
    private String trainer_name;
    private int city_id;
    private String city_name;
    private String team_name;
    private int last_year_rating;

    public Team(String stadium_name, String trainer_name, String city_name, String team_name, int last_year_rating) {
        this.stadium_name = stadium_name;
        this.trainer_name = trainer_name;
        this.city_name = city_name;
        this.team_name = team_name;
        this.last_year_rating = last_year_rating;
    }
    public String getTrainer_name() {
        return trainer_name;
    }
    public String getCity_name() {
        return city_name;
    }
    public String getStadium_name() {
        return stadium_name;
    }

    public int getTeam_id() {
        return team_id;
    }

    public int getStadium_id() {
        return stadium_id;
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

    public Team(int team_id, int stadium_id, int trainer_id, int city_id, String team_name, int last_year_rating) {
        this.team_id = team_id;
        this.stadium_id = stadium_id;
        this.trainer_id = trainer_id;
        this.city_id = city_id;
        this.team_name = team_name;
        this.last_year_rating = last_year_rating;
    }
}
