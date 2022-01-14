package database.model;

public class Wallet {
    private int id;
    private double amount;

    public Wallet(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }


    //getters
    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }


    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
