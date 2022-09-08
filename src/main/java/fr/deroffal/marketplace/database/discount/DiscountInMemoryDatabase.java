package fr.deroffal.marketplace.database.discount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.deroffal.marketplace.domain.DiscountPort;
import fr.deroffal.marketplace.domain.model.Discount;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DiscountInMemoryDatabase implements InitializingBean, DiscountPort {

    private final List<Discount> discounts = new ArrayList<>();

    @Override
    public Optional<Discount> loadByItemName(final String name) {
        return discounts.stream().filter(discount -> discount.itemName().equals(name)).findFirst();
    }

    @Override
    public void afterPropertiesSet() {
        discounts.add(new Discount("book", 2, 10));
    }
}
