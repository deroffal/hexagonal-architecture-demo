package fr.deroffal.marketplace.domain;

import java.util.Collection;
import java.util.Optional;

import fr.deroffal.marketplace.domain.model.BasketItem;
import fr.deroffal.marketplace.domain.model.Discount;
import fr.deroffal.marketplace.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceCalculator {

    private final ItemPort itemPort;
    private final DiscountPort discountPort;

    public double getPrice(final Collection<BasketItem> basketItems) {
        return basketItems.stream().map(this::getPrice).reduce(0d, Double::sum);
    }

    private double getPrice(final BasketItem basketItem) {
        final Item item = itemPort.loadItem(basketItem.item()).orElseThrow(() -> new IllegalArgumentException("Unknown item : " + basketItem.item()));
        double price = item.price() * basketItem.quantity();

        final Optional<Discount> itemDiscount = discountPort.loadByItemName(item.name());
        final boolean discountRelevant = itemDiscount.map(discount -> discount.isRelevantOn(basketItem)).orElse(false);
        if (discountRelevant) {
            price = itemDiscount.orElseThrow().applyTo(price);
        }

        return price;
    }
}
