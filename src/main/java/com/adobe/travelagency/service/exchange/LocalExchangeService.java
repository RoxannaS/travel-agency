package com.adobe.travelagency.service.exchange;

import com.adobe.travelagency.domain.exchange.ExchangeResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("local")
public class LocalExchangeService implements ExchangeService {

    public ExchangeResponse exchangeEuroToRon(Double amount) {
        // Take data from local file and return it
        return new ExchangeResponse(10d, 10d);
    }
}
