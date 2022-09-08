package fr.deroffal.marketplace.domain.model;

public record Discount(String itemName, int threshold, double amount) {

    public boolean isRelevantOn(final BasketItem basketItem) {
        return basketItem.item().equals(itemName) && basketItem.quantity() >= threshold;
    }

    public double applyTo(final double price) {
        return price * (100 - amount()) / 100;
    }
}
