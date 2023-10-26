package tycoonclicker.voidane.com.Mechanics;

public class Currency {

    private static double currency;
    private static double currencyFromCopper;
    private static double currencyPerClick = 1;

    public static double getCurrencyPerClick() {
        return currencyPerClick;
    }

    public static void setCurrencyPerClick(double currencyPerClick) {
        Currency.currencyPerClick = currencyPerClick;
    }

    public static double getCurrencyFromCopper() {
        return currencyFromCopper;
    }

    public static void setCurrencyFromCopper(double currencyFromCopper) {
        Currency.currencyFromCopper = currencyFromCopper;
    }

    public static double getCurrency() {
        return currency;
    }

    public static void setCurrency(double currency) {
        Currency.currency = currency;
    }
}
