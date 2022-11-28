package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class BudgetBase {

    private List<MyBudget> mBudgets;

    private BudgetBase() {
        mBudgets = new ArrayList<>();
    }

    public List<MyBudget> getBudgets() {
        return mBudgets;
    }

    private static BudgetBase mBudgetBase;

    public static BudgetBase get() {
        if(mBudgetBase == null) {
            mBudgetBase = new BudgetBase();
        }
        return mBudgetBase;
    }
}
