package model;

public class Math {
    private int math_id;
    private int team_id_1;
    private int team_id_2;
    private int date;

    public int getMath_id() {
        return math_id;
    }

    public int getTeam_id_1() {
        return team_id_1;
    }

    public int getTeam_id_2() {
        return team_id_2;
    }

    public int getDate() {
        return date;
    }

    public Math(int math_id, int team_id_1, int team_id_2, int date) {
        this.math_id = math_id;
        this.team_id_1 = team_id_1;
        this.team_id_2 = team_id_2;
        this.date = date;
    }
}
