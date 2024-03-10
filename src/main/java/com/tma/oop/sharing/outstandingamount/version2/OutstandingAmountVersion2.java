package com.tma.oop.sharing.outstandingamount.version2;

import java.util.Arrays;

/**
    - Apply Encapsulation and Abstraction
    <p>
    - Separating what changes from what stays the same
 */

public class OutstandingAmountVersion2 {

    public static void main(String[] args) {
        OutstandingAmountService outstandingAmountService = new OutstandingAmountService();
        Double outstandingAmount = outstandingAmountService.getOutStandingAmount("app-123", "ABFL");
    }

}

/**
 Take the parts that vary and encapsulate them, so that later you can
 alter or extend the parts that vary without affecting
 those that don’t.
 */
class OutstandingAmountService {


    public double getOutStandingAmount(String application, String lender) {
        String type = getLenderType(lender);
        if (Arrays.asList("B", "C").contains(type)) {
            if (lender.equals("IDFC")) {
                return callIdfcAPi(application);
            }
            if (lender.equals("ABFL")) {
                return callABFLAPi(application);
            }
            return getOutStandingBalanceFromInternalAPi(application);
        }
        return getOutStandingBalanceFromDB(application);
    }

    private Double callABFLAPi(String application) {
        return 0.0;
    }

    private Double callIdfcAPi(String application) {
        return 0.0;
    }

    private String getLenderType(String lender) {
        return "A";
    }


    private Double getOutStandingBalanceFromDB(String application) {
        return 0.0;
    }

    private Double getOutStandingBalanceFromInternalAPi(String application) {
        return 0.0;
    }

}











