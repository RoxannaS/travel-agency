package com.adobe.travelagency.service;

import com.adobe.travelagency.entity.Destination;
import com.adobe.travelagency.exception.NotFoundDestinationException;
import com.adobe.travelagency.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }


    public Optional<Destination> save(Destination destination) {
        return Optional.of(destinationRepository.save(destination));
    }

    public List<Destination> findAllByCountry(String country) {
        return destinationRepository.findAllByCountry(country);
    }

    public Optional<Destination> findById(long id) {
        return destinationRepository.findById(id);
    }

    public void deleteById(long id) {
        destinationRepository.deleteById(id);
    }

    @Transactional
    public Destination update(long id, Destination newDestination) {
        Optional<Destination> oldDestinationOptional = this.findById(id);

        if (oldDestinationOptional.isPresent()) {
            return destinationRepository.save(newDestination);
        }

        throw new NotFoundDestinationException();
    }
}
