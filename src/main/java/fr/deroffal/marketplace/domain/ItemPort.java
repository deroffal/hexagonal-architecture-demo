package fr.deroffal.marketplace.domain;

import java.util.Optional;

import fr.deroffal.marketplace.domain.model.Item;

public interface ItemPort {

    Optional<Item> loadItem(String name);
}
