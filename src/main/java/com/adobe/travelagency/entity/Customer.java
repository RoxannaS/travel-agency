package com.adobe.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer extends Person {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToMany
    @JoinTable(name = "customer_holiday_offer", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "holiday_offer_id"))
    private List<HolidayOffer> holidayOfferList;

    @OneToMany(mappedBy = "customer")
    private List<Document> providedDocumentList;

}
