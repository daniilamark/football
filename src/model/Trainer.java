package model;

public class Trainer {
    private int trainer_id;
    private String family;
    private String trainer_name;
    private String last_name;

    public Trainer(int trainer_id, String trainer_name) {
        this.trainer_id = trainer_id;
        this.trainer_name = trainer_name;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public String getFamily() {
        return family;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Trainer(int trainer_id, String family, String trainer_name, String last_name) {
        this.trainer_id = trainer_id;
        this.family = family;
        this.trainer_name = trainer_name;
        this.last_name = last_name;
    }
}
