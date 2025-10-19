class CreditCardPayment implements Payment {
    @Override
    public double pay(double amount) {
        System.out.println("Payment processed via Credit Card.");
        double finalAmount = Math.round(amount * 0.9 * 100.0) / 100.0;
        System.out.println("Applied 10% discount. Final amount: " + format(finalAmount));
        return finalAmount;
    }

    private String format(double v) {
        return String.format("%.2f", v);
    }
}
