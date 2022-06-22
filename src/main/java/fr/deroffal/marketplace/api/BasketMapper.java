package fr.deroffal.marketplace.api;

import fr.deroffal.marketplace.api.request.ItemRequest;
import fr.deroffal.marketplace.config.MapperConfiguration;
import fr.deroffal.marketplace.domain.model.BasketItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
interface BasketMapper {

    BasketItem toBasketItem(ItemRequest itemRequest);
}
