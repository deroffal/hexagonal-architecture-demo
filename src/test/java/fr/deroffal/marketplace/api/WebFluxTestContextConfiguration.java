package fr.deroffal.marketplace.api;

import fr.deroffal.marketplace.domain.PriceCalculator;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = WebFluxTestContextConfiguration.class)
public class WebFluxTestContextConfiguration {

    @MockBean
    private PriceCalculator priceCalculator;
}
