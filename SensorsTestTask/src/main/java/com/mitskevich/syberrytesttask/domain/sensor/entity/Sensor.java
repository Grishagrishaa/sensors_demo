package com.mitskevich.syberrytesttask.domain.sensor.entity;

import com.mitskevich.syberrytesttask.domain.sensor_unit.entity.SensorUnit;
import com.mitskevich.syberrytesttask.domain.sensor.entity.embedded.Range;
import com.mitskevich.syberrytesttask.domain.sensor_type.entity.SensorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@FieldNameConstants
@AllArgsConstructor @NoArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;
    private String name;
    private String model;
    private String location;
    private String description;

    @Embedded
    private Range range;

    @ManyToOne(fetch = FetchType.LAZY)
    private SensorType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private SensorUnit unit;

}
