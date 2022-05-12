package model;

public class Trainer {
    private int trainer_id;
    private String family;
    private String name;
    private String last_name;

    public Trainer(int trainer_id, String name) {
        this.trainer_id = trainer_id;
        this.name = name;
    }

    public int getTrainer_id() {
        return trainer_id;
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

    public Trainer(int trainer_id, String family, String name, String last_name) {
        this.trainer_id = trainer_id;
        this.family = family;
        this.name = name;
        this.last_name = last_name;
    }
}
