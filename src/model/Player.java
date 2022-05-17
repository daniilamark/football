package model;

public class Player {
    private int player_id;
    private int team_id;
    private String team_name;
    private String family;
    private String name;
    private String last_name;
    private int age;
    private String role;
    private int number;

    public Player(String team_name, String family, String name, String last_name, int age, String role, int number) {
        this.team_name = team_name;
        this.family = family;
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.role = role;
        this.number = number;
    }

    public Player(int player_id, int team_id, String family, String name, String last_name, int age, String role, int number) {
        this.player_id = player_id;
        this.team_id = team_id;
        this.family = family;
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.role = role;
        this.number = number;
    }

    public String getTeam_name() {
        return team_name;
    }


    public int getPlayer_id() {
        return player_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public int getNumber() {
        return number;
    }
}
