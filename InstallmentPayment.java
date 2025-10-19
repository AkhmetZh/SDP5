class InstallmentPayment implements Payment {
    private final int months;
    private final double annualRate;

    public InstallmentPayment(int months, double annualRate) {
        this.months = months;
        this.annualRate = annualRate;
    }

    @Override
    public double pay(double amount) {
        double monthlyRate = annualRate / 12 / 100;
        double monthlyPayment = (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        System.out.println("Installment Plan Selected:");
        System.out.println("Total Amount: " + format(amount));
        System.out.println("Monthly Payment: " + format(monthlyPayment) + " for " + months + " months");
        System.out.println("Total Payable with interest: " + format(monthlyPayment * months));
        return monthlyPayment;
    }

    private String format(double v) {
        return String.format("%.2f", v);
    }
}
