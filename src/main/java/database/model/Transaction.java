package database.model;

import database.Status;
import database.Type;

public class Transaction {

    private int id;
    private Wallet wallet;
    private double amount;
    private Status status;
    private Type type;


    public Transaction(int id, Wallet wallet, double amount, Status status, Type type) {
        this.id = id;
        this.wallet = wallet;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public double getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }
}

