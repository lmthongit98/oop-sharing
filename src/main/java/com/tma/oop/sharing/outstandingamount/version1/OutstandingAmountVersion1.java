package com.tma.oop.sharing.outstandingamount.version1;

import java.util.List;

/**
 * We have 3 type of lender: A, B and C.
 * <p>
 * Sprint 1:
 * All Lenders : fetch from internal API
 * <p>
 * Sprint 2:
 * if lenders are A type: fetch from DB
 * if lenders are B or C type: fetch from internal API
 * <p>
 * Sprint 3:
 * if lenders are A type: fetch from DB
 * if lenders are B or C type: fetch from external API
 * if lenders are IDFC (B type) or ABFl (B type), we will call lender API (IDFC API or ABFL API)
 */

public class OutstandingAmountVersion1 {

    // getting outstanding amount logic is being used in multiple places within the project.

    public Double getOutstandingBalanceSprint1(String application, String lender) {
        return getOutStandingBalanceFromDB(application);
    }

    public Double getOutstandingBalanceSprint2(String application, String lender) {
        String type = getLenderType(lender);
        if (List.of("B", "C").contains(type)) {
            return getOutStandingBalanceFromInternalAPi(application);
        } else if ("A".equals(type)) {
            return getOutStandingBalanceFromDB(application);
        }
        throw new IllegalArgumentException("Type is not valid");
    }


    public Double getOutstandingBalanceSprint3(String application, String lender) {
        String type = getLenderType(lender);
        if (List.of("B", "C").contains(type)) {
            if (lender.equals("IDFC")) {
                return callIdfcAPi(application);
            }
            if (lender.equals("ABFL")) {
                return callABFLAPi(application);
            }
            return getOutStandingBalanceFromInternalAPi(application);
        } else if ("A".equals(type)) {
            return getOutStandingBalanceFromDB(application);
        }
        throw new IllegalArgumentException("Type is not valid");
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
