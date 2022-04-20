package model;

public class City {
    private int id;
    private String city_name;

    public City(int id, String city_name) {
        this.id = id;
        this.city_name = city_name;
    }

    public int getId() {
        return id;
    }

    public String getCity_name() {
        return city_name;
    }
}
