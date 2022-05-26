package com.adobe.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logging")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Logging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @Column(name = "action")
    private String action;

}
