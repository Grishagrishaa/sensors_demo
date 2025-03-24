package com.mitskevich.sensorstesttask.domain.sensor.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Range(@Column(name = "range_from")
                    Integer from,

                    @Column(name = "range_to")
                    Integer to) {

}
