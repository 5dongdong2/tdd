package com.study.tdd.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonth) {
        LocalDate expectedNextBillingDate = payData.getBillingDate().plusMonths(addedMonth);
        final int dayOfFirstBillingDate = payData.getFirstBillingDate().getDayOfMonth();

        if (isSameDayOfMonth(expectedNextBillingDate, dayOfFirstBillingDate)) {
            final int dayLengthOfExpectedNextBillingDate = lastDayOfMonth(expectedNextBillingDate);
            if (dayLengthOfExpectedNextBillingDate < dayOfFirstBillingDate) {
                return expectedNextBillingDate.withDayOfMonth(dayLengthOfExpectedNextBillingDate);
            }
            return expectedNextBillingDate.withDayOfMonth(dayOfFirstBillingDate);
        } else {
            return expectedNextBillingDate;
        }
    }

    private boolean isSameDayOfMonth(LocalDate expectedNextBillingDate, int dayOfFirstBillingDate) {
        return dayOfFirstBillingDate != expectedNextBillingDate.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate expectedNextBillingDate) {
        return YearMonth.from(expectedNextBillingDate).lengthOfMonth();
    }
}
