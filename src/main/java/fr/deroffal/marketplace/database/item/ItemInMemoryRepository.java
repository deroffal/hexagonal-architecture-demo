package fr.deroffal.marketplace.database.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.deroffal.marketplace.domain.ItemPort;
import fr.deroffal.marketplace.domain.model.Item;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ItemInMemoryRepository implements InitializingBean, ItemPort {

    private final List<Item> items = new ArrayList<>();

    @Override
    public Optional<Item> loadItem(final String name) {
        return items.stream().filter(item -> item.name().equals(name)).findFirst();
    }

    @Override
    public void afterPropertiesSet() {
        items.add(new Item("book", 10d));
        items.add(new Item("ball", 5.5d));
    }
}
