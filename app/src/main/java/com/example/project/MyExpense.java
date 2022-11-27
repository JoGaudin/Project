package com.example.project;

public class MyExpense {
    public String title;
    public String date;
    public double amount;
    public String currency;
    public String category;

    public MyExpense(String title, String date, double amount, String currency, String category) {
        this.title = title;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCategory() {
        return category;
    }

    public String getOverall() {
        String res = getTitle() + " " + getDate() + " " + getAmount() + " " + getCurrency() + " " + getCategory();
        return res;
    }
}
