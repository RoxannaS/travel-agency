package com.adobe.travelagency.service;

import com.adobe.travelagency.entity.Destination;
import com.adobe.travelagency.entity.HolidayOffer;
import com.adobe.travelagency.domain.TransportationType;
import com.adobe.travelagency.exception.NotFoundDestinationException;
import com.adobe.travelagency.repository.HolidayOfferRepository;
import com.adobe.travelagency.service.exchange.ExchangeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayOfferService {

    private final HolidayOfferRepository holidayOfferRepository;
    private final ExchangeService exchangeService;

    public HolidayOfferService(HolidayOfferRepository holidayOfferRepository, @Qualifier("cache") ExchangeService exchangeService) {
        this.holidayOfferRepository = holidayOfferRepository;
        this.exchangeService = exchangeService;
    }

    public Optional<HolidayOffer> findById(long id) {
        Optional<HolidayOffer> oldHolidayOfferOptional = holidayOfferRepository.findById(id);

        if (oldHolidayOfferOptional.isPresent()) {
            HolidayOffer oldHolidayOffer = oldHolidayOfferOptional.get();

            HolidayOffer newHolidayOffer = HolidayOffer.builder()
                    .price(exchangeService.exchangeEuroToRon(oldHolidayOffer.getPrice()).getAmount())
                    .accommodation(oldHolidayOffer.getAccommodation())
                    .availableOffers(oldHolidayOffer.getAvailableOffers())
                    .advancePayment(oldHolidayOffer.getAdvancePayment())
                    .build();

            return Optional.of(newHolidayOffer);
        }

        throw new NotFoundDestinationException();
    }

    public Optional<HolidayOffer> save(HolidayOffer holidayOffer) {
        return Optional.of(holidayOfferRepository.save(holidayOffer));
    }

    public List<HolidayOffer> findByDestinationCountry(String destinationCountry) {
        return holidayOfferRepository.findByDestinationCountry(destinationCountry);
    }

    public long countByDestinationCountry(String country) {
        return holidayOfferRepository.countByDestinationCountry(country);
    }

    @Transactional
    public void increasePrice(TransportationType transportation) {
        holidayOfferRepository.increasePrice(transportation);
    }

}
