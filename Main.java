import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static String prompt(String text) {
        System.out.print(text);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        System.out.println("--Welcome to the E-Commerce Checkout System--");

        double randomBalance = 50 + new Random().nextDouble() * 450;
        User user = new User.Builder()
                .setName(prompt("Enter your name: "))
                .setEmail(prompt("Enter your email: "))
                .setPassword(prompt("Create your password: "))
                .setBalance(randomBalance)
                .build();

        double rate = 540.0;
        user.displayInfo(rate);

        System.out.println("\nSelect currency:");
        System.out.println("1. USD (Dollar)");
        System.out.println("2. KZT (Tenge)");
        int currencyChoice = safeIntInput();
        boolean isUSD = (currencyChoice == 1);

        String currencySymbol = isUSD ? "USD" : "KZT";
        System.out.print("\nEnter payment amount in " + currencySymbol + ": ");
        String amountInput = scanner.nextLine().trim().replace(",", ".");
        double amount = parseDouble(amountInput);
        amount = Math.round(amount * 100.0) / 100.0;

        System.out.print("\nEnter promo code (or press Enter to skip): ");
        String promoInput = scanner.nextLine().trim();
        double promoDiscount = 0.0;
        if (promoInput.equals("2416")) {
            promoDiscount = 10.0;
            System.out.println("+ Promo code '2416' accepted! You received a 10% discount.");
        } else if (!promoInput.isEmpty()) {
            System.out.println("- Invalid promo code. No discount applied.");
        }

        System.out.println("\nChoose payment method:");
        System.out.println("1. Credit Card (10% discount + Fraud Detection)");
        System.out.println("2. PayPal (7% cashback + Fraud Detection)");
        System.out.println("3. Installment (12% annual)");

        int paymentOption = safeIntInput();
        Payment basePayment;

        switch (paymentOption) {
            case 1 -> basePayment = new FraudDetectionDecorator(new CreditCardPayment());
            case 2 -> basePayment = new FraudDetectionDecorator(new PayPalPayment());
            case 3 -> {
                System.out.print("Enter 16-digit card number: ");
                String cardNumber = scanner.nextLine().trim();
                if (cardNumber.length() != 16) {
                    System.out.println("Invalid card number. Exiting.");
                    System.exit(0);
                }
                System.out.print("Enter 3-digit CSV: ");
                String csv = scanner.nextLine().trim();
                if (csv.length() != 3) {
                    System.out.println("Invalid CSV. Exiting.");
                    System.exit(0);
                }
                System.out.print("Enter number of months for installment: ");
                int months = safeIntInput();
                basePayment = new InstallmentPayment(months, 12.0);
            }
            default -> basePayment = new FraudDetectionDecorator(new CreditCardPayment());
        }

        Payment decoratedPayment = basePayment;
        if (paymentOption == 1) decoratedPayment = new DiscountDecorator(decoratedPayment, 10.0);
        else if (paymentOption == 2) decoratedPayment = new CashbackDecorator(decoratedPayment, 5.0);

        CheckoutFacade checkout = new CheckoutFacade();
        checkout.processPayment(amount, decoratedPayment, promoDiscount, user.getPassword(), user.getEmail(), isUSD, rate);

        System.out.println("\nThank you for your purchase, " + user.getName() + "!");
    }

    private static int safeIntInput() {
        try { return Integer.parseInt(scanner.nextLine()); }
        catch (Exception e) { return 1; }
    }

    private static double parseDouble(String s) {
        try { return Double.parseDouble(s); }
        catch (Exception e) { return 0.0; }
    }
}
