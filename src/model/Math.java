package model;


public class Math {
    private int math_id;
    private int team_id_1;
    //private String team_1_name;
    private int team_id_2;
    //private String team_2_name;
    private String team_name1;
    private String team_name2;
    private String date;
    private String score;


    public int getMath_id() {
        return math_id;
    }

    public int getTeam_id_1() {
        return team_id_1;
    }
    public int getTeam_id_2() {
        return team_id_2;
    }

    public String getDate() {
        return date;
    }

    public String getTeam_name1() {
        return team_name1;
    }

    public String getTeam_name2() {
        return team_name2;
    }

    public String getScore() {
        return score;
    }
    /*
    public Math(int math_id, int team_id_1, int team_id_2, String date, String score) {
        this.math_id = math_id;
        this.team_id_1 = team_id_1;
        this.team_id_2 = team_id_2;
        this.score = score;
        this.date = date;
    }


    public Math(String team_1_name, String team_2_name, String date, int score) {
        this.team_1_name = team_1_name;
        this.team_2_name = team_2_name;
        this.score = score;
        this.date = date;
    }

     */

    public Math(String team_name1) {
        this.team_name1 = team_name1;
    }



    public Math(String team_name2, String date, String score) {
        this.team_name2 = team_name2;
        this.score = score;
        this.date = date;
    }

}
