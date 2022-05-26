package com.adobe.travelagency.service.exchange;

import lombok.AllArgsConstructor;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.adobe.travelagency.domain.exchange.ExchangeApiResponse;
import com.adobe.travelagency.domain.exchange.ExchangeResponse;

@Service
@Qualifier("api")
@AllArgsConstructor
public class ApiExchangeService implements ExchangeService {

    private ExchangeConnectorProperties properties;

    public ExchangeResponse exchangeEuroToRon(Double amount) {
        HttpResponse<ExchangeApiResponse> response = Unirest.get(properties.getHostUrl() + "/api/latest")
                .queryString("access_key", "f7dbe1842278-43779b")
                .asObject(ExchangeApiResponse.class);

        if (response.isSuccess()) {
            Double curs = response.getBody().getRates().getEUR();
            return new ExchangeResponse(amount * curs, curs);
        }

        return new ExchangeResponse(amount, 4.9);
    }
}
