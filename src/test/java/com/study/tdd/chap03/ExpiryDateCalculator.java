package com.study.tdd.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonth = payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() != null) {
            LocalDate expectedNextBillingDate = payData.getBillingDate().plusMonths(addedMonth);
            if (payData.getFirstBillingDate().getDayOfMonth() != expectedNextBillingDate.getDayOfMonth()) {
                if (YearMonth.from(expectedNextBillingDate).lengthOfMonth() < payData.getFirstBillingDate().getDayOfMonth()) {
                    return expectedNextBillingDate.withDayOfMonth(YearMonth.from(expectedNextBillingDate).lengthOfMonth());
                }
                return expectedNextBillingDate.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addedMonth);
    }
}
