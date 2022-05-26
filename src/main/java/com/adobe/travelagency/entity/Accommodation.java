package com.adobe.travelagency.entity;

import com.adobe.travelagency.domain.AccommodationType;
import com.adobe.travelagency.domain.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "accommodation_type")
    private AccommodationType accommodationType;

    private String name;

    @Column(name = "number_of_stars")
    private Integer numberOfStars;

    @ColumnDefault("false")
    @Column(name = "has_pool")
    private Boolean hasPool;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

}
