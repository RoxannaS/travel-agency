package com.adobe.travelagency.repository;

import com.adobe.travelagency.entity.HolidayOffer;
import com.adobe.travelagency.domain.TransportationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayOfferRepository extends JpaRepository<HolidayOffer, Long> {

    @Query("select h from HolidayOffer h join h.accommodation a join a.destination d " +
            "where d.country = :destinationCountry")
    List<HolidayOffer> findByDestinationCountry(String destinationCountry);

    @Query("select count(h) from HolidayOffer h join h.accommodation a join a.destination d " +
            "where d.country = :country")
    long countByDestinationCountry(String country);

    @Modifying
    @Query("update HolidayOffer h set h.price = h.price * 1.10 " +
            "where h.transportationType = :transportationType")
    void increasePrice(TransportationType transportationType);
}
