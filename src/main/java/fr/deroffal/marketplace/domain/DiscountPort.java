package fr.deroffal.marketplace.domain;

import java.util.Optional;

import fr.deroffal.marketplace.domain.model.Discount;

public interface DiscountPort {
    Optional<Discount> loadByItemName(String name);
}
