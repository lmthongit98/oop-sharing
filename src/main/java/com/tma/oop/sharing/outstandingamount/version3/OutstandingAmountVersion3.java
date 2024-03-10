package com.tma.oop.sharing.outstandingamount.version3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Remember, knowing
 * concepts like abstraction,
 * inheritance, and polymorphism do
 * not make you a good object oriented
 * designer. A design guru thinks about
 * how to create flexible designs that
 * are maintainable and that can
 * cope with change.
 */

public class OutstandingAmountVersion3 {

    public static void main(String[] args) {
       OutstandingAmountService outstandingAmountService = new OutstandingAmountService(
               List.of(new LenderApiCalling(), new InternalApiCalling(), new DBService())
       );
        Double outstandingAmount = outstandingAmountService.getOutStandingAmount("app-123", "ABFL");
    }

}

class OutstandingAmountService { // should be singleton class

    private final List<OutstandingAmountResolver> outstandingAmountResolvers;

    OutstandingAmountService(List<OutstandingAmountResolver> outstandingAmountResolvers) {
        this.outstandingAmountResolvers = outstandingAmountResolvers;
    }

    Double getOutStandingAmount(String application, String lender) {
        for (OutstandingAmountResolver outstandingAmountResolver : outstandingAmountResolvers) {
            if (outstandingAmountResolver.applicableLenders().contains(lender)) {
                return outstandingAmountResolver.getOutStandingAmount(application, lender);
            }
        }
        throw new IllegalArgumentException("Lender is invalid");
    }
}

interface OutstandingAmountResolver {
    Double getOutStandingAmount(String application, String lender);

    List<String> applicableLenders();
}

class LenderApiCalling implements OutstandingAmountResolver {
    @Override
    public Double getOutStandingAmount(String application, String lender) {
        return null;
    }

    @Override
    public List<String> applicableLenders() {
        return Arrays.asList("KSF", "CHOLA", "PNB");
    }
}

class InternalApiCalling implements OutstandingAmountResolver {
    @Override
    public Double getOutStandingAmount(String application, String lender) {
        return null;
    }

    @Override
    public List<String> applicableLenders() {
        return List.of("ABFL");
    }
}

class DBService implements OutstandingAmountResolver {
    @Override
    public Double getOutStandingAmount(String application, String lender) {
        return null;
    }

    @Override
    public List<String> applicableLenders() {
        return List.of("IDFC");
    }
}


class OutstandingAmountServiceCache {

    private final Map<String, OutstandingAmountResolver> outstandingAmountResolverMap = new HashMap<>();

    public OutstandingAmountServiceCache(List<OutstandingAmountResolver> outstandingAmountResolvers) {
        for (OutstandingAmountResolver outstandingAmountResolver : outstandingAmountResolvers) {
            List<String> lenders = outstandingAmountResolver.applicableLenders();
            for (String lender : lenders) {
                outstandingAmountResolverMap.put(lender, outstandingAmountResolver);
            }
        }
    }

    Double getOutStandingAmount(String application, String lender) {
        OutstandingAmountResolver resolver = outstandingAmountResolverMap.get(lender);
        if (resolver == null) {
            throw new IllegalArgumentException("Lender is invalid");
        }

        return resolver.getOutStandingAmount(application, lender);
    }
}










