class DiscountDecorator extends PaymentDecorator {
    private final double discountPercent;

    public DiscountDecorator(Payment wrapped, double discountPercent) {
        super(wrapped);
        this.discountPercent = discountPercent;
    }

    @Override
    public double pay(double amount) {
        double discounted = amount - (amount * discountPercent / 100);
        System.out.println("Discount applied: " + discountPercent + "% -> New amount: " + format(discounted));
        return super.pay(discounted);
    }

    private String format(double v) {
        return String.format("%.2f", v);
    }
}
