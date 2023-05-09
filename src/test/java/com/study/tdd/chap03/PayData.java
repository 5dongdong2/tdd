package com.study.tdd.chap03;

import java.time.LocalDate;

public class PayData {
    private LocalDate billingDate;
    private int payAmount;

    public PayData() {
    }

    public PayData(LocalDate billingDate, int payAmount) {
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public static class Builder {
        private final PayData date = new PayData();

        public Builder billingDate(LocalDate billingDate) {
            date.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            date.payAmount = payAmount;
            return this;
        }

        public PayData build() {
            return date;
        }
    }
}
