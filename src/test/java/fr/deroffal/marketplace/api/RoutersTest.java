package fr.deroffal.marketplace.api;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.util.List;

import fr.deroffal.marketplace.api.request.ItemRequest;
import fr.deroffal.marketplace.api.response.BasketPriceResponse;
import fr.deroffal.marketplace.domain.PriceCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ContextConfiguration(classes = WebFluxTestContextConfiguration.class)
@WebFluxTest
class RoutersTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private PriceCalculator priceCalculator;

    @Test
    @DisplayName("api : /basket")
    void postBasket() {
        doReturn(1008.22)
            .when(priceCalculator)
            .getPrice(argThat(
                basketItems -> basketItems.stream().anyMatch(item -> item.item().equals("ball") && item.quantity() == 1L)
                    &&basketItems.stream().anyMatch(item -> item.item().equals("book") && item.quantity() == 2L))
            );

        var exchange = webTestClient.post()
            .uri("/basket").accept(APPLICATION_JSON)
            .body(fromValue(List.of(new ItemRequest("ball", 1), new ItemRequest("book", 2))))
            .exchange();

        exchange.expectStatus().isOk();

        final Flux<BasketPriceResponse> responseBody = exchange.returnResult(BasketPriceResponse.class).getResponseBody();

        StepVerifier.create(responseBody)
            .expectNext(new BasketPriceResponse(1008.22d))
            .verifyComplete();

    }
}
