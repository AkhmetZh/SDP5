import java.util.Scanner;

class CheckoutFacade {
    private final Scanner scanner = new Scanner(System.in);

    public void processPayment(double amount, Payment payment, double promoDiscount,
                               String password, String email, boolean isUSD, double rate) {

        System.out.println("\n=== Checkout Started ===");
        System.out.print("Enter your password to confirm payment: ");
        String enteredPassword = scanner.nextLine();

        if (!enteredPassword.equals(password)) {
            System.out.println("- Incorrect password. Payment cancelled.");
            return;
        }

        double finalAmount = amount;
        if (promoDiscount > 0) {
            System.out.println("Promo code applied: -" + (int) promoDiscount + "% discount");
            finalAmount -= (finalAmount * promoDiscount / 100);
        }

        finalAmount = Math.round(finalAmount * 100.0) / 100.0;
        double paid = payment.pay(finalAmount);

        String currencySymbol = isUSD ? "$" : "₸";
        System.out.println("Total paid: " + currencySymbol + format(paid));
        System.out.println("Receipt sent to your email: " + email);
        System.out.println("=== Checkout Completed ===\n");

        if (isUSD) {
            double totalInKZT = paid * rate;
            System.out.println("Equivalent in KZT: ₸" + format(totalInKZT));
        } else {
            double totalInUSD = paid / rate;
            System.out.println("Equivalent in USD: $" + format(totalInUSD));
        }
    }

    private String format(double v) {
        return String.format("%.2f", v);
    }
}
