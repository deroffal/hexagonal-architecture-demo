package fr.deroffal.marketplace.domain.cucumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.deroffal.marketplace.domain.ItemPort;
import fr.deroffal.marketplace.domain.PriceCalculator;
import fr.deroffal.marketplace.domain.model.BasketItem;
import fr.deroffal.marketplace.domain.model.Item;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class StepDefinition {

    @Autowired
    private PriceCalculator priceCalculator;

    @Autowired
    private ItemPort itemPort;

    private final List<BasketItem> basketItems = new ArrayList<>();
    private double actualPrice;

    @Given("the price of the following items :")
    public void thePriceOfTheFollowingItems(final List<Item> items) {
        items.forEach(item -> doReturn(Optional.of(item)).when(itemPort).loadItem(item.name()));
    }

    @Given("a {string} costs {double}")
    public void initializePrices(final String itemName, final double price) {
        final Item item = new Item(itemName, price);
        doReturn(Optional.of(item)).when(itemPort).loadItem(itemName);
    }

    @Given("I add {int} {string} in my basket")
    public void addItemToBasket(final long quantity, final String item) {
        basketItems.add(new BasketItem(item, quantity));
    }

    @When("I validate my basket")
    public void validateBasket() {
        actualPrice = priceCalculator.getPrice(basketItems);
    }

    @Then("I should pay {double}")
    public void iShouldPay(final double expectedPrice) {
        assertThat(actualPrice).isEqualTo(expectedPrice);
    }
}
