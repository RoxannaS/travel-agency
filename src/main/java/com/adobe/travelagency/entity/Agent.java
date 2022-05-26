package com.adobe.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("agent")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Agent extends Person {

    @OneToMany(mappedBy = "agent")
    private List<Customer> customerList;

}
