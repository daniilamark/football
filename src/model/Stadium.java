package model;

public class Stadium {
    private int stadium_id;
    private int city_id;
    private String stadium_name;
    private int capacity;
    private int ticket_price;

    public Stadium(int stadium_id, int city_id, String stadium_name, int capacity, int ticket_price) {
        this.stadium_id = stadium_id;
        this.city_id = city_id;
        this.stadium_name = stadium_name;
        this.capacity = capacity;
        this.ticket_price = ticket_price;
    }

    public int getStadium_id() {
        return stadium_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public String getStadium_name() {
        return stadium_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTicket_price() {
        return ticket_price;
    }
}
