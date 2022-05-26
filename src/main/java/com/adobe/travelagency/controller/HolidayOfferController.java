package com.adobe.travelagency.controller;

import com.adobe.travelagency.entity.HolidayOffer;
import com.adobe.travelagency.domain.TransportationType;
import com.adobe.travelagency.service.HolidayOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/holiday")
public class HolidayOfferController {

    HolidayOfferService holidayOfferService;

    @PostMapping
    public ResponseEntity<HolidayOffer> save(@RequestBody HolidayOffer holidayOffer) {

        Optional<HolidayOffer> savedHolidayOffer = holidayOfferService.save(holidayOffer);
        if (savedHolidayOffer.isPresent()) {
            return new ResponseEntity<>(savedHolidayOffer.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HolidayOffer> findById(@PathVariable long id) {
        Optional<HolidayOffer> holidayOfferOptional = holidayOfferService.findById(id);

        return holidayOfferOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<HolidayOffer> findByDestinationCountry(@RequestParam String destinationCountry) {
        return holidayOfferService.findByDestinationCountry(destinationCountry);
    }

    @GetMapping("/counterByDestinationCountry")
    public long countByDestinationCountry(@RequestParam String country) {
        return holidayOfferService.countByDestinationCountry(country);
    }

    @PutMapping("/priceIncrease")
    public void increasePrice(@RequestParam TransportationType transportation) {
        holidayOfferService.increasePrice(transportation);
    }
}
