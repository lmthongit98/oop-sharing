package com.tma.oop.sharing.lenderborrower.version1;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LenderBorrowerVersion1 {


    public static void main(String[] args) {
        Borrower johny = new Borrower("Johny", "Poor", 0);
        Lender peter = new Lender("Peter", "Rich", 5000);

        // Johny borrows money from Petter
        johny.setCash(50);
        peter.setMoney(peter.getMoney() - 50);

    }

}


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
abstract class User {

    private String firstName;
    private String lastName;

    public abstract double getMoney();

}

@Getter
@Setter
@NoArgsConstructor
class Lender extends User {

    private double money;

    public Lender(String firstName, String lastName, double money) {
        super(firstName, lastName);
        this.money = money;
    }

    @Override
    public double getMoney() {
        return this.money;
    }

}

@Getter
@Setter
@NoArgsConstructor
class Borrower extends User {

    private double cash;

    public Borrower(String firstName, String lastName, double cash) {
        super(firstName, lastName);
        this.cash = cash;
    }

    @Override
    public double getMoney() {
        return this.cash;
    }

}