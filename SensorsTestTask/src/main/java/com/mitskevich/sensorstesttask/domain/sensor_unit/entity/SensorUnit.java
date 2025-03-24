package com.mitskevich.sensorstesttask.domain.sensor_unit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorUnit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

}
