package com.example.project;

public class MyExpense {
    public String title;
    public String date;
    public double amount;
    public MyCurrency currency;
    public String category;

    public MyExpense(String title, String date, double amount, MyCurrency currency, String category) {
        this.title = title;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
    }
}
