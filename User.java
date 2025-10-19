class User {
    private final String name, email, password;
    private final double balanceUSD;
    private User(Builder b) {
        this.name = b.name;
        this.email = b.email;
        this.password = b.password;
        this.balanceUSD = b.balanceUSD;
    }

    public static class Builder {
        private String name, email, password;
        private double balanceUSD;

        public Builder setName(String n) {
            this.name = n;
            return this;
        }

        public Builder setEmail(String e) {
            this.email = e;
            return this;
        }

        public Builder setPassword(String p) {
            this.password = p;
            return this;
        }

        public Builder setBalance(double b) {
            this.balanceUSD = b;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void displayInfo(double rate) {
        System.out.println("\nWelcome, " + name + "!");
        System.out.println("Email: " + email);
        System.out.println("Balance: $" + format(balanceUSD) + " | ₸" + format(balanceUSD * rate));
    }

    private String format(double v) {
        return String.format("%.2f", v);
    }
}
