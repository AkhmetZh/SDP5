class FraudDetectionDecorator extends PaymentDecorator {
    public FraudDetectionDecorator(Payment wrapped) {
        super(wrapped);
    }

    @Override
    public double pay(double amount) {
        System.out.println("Performing fraud detection for " + format(amount) + "...");
        double result = super.pay(amount);
        System.out.println("Fraud check passed successfully.");
        return result;
    }

    private String format(double v) {
        return String.format("%.2f", v);
    }
}
