package fr.deroffal.marketplace.domain;

import java.util.Collection;

import fr.deroffal.marketplace.domain.model.BasketItem;
import fr.deroffal.marketplace.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceCalculator {

    private final ItemPort itemPort;

    public double getPrice(final Collection<BasketItem> basketItems) {
        return basketItems.stream().map(this::getPrice).reduce(0d, Double::sum);
    }

    private double getPrice(final BasketItem basketItem) {
        final Item item = itemPort.loadItem(basketItem.item()).orElseThrow(() -> new IllegalArgumentException("Unknown item : " + basketItem.item()));
        return item.price() * basketItem.quantity();
    }
}
