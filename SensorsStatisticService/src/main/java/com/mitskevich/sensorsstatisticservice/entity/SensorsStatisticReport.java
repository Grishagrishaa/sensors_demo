package com.mitskevich.sensorsstatisticservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class SensorsStatisticReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @CreationTimestamp
    private LocalDateTime time;

    private Long total;

    @ElementCollection
    @CollectionTable(name = "sensor_type_count", joinColumns = @JoinColumn(name = "statistics_id"))
    @MapKeyColumn(name = "sensor_type")
    @Column(name = "count")
    private Map<String, Long> sensorTypeCount;
}

