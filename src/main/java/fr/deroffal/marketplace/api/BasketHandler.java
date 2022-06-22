package fr.deroffal.marketplace.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import fr.deroffal.marketplace.api.request.ItemRequest;
import fr.deroffal.marketplace.api.response.BasketPriceResponse;
import fr.deroffal.marketplace.domain.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class BasketHandler {

    private final BasketMapper basketMapper;
    private final PriceCalculator priceCalculator;

    public Mono<ServerResponse> newBasket(final ServerRequest request) {
        final Flux<ItemRequest> itemRequest = request.bodyToFlux(ItemRequest.class);
        return itemRequest
            .map(basketMapper::toBasketItem).collectList()
            .map(priceCalculator::getPrice)
            .map(BasketPriceResponse::new)
            .flatMap(this::getBody);
    }

    private Mono<ServerResponse> getBody(final Object result) {
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromValue(result));
    }
}

