class PayPalPayment implements Payment {
    @Override
    public double pay(double amount) {
        System.out.println("Payment processed via PayPal.");
        double cashback = amount * 0.07;
        System.out.println("Cashback earned: " + format(cashback));
        return amount;
    }

    private String format(double v) {
        return String.format("%.2f", v);
    }
}
