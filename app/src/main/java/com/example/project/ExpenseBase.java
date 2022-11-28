package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class ExpenseBase {

    private List<MyExpense> mExpenses;

    private ExpenseBase() {
        mExpenses = new ArrayList<>();
    }

    public List<MyExpense> getExpenses() {
        return mExpenses;
    }

    private static ExpenseBase mExpenseBase;

    public static ExpenseBase get() {
        if(mExpenseBase == null) {
            mExpenseBase = new ExpenseBase();
        }
        return mExpenseBase;
    }

}
