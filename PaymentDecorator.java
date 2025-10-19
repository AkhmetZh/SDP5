class PaymentDecorator implements Payment {
    protected Payment wrapped;

    public PaymentDecorator(Payment wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public double pay(double amount) {
        return wrapped.pay(amount);
    }
}
