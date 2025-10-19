class CashbackDecorator extends PaymentDecorator {
    private final double cashbackPercent;

    public CashbackDecorator(Payment wrapped, double cashbackPercent) {
        super(wrapped);
        this.cashbackPercent = cashbackPercent;
    }

    @Override
    public double pay(double amount) {
        double paid = super.pay(amount);
        double cashback = paid * cashbackPercent / 100;
        System.out.println("Additional Cashback: " + format(cashback));
        return paid;
    }

    private String format(double v) {
        return String.format("%.2f", v);
    }
}
