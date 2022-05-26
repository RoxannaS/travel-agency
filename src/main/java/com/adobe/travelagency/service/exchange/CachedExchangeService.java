package com.adobe.travelagency.service.exchange;

import com.adobe.travelagency.domain.exchange.ExchangeResponse;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("cache")
public class CachedExchangeService implements ExchangeService {

    private final ExchangeService exchangeService;
    private final Cache<String, Double> cache;

    public CachedExchangeService(@Qualifier("api") ExchangeService exchangeService,
                                 Cache<String, Double> cache) {
        this.exchangeService = exchangeService;
        this.cache = cache;
    }

    @Override
    public ExchangeResponse exchangeEuroToRon(Double amount) {
        Double curs = cache.get("CURS", (key) -> exchangeService.exchangeEuroToRon(amount).getCurs());
        return new ExchangeResponse(amount * curs, curs);
    }
}
