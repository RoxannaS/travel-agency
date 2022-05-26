package com.adobe.travelagency.entity;

import com.adobe.travelagency.domain.PackageType;
import com.adobe.travelagency.domain.TransportationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "holiday_offer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HolidayOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;

    //private List<DocumentType> requiredDocumentTypeList;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportation_type")
    private TransportationType transportationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "package_type")
    private PackageType packageType;

    private Double price;

    @Column(name = "advance_payment")
    private Double advancePayment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "deadline_payment")
    private LocalDate deadlinePayment;

    @Column(name = "available_offers")
    private Integer availableOffers;

}
