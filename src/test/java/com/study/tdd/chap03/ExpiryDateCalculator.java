package com.study.tdd.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonth = 1;
        if (payData.getFirstBillingDate() != null) {
            LocalDate expectedNextBillingDate = payData.getBillingDate().plusMonths(addedMonth);
            if (payData.getFirstBillingDate().getDayOfMonth() != expectedNextBillingDate.getDayOfMonth()) {
                return expectedNextBillingDate.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(1);
    }
}
