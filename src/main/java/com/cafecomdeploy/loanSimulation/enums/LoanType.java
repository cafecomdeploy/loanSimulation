package com.cafecomdeploy.loanSimulation.enums;

public enum LoanType {
    PERSONAL(3.5),
    CONSIGNADO(1.8),
    EMPRESARIAL(4.2);

    private final double interestRate;

    LoanType(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }
}