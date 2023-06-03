package ru.job4j.cash;

public class Account {
    private int id;
    private int amount;

    public Account(int id, int amount) {
        this.amount = amount;
        this.id = id;
    }

    public int amount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }
}
