package com.tma.oop.sharing.lenderborrower.version2;

import lombok.Getter;
import lombok.Setter;

public class LenderBorrowerVersion2 {


    public static void main(String[] args) {
        Borrower johny = new Borrower("Johny", "Poor", 500);
        Lender peter = new Lender("Peter", "Rich", 5000);

        // Johny borrow money from Petter
        johny.borrowMoneyFrom(peter, 1000);
        System.out.println("Johny after borrow money: " + johny);
        System.out.println("Peter after lend money: " + peter);
        // Johny repay money to his lender
        johny.repayToLender();
        System.out.println("Johny after repay money: " + johny);
        System.out.println("Peter after get money back: " + peter);
    }

}


@Getter
@Setter
abstract class User {

    protected String firstName;
    protected String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public abstract double getMoney();

}

@Getter
@Setter
class Lender extends User {
    private double money;
    private double loanAmount;
    private Borrower borrower;

    public Lender(String firstName, String lastName, double money) {
        super(firstName, lastName);
        this.money = money;
    }

    public boolean lendMoney(Borrower br, double amount) {
        if (amount <= 0 || this.money < amount) {
            return false;
        }
        this.loanAmount = amount;
        this.borrower = br;
        this.money -= amount;
        return true;
    }

    public boolean getMoneyBackFrom(Borrower br, double amount) {
        if (this.borrower == null || !this.borrower.equals(br) || this.loanAmount != amount) {
            return false;
        }
        this.money += amount;
        this.loanAmount = 0;
        this.borrower = null;
        return true;
    }

    @Override
    public double getMoney() {
        return this.money;
    }

    @Override
    public String toString() {
        return "Lender{" +
                "money=" + money +
                ", loanAmount=" + loanAmount +
                '}';
    }
}

@Getter
@Setter
class Borrower extends User {

    private double cash;
    private Lender lender;
    private double loanAmount;

    public Borrower(String firstName, String lastName, double cash) {
        super(firstName, lastName);
        this.cash = cash;
    }

    public boolean borrowMoneyFrom(Lender l, double amount) {
        if (this.lender != null) { // can only borrow one lender at a time
            return false;
        }
        if (!l.lendMoney(this, amount)) {
            return false;
        }
        this.cash += amount;
        this.loanAmount = amount;
        this.lender = l;
        return true;
    }

    public boolean repayToLender() {
        if (lender == null || cash < loanAmount || !lender.getMoneyBackFrom(this, loanAmount)) {
            return false;
        }
        cash -= loanAmount;
        loanAmount = 0;
        lender = null;
        return true;
    }

    @Override
    public double getMoney() {
        return this.cash;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "cash=" + cash +
                ", loanAmount=" + loanAmount +
                '}';
    }
}