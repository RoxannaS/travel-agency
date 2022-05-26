package com.adobe.travelagency.controller;

import com.adobe.travelagency.entity.Destination;
import com.adobe.travelagency.service.DestinationService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destination")
public class DestinationController {

    DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping
    public ResponseEntity<Destination> save(@RequestBody Destination destination) {

        Optional<Destination> savedDestinationService = destinationService.save(destination);
        if (savedDestinationService.isPresent()) {
            return new ResponseEntity<>(savedDestinationService.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Destination> findAllByCountry(@RequestParam String country) {
        return destinationService.findAllByCountry(country);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Destination> deleteById(@PathVariable long id) {
        try {
            destinationService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> update(@PathVariable long id, @RequestBody Destination destination) {
        return new ResponseEntity(destinationService.update(id, destination), HttpStatus.OK);
    }
}
