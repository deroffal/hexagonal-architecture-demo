package fr.deroffal.marketplace.domain.cucumber.steps;

import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

import fr.deroffal.marketplace.domain.ItemPort;
import fr.deroffal.marketplace.domain.model.Item;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemStepDefinitions {

    @Autowired
    private ItemPort itemPort;

    @Given("the price of the following items :")
    public void initializePrices(final List<Item> items) {
        items.forEach(item -> doReturn(Optional.of(item)).when(itemPort).loadItem(item.name()));
    }

    @Given("a {string} costs {double}")
    public void initializePrices(final String itemName, final double price) {
        final Item item = new Item(itemName, price);
        doReturn(Optional.of(item)).when(itemPort).loadItem(itemName);
    }
}
