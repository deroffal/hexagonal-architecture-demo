package fr.deroffal.marketplace.domain.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import fr.deroffal.marketplace.domain.PriceCalculator;
import fr.deroffal.marketplace.domain.model.BasketItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class BasketStepDefinition {

    @Autowired
    private PriceCalculator priceCalculator;

    private final List<BasketItem> basketItems = new ArrayList<>();
    private double actualPrice;

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
